package com.hari.sb.httpdemo.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data /* @ToString, @EqualsAndHashCode, @Getter, @Setter(all non-static fields),
 if field is final it generates only getters for it, @RequiredArgsConstructor(every final fields) */
// If your class has no final or @NonNull fields, Lombok generates a plain, empty no-argument constructor by default.
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    Integer productId;

    @Column(name = "product_name")
    @NonNull
    String productName;

    @Column(name = "product_type")
    @NonNull
    String productType;
}
