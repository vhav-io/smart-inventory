package com.vaibhav.smartinventory.product;

public class DuplicateSkuException extends RuntimeException {

    public DuplicateSkuException(String sku) {
        super("Product already exists with SKU: " + sku);
    }
}