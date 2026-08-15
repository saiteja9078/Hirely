package com.sai.hirely.service.company;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.models.company.Company;
import com.sai.hirely.repository.company.CompanyRepo;
import com.sai.hirely.repository.job.IndustryRepo;
import com.sai.hirely.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sai.hirely.exceptions.company.EntityNotFoundException;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;
    private final IndustryRepo industryRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public CompanyService(CompanyRepo companyRepo, IndustryRepo industryRepo, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.companyRepo = companyRepo;
        this.industryRepo = industryRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Transactional(readOnly = true)
    public Company findById(Long id) throws EntityNotFoundException {
        return companyRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Company", id)
        );
    }

    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    @Transactional
    public Company addCompany(Company company, Long industryId, String industryName) {
        if (company.getPassword() != null && !company.getPassword().isEmpty()) {
            company.setPassword(passwordEncoder.encode(company.getPassword()));
        }
        if (industryId != null) {
            company.setIndustry(industryRepo.getReferenceById(industryId));
        } else if (industryName != null && !industryName.trim().isEmpty()) {
            com.sai.hirely.models.job.Industry newIndustry = new com.sai.hirely.models.job.Industry(industryName.trim());
            newIndustry = industryRepo.save(newIndustry);
            company.setIndustry(newIndustry);
        }
        Company savedCompany = companyRepo.save(company);
        emailService.sendWelcomeEmail(savedCompany.getEmail(), savedCompany.getName(), "Company Partner");
        return savedCompany;
    }

    @Transactional
    public Company updateCompany(Long id, CompanyRequest request) throws EntityNotFoundException {
        Company company = findById(id);
        if (request.name() != null) company.setName(request.name());
        if (request.companyProfileUrl() != null) company.setCompanyProfileUrl(request.companyProfileUrl());
        if (request.location() != null) company.setLocation(request.location());
        if (request.email() != null) company.setEmail(request.email());
        
        if (request.industryId() != null && (company.getIndustry() == null || !company.getIndustry().getId().equals(request.industryId()))) {
            company.setIndustry(industryRepo.getReferenceById(request.industryId()));
        }
        
        return company;
    }

    @Transactional
    public void deleteCompany(Long id) throws EntityNotFoundException {
        if (!companyRepo.existsById(id)) {
            throw new EntityNotFoundException("Company", id);
        }
        companyRepo.deleteById(id);
    }
}
