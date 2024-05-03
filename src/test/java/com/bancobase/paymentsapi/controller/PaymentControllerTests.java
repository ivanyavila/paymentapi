package com.bancobase.paymentsapi.controller;

import com.bancobase.paymentsapi.entity.Payment;
import com.bancobase.paymentsapi.entity.PaymentStatus;
import com.bancobase.paymentsapi.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTests {

    @MockBean
    PaymentService paymentService;

    @Autowired
    MockMvc mockMvc;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setId(100L);
        payment.setConcept("Pago 1/3");
        payment.setQuantity(1);
        payment.setRecipient("Receptor");
        payment.setPayer("Emisor");
        payment.setAmount(BigDecimal.valueOf(15.25));
        payment.setStatus(PaymentStatus.PENDING);
    }


    @Test
    public void testFindById() throws Exception {
        Mockito.when(paymentService.getPaymentById(100L)).thenReturn(payment);
        mockMvc.perform(get("/api/v1/payments/{id}",100L))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.concept").value("Pago 1/3"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.recipient").value("Receptor"))
                .andExpect(jsonPath("$.payer").value("Emisor"))
                .andExpect(jsonPath("$.amount").value(15.25))
                .andExpect(jsonPath("$.status").value(PaymentStatus.PENDING.toString()));
    }

    @Test
    public void testCreatePayment() throws Exception {
        Mockito.when(paymentService.createPayment(payment)).thenReturn(payment);
        mockMvc.perform(
                    post("/api/v1/payments")
                            .content(asJsonString(payment))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.concept").value("Pago 1/3"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.recipient").value("Receptor"))
                .andExpect(jsonPath("$.payer").value("Emisor"))
                .andExpect(jsonPath("$.amount").value(15.25))
                .andExpect(jsonPath("$.status").value(PaymentStatus.PENDING.toString()));
    }

    @Test
    public void testUpdateStatusPayment() throws Exception {
        payment.setStatus(PaymentStatus.COMPLETED);
        Mockito.when(paymentService.updatePaymentStatus(100L, PaymentStatus.COMPLETED)).thenReturn(payment);
        mockMvc.perform(put("/api/v1/payments/{id}/{status}",100,PaymentStatus.COMPLETED))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.concept").value("Pago 1/3"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.recipient").value("Receptor"))
                .andExpect(jsonPath("$.payer").value("Emisor"))
                .andExpect(jsonPath("$.amount").value(15.25))
                .andExpect(jsonPath("$.status").value(PaymentStatus.COMPLETED.toString()));
    }

    @Test
    public void testCreatePaymentConstraintViolationException() throws Exception {
        payment.setQuantity(0);
        payment.setAmount(BigDecimal.valueOf(0.00));
        Mockito.when(paymentService.createPayment(payment)).thenThrow(ConstraintViolationException.class);
        assertThatThrownBy(() -> paymentService.createPayment(payment))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testFindByIdException() throws Exception {
        Mockito.when(paymentService.getPaymentById(123456L)).thenThrow(NoSuchElementException.class);
        assertThatThrownBy(() -> paymentService.getPaymentById(123456L))
                .isInstanceOf(NoSuchElementException.class);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void setDown() {
        payment = null;
    }

}
