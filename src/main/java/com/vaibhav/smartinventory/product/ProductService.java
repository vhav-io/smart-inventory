package com.vaibhav.smartinventory.product;

import com.vaibhav.smartinventory.product.dto.ProductRequest;
import com.vaibhav.smartinventory.product.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException(request.getSku());
        }

        Product product = new Product();

        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        return productRepository.save(product);
    }

    public Page<ProductResponse> getAllProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size))
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getSku(),
                        product.getDescription(),
                        product.getPrice()
                ));
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        Product updatedProduct = productRepository.save(product);

        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getSku(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice()
        );
    }

    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }
}