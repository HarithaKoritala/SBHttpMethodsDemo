package com.hari.sb.httpdemo.controller;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class MainController {

    @Autowired
    ProductService productService;

    @GetMapping("/get/{type}")
    public ResponseEntity<List<Product>> getProductsType(@PathVariable("type") String type) {
        List<Product> prodList = productService.getProductList(type);
        return ResponseEntity.ok(prodList);
    }
}
