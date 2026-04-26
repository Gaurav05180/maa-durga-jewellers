package com.maadurga.jewellers.service;

import com.maadurga.jewellers.dto.InquiryForm;
import com.maadurga.jewellers.entity.Inquiry;
import com.maadurga.jewellers.repository.InquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    // Customer submits inquiry — no login needed
    public void saveInquiry(InquiryForm form) {
        Inquiry inquiry = Inquiry.builder()
                .name(form.getName())
                .email(form.getEmail())
                .phone(form.getPhone())
                .message(form.getMessage())
                .read(false)
                .build();
        inquiryRepository.save(inquiry);
    }

    // Admin views all inquiries
    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAllByOrderByCreatedAtDesc();
    }

    public long getUnreadCount() {
        return inquiryRepository.countByReadFalse();
    }

    public void markAsRead(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        inquiry.setRead(true);
        inquiryRepository.save(inquiry);
    }

    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }
}