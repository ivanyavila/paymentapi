package com.bancobase.paymentsapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String concept;
    @Valid
    @Min(1)
    private int quantity;
    private String payer;
    private String recipient;
    @Valid
    @DecimalMin("0.01")
    private BigDecimal amount;
    private PaymentStatus status;
}
