package com.sai.hirely.repository.company;

import com.sai.hirely.models.company.HiringManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringManagerRepo extends JpaRepository<HiringManager, Long> {
}
