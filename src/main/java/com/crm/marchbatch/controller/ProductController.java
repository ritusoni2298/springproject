package com.crm.marchbatch.controller;

import com.crm.marchbatch.model.Product;
import com.crm.marchbatch.repositories.OrderRepository;
import com.crm.marchbatch.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    //CRUD
    //Create => post
    //Read => get
    //update => put
    //delete => delete

    @GetMapping("/products")
    public List<Product> getProducts(){
       return productRepository.findAll();
    }

    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public Product addProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/products/{productId}")
    public Product editProduct(@PathVariable(value = "productId") Long id, @Valid @RequestBody Product product) throws Exception{
       Optional<Product> oldProduct= productRepository.findById(id);
       if(oldProduct.isPresent()){
           oldProduct.get().setProductName(product.getProductName());
           oldProduct.get().setSpecification(product.getSpecification());
           return productRepository.save(oldProduct.get());
       }else{
            throw new Exception("invalid");
       }
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable(value = "productId") Long id){
        productRepository.deleteById(id);
        return "deleted";
    }




}
//spring beans
//dispatcher servlet
//DI
//database connectivity



//Spring Bean
//Component
//ComponentScan
//Dependency Injection => DI
//Inversion of Control => IOC
//application.proterties => specifications


//Spring boot => Actuator and Devtools



