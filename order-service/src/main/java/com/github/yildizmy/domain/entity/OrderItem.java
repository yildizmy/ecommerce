package com.github.yildizmy.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
public class OrderItem extends BaseEntity {

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Long productId;  // for cross-service reference, use id field instead of entity to keep services loosely coupled

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(quantity, orderItem.quantity) &&
                Objects.equals(price, orderItem.price) &&
                Objects.equals(productId, orderItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price, productId);
    }
}
