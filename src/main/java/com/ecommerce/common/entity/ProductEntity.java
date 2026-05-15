package com.ecommerce.common.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


/**
 * Entity representing product details.
 */
@Entity
@Table(name = "tb_products")
@Data
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 150,unique=true)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;




}