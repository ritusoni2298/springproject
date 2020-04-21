package com.crm.marchbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderName;

    /*
    * product --> multiple orders
    *
    * */
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="product_id", nullable=false)
    @JsonIgnore
    private Product product;

    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public Order(){
    }

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Long getOrderId() {
        return id;
    }

    public void setOrderId(Long orderId) {
        this.id = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }



    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", OrderName='" + orderName + '\'' +

                '}';
    }
}

//order_product => fk
//class Address{
//    String building;
//    String city;
//    String country;
//
//    //getter setter
//}

/*
* main
* Address add = new Address("12","ny","us");
* Order ord = new Order("dryer",add)*/