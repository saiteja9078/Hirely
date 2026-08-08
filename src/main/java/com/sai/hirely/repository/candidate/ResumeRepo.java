package com.sai.hirely.repository.candidate;

import com.sai.hirely.models.utils.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<Resume,Long> {
}
