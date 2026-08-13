package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.CompanyRequest;
import com.sai.hirely.dto.company.CompanyResponse;
import com.sai.hirely.mappers.CompanyMapper;
import com.sai.hirely.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyApi(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(companyMapper.toResponse(companyService.findById(id)));
    }



    @PatchMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @RequestBody CompanyRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                companyMapper.toResponse(companyService.updateCompany(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
