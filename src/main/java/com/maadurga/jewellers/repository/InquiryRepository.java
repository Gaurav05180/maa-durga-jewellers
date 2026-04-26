package com.maadurga.jewellers.repository;

import com.maadurga.jewellers.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByOrderByCreatedAtDesc();
    long countByReadFalse();           // unread badge count for admin
}