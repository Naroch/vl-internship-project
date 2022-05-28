package com.virtuslab.internship.controller;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AppController {

    @Autowired
    private AppService appService;


//    @GetMapping("/products")
//    public Set<Product> listProducts() {
//        return appService.getProducts();
//    }
//
//    @GetMapping("/basket")
//    public List<Product> listBasket() {
//        return appService.getBasket();
//    }
//
//    @PostMapping("/basket/{productName}")
//    public ResponseEntity<Product> addToBasket(@PathVariable String productName) {
//        return new ResponseEntity<Product>(appService.addToBasket(productName), HttpStatus.OK);
//    }

    @PostMapping("basket/stateless")
    public ResponseEntity<Receipt> generateReceipt(@RequestBody List<Product> productsList) {
        return new ResponseEntity<Receipt>(appService.generateReceipt(productsList), HttpStatus.OK);
    }

//    @GetMapping("/receipt")
//    public Receipt generateReceipt() {
//        return appService.generateReceipt();
//    }
}
