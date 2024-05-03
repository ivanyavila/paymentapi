package com.bancobase.paymentsapi.repository;

import com.bancobase.paymentsapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
