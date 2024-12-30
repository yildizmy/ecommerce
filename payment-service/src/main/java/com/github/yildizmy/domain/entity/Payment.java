package com.github.yildizmy.domain.entity;

import com.github.yildizmy.domain.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"transactionId"}, callSuper = false)
public class Payment extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, unique = true)
    private Long orderId;  // for cross-service reference, use id field instead of entity to keep services loosely coupled
}
