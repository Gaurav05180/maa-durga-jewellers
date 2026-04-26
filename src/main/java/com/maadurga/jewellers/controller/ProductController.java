package com.maadurga.jewellers.controller;

import com.maadurga.jewellers.entity.Category;
import com.maadurga.jewellers.service.CategoryService;
import com.maadurga.jewellers.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // /products — show all active products
    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllActiveProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategory", null);
        return "products";
    }

    // /products/category/{slug} — filter by category
    @GetMapping("/category/{slug}")
    public String productsByCategory(@PathVariable String slug, Model model) {
        Category category = categoryService.getCategoryBySlug(slug);
        model.addAttribute("products", productService.getProductsByCategory(category));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategory", category);
        return "products";
    }

    // /products/{id} — single product detail
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-detail";
    }
}