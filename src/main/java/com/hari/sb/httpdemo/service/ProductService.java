package com.hari.sb.httpdemo.service;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.exception.ProductNotFoundException;
import com.hari.sb.httpdemo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    List<Product> pList = null;

    // hardcoded list of products
    ProductService() {
        pList = new ArrayList<>();
        pList.add(new Product(10, "Necklace", "Jewelery"));
        pList.add(new Product(20, "Bangles", "Jewelery"));
        pList.add(new Product(30, "Earrings", "Jewelery"));
        pList.add(new Product(50, "Sarees", "Clothing"));
        pList.add(new Product(70, "Blouses", "Clothing"));
        pList.add(new Product(100, "Dresses", "Clothing"));
        // Electronic Items
        pList.add(new Product(110, "Smartphone", "Electronics"));
        pList.add(new Product(120, "Laptop", "Electronics"));
        pList.add(new Product(130, "Headphones", "Electronics"));
        pList.add(new Product(140, "Smartwatch", "Electronics"));
        pList.add(new Product(150, "Tablet", "Electronics"));
        pList.add(new Product(160, "Television", "Electronics"));
        pList.add(new Product(170, "Camera", "Electronics"));
        pList.add(new Product(180, "Bluetooth Speaker", "Electronics"));

// Footwear
        pList.add(new Product(190, "Sneakers", "Footwear"));
        pList.add(new Product(200, "Running Shoes", "Footwear"));
        pList.add(new Product(210, "Heels", "Footwear"));
        pList.add(new Product(220, "Sandals", "Footwear"));
        pList.add(new Product(230, "Boots", "Footwear"));
        pList.add(new Product(240, "Slippers", "Footwear"));

// Men's Clothing
        pList.add(new Product(250, "T-Shirts", "Clothing"));
        pList.add(new Product(260, "Jeans", "Clothing"));
        pList.add(new Product(270, "Formal Shirts", "Clothing"));
        pList.add(new Product(280, "Trousers", "Clothing"));
        pList.add(new Product(290, "Jackets", "Clothing"));
        pList.add(new Product(300, "Sweaters", "Clothing"));

// Home & Kitchen Appliances
        pList.add(new Product(310, "Microwave Oven", "Appliances"));
        pList.add(new Product(320, "Refrigerator", "Appliances"));
        pList.add(new Product(330, "Washing Machine", "Appliances"));
        pList.add(new Product(340, "Blender", "Appliances"));
        pList.add(new Product(350, "Coffee Maker", "Appliances"));
        pList.add(new Product(360, "Air Fryer", "Appliances"));
        pList.add(new Product(370, "Vacuum Cleaner", "Appliances"));

// Cosmetics & Personal Care
        pList.add(new Product(380, "Lipstick", "Cosmetics"));
        pList.add(new Product(390, "Foundation", "Cosmetics"));
        pList.add(new Product(400, "Eyeliner", "Cosmetics"));
        pList.add(new Product(410, "Perfume", "Cosmetics"));
        pList.add(new Product(420, "Moisturizer", "Cosmetics"));
        pList.add(new Product(430, "Shampoo", "Cosmetics"));
        pList.add(new Product(440, "Sunscreen", "Cosmetics"));

// Books & Stationery
        pList.add(new Product(450, "Notebooks", "Stationery"));
        pList.add(new Product(460, "Pen Set", "Stationery"));
        pList.add(new Product(470, "Novel Books", "Books"));
        pList.add(new Product(480, "Textbooks", "Books"));
        pList.add(new Product(490, "Art Markers", "Stationery"));
        pList.add(new Product(500, "Desk Organizer", "Stationery"));

// Sports & Fitness
        pList.add(new Product(510, "Yoga Mat", "Fitness"));
        pList.add(new Product(520, "Dumbbells", "Fitness"));
        pList.add(new Product(530, "Cricket Bat", "Sports"));
        pList.add(new Product(540, "Football", "Sports"));
        pList.add(new Product(550, "Badminton Racket", "Sports"));
        pList.add(new Product(560, "Water Bottle", "Fitness"));

// Bags & Accessories
        pList.add(new Product(570, "Backpack", "Accessories"));
        pList.add(new Product(580, "Handbag", "Accessories"));
        pList.add(new Product(590, "Wallet", "Accessories"));
        pList.add(new Product(600, "Sunglasses", "Accessories"));
        pList.add(new Product(610, "Leather Belt", "Accessories"));

// Home Decor & Bedding
        pList.add(new Product(620, "Bedsheets", "Home Decor"));
        pList.add(new Product(630, "Curtains", "Home Decor"));
        pList.add(new Product(640, "Wall Painting", "Home Decor"));
        pList.add(new Product(650, "Scented Candles", "Home Decor"));

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
        return productRepo.findAll(Sort.by(field).descending());
    }

    public Page<Product> findProductsWithPagination(int pageNumber, int pageSize){
        // 1. Sanitize Page Number: Prevent negative page indices
        int sanitizedPage = Math.max(0, pageNumber);

        // 2. Enforce Hard Limits: Prevent malicious Out-of-Memory attacks
        int maxPageSize = 100;
        int sanitizedSize = (pageSize < 1) ? 20 : Math.min(pageSize, maxPageSize);

        // 3. Mandatory Sorting: Databases do not guarantee row order.
        // Without sorting, items can randomly swap pages on the UI.
        Pageable pageable = PageRequest.of(sanitizedPage, sanitizedSize, Sort.by("productId").ascending());

        return productRepo.findAll(pageable);
    }

    public Slice<Product> findProductsWithPaginationBySlice(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepo.findSliceAll(pageable); // Requires a custom repository method or Slice return type
    }
    /*
    Why? Slice only looks ahead by one extra record (LIMIT size + 1) to check if a "Next" page exists.
    It completely skips the expensive COUNT(*) query, making your real-time API infinitely faster
     */


    public Page<Product> findProductsWithPaginationAndSort(int pageNumber, int pageSize, String field){

        return productRepo.findAll(PageRequest.of(pageNumber, pageSize)
                .withSort(Sort.by(field).ascending()));
    }

}
