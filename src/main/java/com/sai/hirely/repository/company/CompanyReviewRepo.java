package com.sai.hirely.repository.company;

import com.sai.hirely.models.company.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyReviewRepo extends JpaRepository<CompanyReview, Long> {
    List<CompanyReview> findByCompanyId(Long companyId);
}
