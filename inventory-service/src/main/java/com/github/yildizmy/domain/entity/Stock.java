package com.github.yildizmy.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"sku"}, callSuper = false)
public class Stock extends BaseEntity {

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Version
    private Long version;

    @Column(nullable = false, unique = true)
    private Long productId;  // for cross-service reference, use id field instead of entity to keep services loosely coupled
}
