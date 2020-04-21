package com.crm.marchbatch.controller;

import com.crm.marchbatch.model.Order;
import com.crm.marchbatch.model.Product;
import com.crm.marchbatch.repositories.OrderRepository;
import com.crm.marchbatch.repositories.ProductRepository;
import com.crm.marchbatch.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderServices orderServices;

    @GetMapping("/products/{productId}/orders")
    public List<Order> getAllOrders(@PathVariable(value = "productId") Long id){
      return orderServices.getAllOrders(id);
    }

    @GetMapping("/products/{productId}/orders/{orderId}")
    public Optional<Order> findOrderById(@PathVariable(value = "productId") Long productid,
                                         @PathVariable(value = "orderId") Long orderid){
        return orderRepository.findById(orderid);
    }

    @GetMapping("/products/{productId}/orderwithname")
    @ResponseBody
    public List<Order> findByName(@PathVariable(value = "productId") Long productid, @RequestParam("str") String str, @RequestParam("id") Long id){
        return orderRepository.findByName(str,id);
    }

    @GetMapping("/products/{productId}/orderstartswith")
    @ResponseBody
    public List<Order> findByString(@PathVariable(value = "productId") Long productid, @RequestParam String str){
        return orderRepository.findByStartsWithString(str);
    }


    @PostMapping("/products/{productId}/orders")
    public Order addOrder(@RequestBody @Valid Order order, @PathVariable(value = "productId") Long id) throws Exception{
        return orderServices.addOrder(order,id);
    }

    //put and delete
    @PutMapping("/products/{productId}/orders/{orderId}")
    public Order editOrder(@PathVariable(value = "productId") Long productId,
                           @PathVariable(value = "orderId") Long orderId, @Valid @RequestBody Order order) throws Exception{
        return orderServices.editOrder(productId,orderId,order);
    }

    @DeleteMapping("/products/{productId}/orders/{orderId}")
    public String deleteOrder(@PathVariable(value = "productId") Long productId,@PathVariable(value = "orderId") Long orderId){
       return deleteOrder(productId,orderId);

    }


}
