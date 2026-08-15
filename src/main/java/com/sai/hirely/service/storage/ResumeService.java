package com.sai.hirely.service.storage;

import com.sai.hirely.dto.file.ResumeResponse;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.utils.Resume;
import com.sai.hirely.repository.candidate.ResumeRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.Path;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class ResumeService {
    private final StorageService storageService;
    private final ResumeRepo resumeRepo;

    public ResumeService(@Qualifier("localStorage") StorageService storageService, ResumeRepo resumeRepo) {
        this.storageService = storageService;
        this.resumeRepo = resumeRepo;
    }

    @Transactional
    public ResumeResponse upload(Candidate candidate, MultipartFile file) {
        String text = storageService.extractTxt(file);
        String storedPath = storageService.storePdf(file);
        Resume resume = resumeRepo.save(new Resume(file.getOriginalFilename(), storedPath, text, candidate));
        return toResponse(resume);
    }

    @Transactional(readOnly = true)
    public List<ResumeResponse> findByCandidateId(Long candidateId) {
        return resumeRepo.findByCandidateIdOrderByUploadedAtDesc(candidateId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void delete(Long resumeId, Long candidateId) {
        Resume resume = resumeRepo.findByIdAndCandidateId(resumeId, candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Resume", resumeId));
        resumeRepo.delete(resume);
    }

    @Transactional(readOnly = true)
    public Resource download(Long resumeId, Long candidateId) {
        Resume resume = resumeRepo.findByIdAndCandidateId(resumeId, candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Resume", resumeId));
        try {
            Path path = Path.of(resume.getStoredPath());
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Resume getResumeEntity(Long resumeId, Long candidateId) {
        return resumeRepo.findByIdAndCandidateId(resumeId, candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Resume", resumeId));
    }

    private ResumeResponse toResponse(Resume resume) {
        return new ResumeResponse(resume.getId(), resume.getActualName(), resume.getUploadedAt());
    }
}
