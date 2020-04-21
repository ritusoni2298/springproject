package com.crm.marchbatch.services;

import com.crm.marchbatch.model.Order;
import com.crm.marchbatch.model.Product;
import com.crm.marchbatch.repositories.OrderRepository;
import com.crm.marchbatch.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders(@PathVariable(value = "productId") Long id){
        Optional<Product> product=productRepository.findById(id);
        return orderRepository.findByProduct(product.get());
    }


    public Order addOrder(@RequestBody @Valid Order order, @PathVariable(value = "productId") Long id) throws Exception{
        Optional<Product> product = productRepository.findById(id);
        System.out.println(product.get().getProductName());
        if(product.isPresent()){
            order.setProduct(product.get());
            System.out.println(order.getOrderName());
            return orderRepository.save(order);
        }else{
            throw new Exception("Invalid");
        }
    }

    //put and delete

    public Order editOrder(@PathVariable(value = "productId") Long productId,
                           @PathVariable(value = "orderId") Long orderId, @Valid @RequestBody Order order) throws Exception{
        Optional<Order> oldOrder = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            if(oldOrder.isPresent()) {
                oldOrder.get().setOrderName(order.getOrderName());
                return orderRepository.save(oldOrder.get());
            }else{
                throw new Exception("invalid");
            }
        }else{
            throw new Exception("Invalid");
        }
    }


    public String deleteOrder(@PathVariable(value = "productId") Long productId,@PathVariable(value = "orderId") Long orderId){
        Optional<Product> product = productRepository.findById(productId);
        Optional<Order> order = orderRepository.findById(orderId);
        if(product.isPresent()){
            if(order.isPresent()){
                orderRepository.deleteById(orderId);
                return "deleted";
            }
            else{
                return "not deleted: order not found";
            }
        }else{
            return "not deleted: product not found";
        }

    }

}
