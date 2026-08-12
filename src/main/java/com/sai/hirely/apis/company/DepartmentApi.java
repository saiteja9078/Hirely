package com.sai.hirely.apis.company;

import com.sai.hirely.dto.company.DepartmentRequest;
import com.sai.hirely.dto.company.DepartmentResponse;
import com.sai.hirely.mappers.DepartmentMapper;
import com.sai.hirely.service.company.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentApi {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentApi(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(departmentMapper.toResponse(departmentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        departmentMapper.toResponse(
                                departmentService.addDepartment(
                                        departmentMapper.toEntity(request),
                                        request.companyId()
                                )
                        )
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                departmentMapper.toResponse(departmentService.updateDepartment(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
