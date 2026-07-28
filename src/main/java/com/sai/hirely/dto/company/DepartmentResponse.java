package com.sai.hirely.dto.company;

public record DepartmentResponse(
        Long id,
        String name,
        CompanyResponse company
) {
}
