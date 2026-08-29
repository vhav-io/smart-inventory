package com.vaibhav.smartinventory.product.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price;

    public ProductResponse(
            Long id,
            String name,
            String sku,
            String description,
            BigDecimal price
    ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}