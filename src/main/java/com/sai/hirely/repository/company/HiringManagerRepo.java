package com.sai.hirely.repository.company;

import com.sai.hirely.models.company.HiringManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface HiringManagerRepo extends JpaRepository<HiringManager, Long> {
    Optional<HiringManager> findByEmail(String email);

    @Query("""
            select manager from HiringManager manager
            join fetch manager.hiringDepartment department
            join fetch department.company
            where manager.id = :managerId
            """)
    Optional<HiringManager> findWithCompanyById(Long managerId);

    @Query("""
            select manager from HiringManager manager
            join fetch manager.hiringDepartment department
            where department.company.id = :companyId
            order by manager.firstName, manager.lastName
            """)
    List<HiringManager> findByCompanyId(Long companyId);
}
