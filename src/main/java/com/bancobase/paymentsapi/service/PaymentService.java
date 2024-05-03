package com.bancobase.paymentsapi.service;

import com.bancobase.paymentsapi.entity.Payment;
import com.bancobase.paymentsapi.entity.PaymentStatus;
import com.bancobase.paymentsapi.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final RabbitTemplate rabbitTemplate;

    public PaymentService(PaymentRepository paymentRepository, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found with id: " + id));
    }

    public Payment updatePaymentStatus(Long id, PaymentStatus status) {
        Payment payment = getPaymentById(id);
        PaymentStatus currentStatus = payment.getStatus();
        if (!currentStatus.equals(status)) {
            payment.setStatus(status);
            Payment paymentUpdated = paymentRepository.save(payment);
            rabbitTemplate.convertAndSend("StatusQueue", payment + " status updated to : " + payment.getStatus());
            return paymentUpdated;
        }
        return payment;
    }
}
