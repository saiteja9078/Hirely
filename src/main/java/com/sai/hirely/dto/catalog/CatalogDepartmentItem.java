package com.sai.hirely.dto.catalog;

public record CatalogDepartmentItem(
        Long id,
        String name,
        Long companyId,
        String companyName
) {
}
