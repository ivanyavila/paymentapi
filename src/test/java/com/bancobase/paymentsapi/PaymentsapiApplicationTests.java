package com.bancobase.paymentsapi;

import com.bancobase.paymentsapi.controller.CustomErrorController;
import com.bancobase.paymentsapi.controller.PaymentController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PaymentsapiApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	PaymentController paymentController;

	@Autowired
	CustomErrorController customErrorController;

	@Test
	void contextLoads() {
		assertNotNull(applicationContext);
	}

	@Test
	void paymentControllerLoads() {
		assertNotNull(paymentController);
	}

	@Test
	void customErrorControllerLoads() {
		assertNotNull(customErrorController);
	}

}
