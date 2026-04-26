package com.maadurga.jewellers.controller;

import com.maadurga.jewellers.dto.InquiryForm;
import com.maadurga.jewellers.service.InquiryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("inquiryForm", new InquiryForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitInquiry(@Valid @ModelAttribute("inquiryForm") InquiryForm form,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "contact";
        }
        inquiryService.saveInquiry(form);
        redirectAttributes.addFlashAttribute("successMessage",
                "Thank you! We will get back to you shortly.");
        return "redirect:/contact";
    }
}