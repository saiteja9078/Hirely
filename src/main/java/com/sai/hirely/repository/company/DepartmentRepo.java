package com.sai.hirely.repository.company;

import com.sai.hirely.models.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.hirely.dto.catalog.CatalogDepartmentItem;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    @Query("""
            select new com.sai.hirely.dto.catalog.CatalogDepartmentItem(
                department.id, department.name, company.id, company.name
            )
            from Department department join department.company company
            order by company.name, department.name
            """)
    List<CatalogDepartmentItem> findCatalogItems();
}
