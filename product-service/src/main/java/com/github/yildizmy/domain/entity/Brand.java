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
@EqualsAndHashCode(of = {"name"}, callSuper = false)
public class Brand extends BaseEntity {

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(length = 200)
    private String logoUrl;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setBrand(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setBrand(null);
    }
}
