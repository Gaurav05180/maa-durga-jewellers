package com.maadurga.jewellers.controller.admin;

import com.maadurga.jewellers.entity.Category;
import com.maadurga.jewellers.entity.Product;
import com.maadurga.jewellers.service.CategoryService;
import com.maadurga.jewellers.service.InquiryService;
import com.maadurga.jewellers.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.nio.file.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final InquiryService inquiryService;

    // folder where uploaded images are saved
    private static final String UPLOAD_DIR = "uploads/images/products/";
    // private static final String UPLOAD_DIR = "src/main/resources/static/images/products/";

    public AdminController(ProductService productService,
                           CategoryService categoryService,
                           InquiryService inquiryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.inquiryService = inquiryService;
    }

    // ── Login ────────────────────────────────────────

    @GetMapping("/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()
                && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/admin/dashboard";
        }
        return "admin/login";
    }

    // ── Dashboard ────────────────────────────────────

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalProducts",  productService.getAllProducts().size());
        model.addAttribute("totalCategories", categoryService.getAllCategories().size());
        model.addAttribute("unreadInquiries", inquiryService.getUnreadCount());
        model.addAttribute("recentInquiries", inquiryService.getAllInquiries()
                .stream().limit(5).toList());
        return "admin/dashboard";
    }

    // ── Products ─────────────────────────────────────

    @GetMapping("/products")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes) throws IOException {

        // handle image upload if a file was selected
        if (!imageFile.isEmpty()) {
            String filename = System.currentTimeMillis()
                    + "_" + imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);
            Files.copy(imageFile.getInputStream(),
                    uploadPath.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            product.setImageUrl("/images/products/" + filename);
        }

        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully.");
        return "redirect:/admin/products";
    }

    @GetMapping("/products/toggle-active/{id}")
    public String toggleActive(@PathVariable Long id) {
        productService.toggleActive(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/toggle-featured/{id}")
    public String toggleFeatured(@PathVariable Long id) {
        productService.toggleFeatured(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted.");
        return "redirect:/admin/products";
    }

    // ── Categories ───────────────────────────────────

    @GetMapping("/categories")
    public String adminCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("newCategory", new Category());
        return "admin/categories";
    }

    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute("newCategory") Category category,
                               RedirectAttributes redirectAttributes) {
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category saved.");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted.");
        return "redirect:/admin/categories";
    }

    // ── Inquiries ────────────────────────────────────

    @GetMapping("/inquiries")
    public String adminInquiries(Model model) {
        model.addAttribute("inquiries", inquiryService.getAllInquiries());
        model.addAttribute("unreadCount", inquiryService.getUnreadCount());
        return "admin/inquiries";
    }

    @GetMapping("/inquiries/mark-read/{id}")
    public String markRead(@PathVariable Long id) {
        inquiryService.markAsRead(id);
        return "redirect:/admin/inquiries";
    }

    @GetMapping("/inquiries/delete/{id}")
    public String deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return "redirect:/admin/inquiries";
    }
}