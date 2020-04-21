package com.crm.marchbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //=> product_id

    private String productName;

    private String specification;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Order> orderslist = new HashSet<>();

    public Set<Order> getOrderslist() {
        return orderslist;
    }

    public void setOrderslist(Set<Order> orderslist) {
        this.orderslist = orderslist;
    }

    //reviews
    public Product(){

    }


    public Product(String productName, String specification) {
        this.productName = productName;
        this.specification = specification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + id +
                ", productName='" + productName + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
