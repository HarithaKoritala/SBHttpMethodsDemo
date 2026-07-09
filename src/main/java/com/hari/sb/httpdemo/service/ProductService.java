package com.hari.sb.httpdemo.service;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.exception.ProductNotFoundException;
import com.hari.sb.httpdemo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // using custom exception
    public List<Product> getProductStaticList(String type) {
        List<Product> pTypeList = pList.stream()
                .filter(p -> p.getProductType().equals(type)).toList();
        return Optional.of(pTypeList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    // if we don't find list we should return all products
    public List<Product> getProductListByType(String type) {
        if (type == null)
            return productRepo.findAll();
        return productRepo.findByProductType(type);
    }

    public List<Product> getAllProducts(String field){
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> findProductsWithPagination(int pageNumber, int pageSize){

        //page size: 1-100, 101-200
        //offset 0, 1
        return productRepo.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> findProductsWithPaginationAndSort(int pageNumber, int pageSize, String field){

        //page size: 1-100, 101-200
        //offset 0, 1
        return productRepo.findAll(
                    PageRequest.of(pageNumber, pageSize)
                .withSort(Sort.by(field)));
    }

}
