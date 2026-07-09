package com.hari.sb.httpdemo.controller;

import com.hari.sb.httpdemo.dao.Product;
import com.hari.sb.httpdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get/static/{type}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getStaticProductsType(@PathVariable("type") String type) {
        List<Product> prodList = productService.getProductStaticList(type);
        return ResponseEntity.ok(prodList);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProductsByType(@RequestParam(value="type", required = false)
                                                            // here required acts as optional
                                                               String type) {
        List<Product> prodList = productService.getProductListByType(type);
        return ResponseEntity.ok(prodList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProductsWithPagination(
            @RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(productService.findProductsWithPagination(pageNum, pageSize));
    }

}
