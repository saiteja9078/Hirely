package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.dto.company.CompanyResponse;
import com.sai.hirely.mappers.CompanyMapper;
import com.sai.hirely.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sai.hirely.repository.company.HiringManagerRepo;
import com.sai.hirely.mappers.HiringManagerMapper;
import com.sai.hirely.dto.company.HiringManagerResponse;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.service.company.HiringManagerService;
import com.sai.hirely.models.company.HiringManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final HiringManagerRepo hiringManagerRepo;
    private final HiringManagerMapper hiringManagerMapper;
    private final HiringManagerService hiringManagerService;

    @Autowired
    public CompanyApi(CompanyService companyService, CompanyMapper companyMapper,
                      HiringManagerRepo hiringManagerRepo, HiringManagerMapper hiringManagerMapper,
                      HiringManagerService hiringManagerService) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.hiringManagerRepo = hiringManagerRepo;
        this.hiringManagerMapper = hiringManagerMapper;
        this.hiringManagerService = hiringManagerService;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.findAll().stream().map(companyMapper::toResponse).toList();
    }

    @GetMapping("/me")
    public ResponseEntity<CompanyResponse> getCurrentCompany(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.COMPANY);
        return ResponseEntity.ok(companyMapper.toResponse(companyService.findById(user.getId())));
    }

    @PatchMapping("/me")
    public ResponseEntity<CompanyResponse> updateCurrentCompany(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody CompanyRequest request
    ) {
        CurrentUser.require(user, AccountType.COMPANY);
        return ResponseEntity.ok(companyMapper.toResponse(companyService.updateCompany(user.getId(), request)));
    }

    @GetMapping("/me/hiring-managers")
    public List<HiringManagerResponse> getCurrentCompanyHiringManagers(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.COMPANY);
        return hiringManagerRepo.findByCompanyId(user.getId()).stream().map(hiringManagerMapper::toResponse).toList();
    }

    @DeleteMapping("/me/hiring-managers/{hiringManagerId}")
    public ResponseEntity<Void> deleteCompanyHiringManager(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable("hiringManagerId") Long hiringManagerId
    ) {
        CurrentUser.require(user, AccountType.COMPANY);
        HiringManager manager = hiringManagerService.findById(hiringManagerId);
        if (manager.getHiringDepartment() == null || manager.getHiringDepartment().getCompany() == null || !manager.getHiringDepartment().getCompany().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        hiringManagerService.deleteHiringManager(hiringManagerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyMapper.toResponse(companyService.findById(id)));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long id,
            @RequestBody CompanyRequest request
    ) {
        CurrentUser.require(user, AccountType.COMPANY);
        CurrentUser.requireId(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                companyMapper.toResponse(companyService.updateCompany(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@AuthenticationPrincipal CustomUserDetails user, @PathVariable Long id) {
        CurrentUser.require(user, AccountType.COMPANY);
        CurrentUser.requireId(user, id);
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
