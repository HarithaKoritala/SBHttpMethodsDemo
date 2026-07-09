package com.hari.sb.httpdemo.service;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    List<Product> pList = null;

    ProductService() {
        pList = new ArrayList<>();
        pList.add(new Product(10, "Necklace", "Jewelery"));
        pList.add(new Product(20, "Bangles", "Jewelery"));
        pList.add(new Product(30, "Earrings", "Jewelery"));
        pList.add(new Product(50, "Sarees", "Clothing"));
        pList.add(new Product(70, "Blouses", "Clothing"));
        pList.add(new Product(100, "Dresses", "Clothing"));
    }

    public List<Product> getProductList(String type) {
        return productRepo.findByProductType(type);
    }

    public List<Product> getProductStaticList(String type) {
        return pList.stream().filter(p -> p.getProductType().equals(type)).toList();
    }

    // if we don't find list we should return all products
    public List<Product> getProductListByType(String type) {
        if (type == null)
            return productRepo.findAll();
        return productRepo.findByProductType(type);
    }


}
