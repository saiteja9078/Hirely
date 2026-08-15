package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.CompanyReviewRequest;
import com.sai.hirely.dto.company.CompanyReviewResponse;
import com.sai.hirely.service.company.CompanyReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;

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
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam Long companyId,
            @Valid @RequestBody CompanyReviewRequest request) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return reviewService.addReview(user.getId(), companyId, request);
    }

    @GetMapping("/company/{companyId}")
    public List<CompanyReviewResponse> getReviewsForCompany(@PathVariable Long companyId) {
        return reviewService.getReviewsForCompany(companyId);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long reviewId,
            @RequestParam(required = false) Long candidateId) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        reviewService.deleteReview(reviewId, user.getId());
    }
}
