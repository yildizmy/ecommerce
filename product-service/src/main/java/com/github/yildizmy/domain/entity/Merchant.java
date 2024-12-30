package com.github.yildizmy.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = {"email"}, callSuper = false)
public class Merchant extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 100)
    private String companyName;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setMerchant(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setMerchant(null);
    }
}
