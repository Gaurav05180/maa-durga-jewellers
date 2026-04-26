package com.maadurga.jewellers.repository;

import com.maadurga.jewellers.entity.Product;
import com.maadurga.jewellers.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();
    List<Product> findByFeaturedTrueAndActiveTrue();
    List<Product> findByCategoryAndActiveTrue(Category category);
}