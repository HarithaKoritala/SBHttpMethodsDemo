package com.hari.sb.httpdemo.service;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getProductList(String type){
        return productRepo.findByProductType(type);
    }
}
