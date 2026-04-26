package com.maadurga.jewellers.service;

import com.maadurga.jewellers.entity.Category;
import com.maadurga.jewellers.entity.Product;
import com.maadurga.jewellers.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ── Public / customer facing ────────────────────

    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    public List<Product> getFeaturedProducts() {
        return productRepository.findByFeaturedTrueAndActiveTrue();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategoryAndActiveTrue(category);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ── Admin facing ────────────────────────────────

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void toggleActive(Long id) {
        Product p = getProductById(id);
        p.setActive(!p.isActive());
        productRepository.save(p);
    }

    public void toggleFeatured(Long id) {
        Product p = getProductById(id);
        p.setFeatured(!p.isFeatured());
        productRepository.save(p);
    }
}