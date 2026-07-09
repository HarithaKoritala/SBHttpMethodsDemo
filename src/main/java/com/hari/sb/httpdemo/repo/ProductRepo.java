package com.hari.sb.httpdemo.repo;

import com.hari.sb.httpdemo.dao.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductType(String productType);

    // Option B: Generic fetch-all override using a JPQL query
    @Query("SELECT p FROM Product p")
    Slice<Product> findSliceAll(Pageable pageable);
}
