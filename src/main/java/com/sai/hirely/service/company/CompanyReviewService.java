package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.CompanyReviewRequest;
import com.sai.hirely.dto.company.CompanyReviewResponse;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.exceptions.company.NotEligibleToReviewException;
import com.sai.hirely.mappers.CompanyReviewMapper;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.models.company.CompanyReview;
import com.sai.hirely.repository.candidate.CandidateExperienceRepo;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.company.CompanyRepo;
import com.sai.hirely.repository.company.CompanyReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyReviewService {

    private final CompanyReviewRepo reviewRepo;
    private final CompanyRepo companyRepo;
    private final CandidateRepo candidateRepo;
    private final CandidateExperienceRepo candidateExperienceRepo;
    private final CompanyReviewMapper reviewMapper;

    @Autowired
    public CompanyReviewService(
            CompanyReviewRepo reviewRepo,
            CompanyRepo companyRepo,
            CandidateRepo candidateRepo,
            CandidateExperienceRepo candidateExperienceRepo,
            CompanyReviewMapper reviewMapper) {
        this.reviewRepo = reviewRepo;
        this.companyRepo = companyRepo;
        this.candidateRepo = candidateRepo;
        this.candidateExperienceRepo = candidateExperienceRepo;
        this.reviewMapper = reviewMapper;
    }

    public CompanyReviewResponse addReview(Long candidateId, Long companyId, CompanyReviewRequest request) {
        if (!candidateExperienceRepo.existsByCandidateIdAndCompanyId(candidateId, companyId)) {
            throw new NotEligibleToReviewException("Candidate has not worked at this company.");
        }

        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate", candidateId));
        Company company = companyRepo.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company", companyId));

        CompanyReview review = new CompanyReview();
        review.setText(request.text());
        review.setStars(request.stars());
        review.setCandidate(candidate);
        review.setCompany(company);

        review = reviewRepo.save(review);
        return reviewMapper.toResponse(review);
    }

    @Transactional(readOnly = true)
    public List<CompanyReviewResponse> getReviewsForCompany(Long companyId) {
        return reviewMapper.toResponseList(reviewRepo.findByCompanyId(companyId));
    }

    public void deleteReview(Long reviewId, Long candidateId) {
        CompanyReview review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("CompanyReview", reviewId));
        
        if (!review.getCandidate().getId().equals(candidateId)) {
            throw new NotEligibleToReviewException("You can only delete your own reviews.");
        }

        reviewRepo.delete(review);
    }
}
