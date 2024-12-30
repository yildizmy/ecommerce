package com.github.yildizmy.domain.entity;

import com.github.yildizmy.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Order extends BaseEntity {

    @Column(nullable = false)
    private Long userId;  // for cross-service reference, use id field instead of entity to keep services loosely coupled

    @Column(nullable = false)
    private Long addressId;  // for cross-service reference, use id field instead of entity to keep services loosely coupled

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(userId, order.userId) &&
                Objects.equals(addressId, order.addressId) &&
                status == order.status &&
                Objects.equals(totalAmount, order.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, addressId, status, totalAmount);
    }
}
