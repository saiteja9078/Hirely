package com.sai.hirely.service.candidate;
import com.sai.hirely.dto.candidate.experience.*;
import com.sai.hirely.exceptions.candidate.CandidateExperienceNotFoundException;
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
import org.springframework.web.servlet.RequestToViewNameTranslator;

import java.util.*;

@Service
public class CandidateExperienceService {

    private final RoleRepo roleRepo;
    private final RequestToViewNameTranslator requestToViewNameTranslator;
    private CandidateExperienceRepo experienceRepo;
    private CandidateRepo candidateRepo;
    private CompanyRepo companyRepo;
    private CandidateExperienceMapper experienceMapper;

    @Autowired
    public CandidateExperienceService(CandidateExperienceRepo candidateExperienceRepo,
                                      CandidateRepo candidateRepo, RoleRepo roleRepo,CompanyRepo companyRepo,
                                      CandidateExperienceMapper experienceMapper,
                                      RequestToViewNameTranslator requestToViewNameTranslator){
        this.experienceRepo = candidateExperienceRepo;
        this.candidateRepo = candidateRepo;
        this.roleRepo = roleRepo;
        this.companyRepo = companyRepo;
        this.experienceMapper = experienceMapper;
        this.requestToViewNameTranslator = requestToViewNameTranslator;
    }
    @Transactional
    public void addCandidateExperiences(CandidateExperienceRequest request) {
        Candidate candidate = candidateRepo.getReferenceById(request.candidateId());
        List<CreateExperienceDto> createExperienceDtos  = request.createRoles();
        List<ExistingExperienceDto> existingDtos = request.existingRoles();
        List<CandidateExperience> experiences = new ArrayList<>();
        for(CreateExperienceDto experienceDto: createExperienceDtos) {
            RoleEntity roleEntity = new RoleEntity(experienceDto.roleName());
            try {
                roleRepo.save(roleEntity);
            } catch (DataIntegrityViolationException e) {
                roleEntity = roleRepo.findByName(experienceDto.roleName());
            }
            experiences.add( new CandidateExperience(
                        roleEntity,
                        experienceDto.organizationName(),
                        experienceDto.companyId() != null ? companyRepo.getReferenceById(experienceDto.companyId()) : null,
                        candidate,
                        experienceDto.experienceInMonths()
                ));
        }
        for (ExistingExperienceDto experienceDto : existingDtos) {
            RoleEntity roleEntity = roleRepo.getReferenceById(experienceDto.roleId());
            experiences.add( new CandidateExperience(
                    roleEntity,
                    experienceDto.organizationName(),
                    experienceDto.companyId() != null ? companyRepo.getReferenceById(experienceDto.companyId()) : null,
                    candidate,
                    experienceDto.experienceInMonths()
            ));
        }
        experienceRepo.saveAll(experiences);
    }
    @Transactional(readOnly = true)
    public List<CandidateExperienceResponse> findById(Long candidateId) {
        return experienceMapper.toResponseList(experienceRepo.findByCandidateId(candidateId));
    }

    @Transactional
    public CandidateExperienceResponse updateExperience(CandidateExperienceUpdateRequest experienceRequest) {
        CandidateExperience experience = experienceRepo.findById(experienceRequest.experienceId()).orElseThrow(() -> new CandidateExperienceNotFoundException(experienceRequest.experienceId()));
        experience.setCompany(companyRepo.getReferenceById(experienceRequest.companyId()));
        experience.setDescription(experienceRequest.description());
        experience.setOrganizationName(experienceRequest.organizationName());
        experience.setExperienceInMonths(experienceRequest.experienceInMonths());
        return experienceMapper.toResponse(experience);
    }

    @Transactional
    public void deleteExperience(Long experienceId) {
        if (!experienceRepo.existsById(experienceId)) {
            throw new CandidateExperienceNotFoundException(experienceId);
        }
        experienceRepo.deleteById(experienceId);
    }
}
