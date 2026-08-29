package com.vaibhav.smartinventory.product;
import com.vaibhav.smartinventory.product.dto.ProductRequest;
import com.vaibhav.smartinventory.product.dto.ProductResponse;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product createProduct(ProductRequest request) {

    	Product product = new Product();

    	product.setName(request.getName());
    	product.setSku(request.getSku());
    	product.setDescription(request.getDescription());
    	product.setPrice(request.getPrice());

        return productRepository.save(product);
}
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getSku(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .toList();
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
}
