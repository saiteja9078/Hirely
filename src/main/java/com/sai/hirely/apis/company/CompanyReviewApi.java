package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.CompanyReviewRequest;
import com.sai.hirely.dto.company.CompanyReviewResponse;
import com.sai.hirely.service.company.CompanyReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company-reviews")
public class CompanyReviewApi {

    private final CompanyReviewService reviewService;

    @Autowired
    public CompanyReviewApi(CompanyReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyReviewResponse addReview(
            @RequestParam Long candidateId,
            @RequestParam Long companyId,
            @Valid @RequestBody CompanyReviewRequest request) {
        return reviewService.addReview(candidateId, companyId, request);
    }

    @GetMapping("/company/{companyId}")
    public List<CompanyReviewResponse> getReviewsForCompany(@PathVariable Long companyId) {
        return reviewService.getReviewsForCompany(companyId);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long candidateId) {
        reviewService.deleteReview(reviewId, candidateId);
    }
}
