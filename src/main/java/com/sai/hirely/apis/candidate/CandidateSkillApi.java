package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.candidate.skill.CandidateSkillsRequest;
import com.sai.hirely.dto.candidate.skill.CandidateSkillResponse;
import com.sai.hirely.dto.candidate.skill.SkillDto;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.mappers.CandidateSkillMapper;
import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.service.candidate.skill.CandidateSkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/candidate-skills")
public class CandidateSkillApi {


    private CandidateSkillService skillService;
    private CandidateSkillMapper skillMapper;
    @Autowired
    public CandidateSkillApi(CandidateSkillService skillService, CandidateSkillMapper skillMapper) {
        this.skillMapper = skillMapper;
        this.skillService = skillService;
    }
    @GetMapping("{candidateId}")
    public ResponseEntity<CandidateSkillResponse> getSkills(@PathVariable Long candidateId) throws CandidateNotFoundException {
        List<CandidateSkill> list = skillService.getSkills(candidateId);
        List<SkillDto> skillList = new ArrayList<>();
        for(CandidateSkill skill : list) {
            skillList.add(skillMapper.toSkillDto(skill));
        }
        CandidateSkillResponse response = new CandidateSkillResponse(candidateId,skillList);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @PostMapping
    public ResponseEntity<CandidateSkillResponse> addSkills(@Valid @RequestBody CandidateSkillsRequest skillsRequest) throws CandidateNotFoundException{
        CandidateSkillResponse response = skillMapper.toResponse(skillService.addSkills(skillsRequest),skillsRequest.candidateId());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

}
