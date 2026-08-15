package com.sai.hirely.service.candidate;
import com.sai.hirely.dto.candidate.experience.*;
import com.sai.hirely.exceptions.company.EntityNotFoundException;
import com.sai.hirely.mappers.CandidateExperienceMapper;
import com.sai.hirely.models.candidate.Candidate;
import com.sai.hirely.models.candidate.CandidateExperience;
import com.sai.hirely.models.utils.RoleEntity;
import com.sai.hirely.repository.candidate.CandidateExperienceRepo;
import com.sai.hirely.repository.candidate.CandidateRepo;
import com.sai.hirely.repository.company.CompanyRepo;
import com.sai.hirely.repository.role.RoleRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CandidateExperienceService {

    private final RoleRepo roleRepo;
    private CandidateExperienceRepo experienceRepo;
    private CandidateRepo candidateRepo;
    private CompanyRepo companyRepo;
    private CandidateExperienceMapper experienceMapper;

    @Autowired
    public CandidateExperienceService(CandidateExperienceRepo candidateExperienceRepo,
                                      CandidateRepo candidateRepo, RoleRepo roleRepo,CompanyRepo companyRepo,
                                      CandidateExperienceMapper experienceMapper){
        this.experienceRepo = candidateExperienceRepo;
        this.candidateRepo = candidateRepo;
        this.roleRepo = roleRepo;
        this.companyRepo = companyRepo;
        this.experienceMapper = experienceMapper;
    }
    @Transactional
    public void addCandidateExperiences(CandidateExperienceRequest request) {
        Candidate candidate = candidateRepo.getReferenceById(request.candidateId());
        List<CreateExperienceDto> createExperienceDtos = Optional.ofNullable(request.createRoles()).orElseGet(List::of);
        List<ExistingExperienceDto> existingDtos = Optional.ofNullable(request.existingRoles()).orElseGet(List::of);
        List<CandidateExperience> experiences = new ArrayList<>();
        for(CreateExperienceDto experienceDto: createExperienceDtos) {
            RoleEntity roleEntity = new RoleEntity(experienceDto.roleName());
            try {
                roleRepo.save(roleEntity);
            } catch (DataIntegrityViolationException e) {
                roleEntity = roleRepo.findByName(experienceDto.roleName());
            }
            CandidateExperience experience = new CandidateExperience(
                        roleEntity,
                        experienceDto.organizationName(),
                        experienceDto.companyId() != null ? companyRepo.getReferenceById(experienceDto.companyId()) : null,
                        candidate,
                        experienceDto.fromDate(),
                        experienceDto.toDate()
                );
            experience.setDescription(experienceDto.description());
            experiences.add(experience);
        }
        for (ExistingExperienceDto experienceDto : existingDtos) {
            RoleEntity roleEntity = roleRepo.getReferenceById(experienceDto.roleId());
            CandidateExperience experience = new CandidateExperience(
                    roleEntity,
                    experienceDto.organizationName(),
                    experienceDto.companyId() != null ? companyRepo.getReferenceById(experienceDto.companyId()) : null,
                    candidate,
                    experienceDto.fromDate(),
                    experienceDto.toDate()
            );
            experience.setDescription(experienceDto.description());
            experiences.add(experience);
        }
        if (!experiences.isEmpty()) {
            experienceRepo.saveAll(experiences);
        }
    }
    @Transactional(readOnly = true)
    public List<CandidateExperienceResponse> findById(Long candidateId) {
        return experienceMapper.toResponseList(experienceRepo.findByCandidateIdOrderByFromDateDesc(candidateId));
    }

    @Transactional
    public CandidateExperienceResponse updateExperience(CandidateExperienceUpdateRequest experienceRequest) {
        CandidateExperience experience = experienceRepo.findById(experienceRequest.experienceId()).orElseThrow(() -> new EntityNotFoundException("CandidateExperience", experienceRequest.experienceId()));
        return updateExperience(experience, experienceRequest);
    }

    @Transactional
    public CandidateExperienceResponse updateExperience(Long candidateId, CandidateExperienceUpdateRequest experienceRequest) {
        CandidateExperience experience = experienceRepo.findByIdAndCandidateId(experienceRequest.experienceId(), candidateId)
                .orElseThrow(() -> new EntityNotFoundException("CandidateExperience", experienceRequest.experienceId()));
        return updateExperience(experience, experienceRequest);
    }

    private CandidateExperienceResponse updateExperience(CandidateExperience experience, CandidateExperienceUpdateRequest experienceRequest) {
        if (experienceRequest.companyId() != null) {
            experience.setCompany(companyRepo.getReferenceById(experienceRequest.companyId()));
        } else {
            experience.setCompany(null);
        }
        experience.setDescription(experienceRequest.description());
        experience.setOrganizationName(experienceRequest.organizationName());
        experience.setFromDate(experienceRequest.fromDate());
        experience.setToDate(experienceRequest.toDate());
        return experienceMapper.toResponse(experience);
    }

    @Transactional
    public void deleteExperience(Long experienceId) {
        if (!experienceRepo.existsById(experienceId)) {
            throw new EntityNotFoundException("CandidateExperience", experienceId);
        }
        experienceRepo.deleteById(experienceId);
    }

    @Transactional
    public void deleteExperience(Long experienceId, Long candidateId) {
        CandidateExperience experience = experienceRepo.findByIdAndCandidateId(experienceId, candidateId)
                .orElseThrow(() -> new EntityNotFoundException("CandidateExperience", experienceId));
        experienceRepo.delete(experience);
    }
}
