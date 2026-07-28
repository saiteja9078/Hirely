package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.candidate.skill.CandidateSkillsProjection;
import com.sai.hirely.dto.candidate.skill.CandidateSkillsRequest;
import com.sai.hirely.dto.candidate.skill.CandidateSkillResponse;
import com.sai.hirely.mappers.CandidateSkillsMapper;
import com.sai.hirely.models.candidate.CandidateSkill;
import com.sai.hirely.models.candidate.CandidateSkillKey;
import com.sai.hirely.models.enums.Proficiency;
import com.sai.hirely.service.candidate.CandidateSkillService;
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
    private CandidateSkillsMapper skillsMapper;
    @Autowired
    public CandidateSkillApi(CandidateSkillService skillService,CandidateSkillsMapper skillsMapper,) {
        this.skillService = skillService;
        this.skillsMapper = skillsMapper;
    }
    @GetMapping("{candidateId}")
    public ResponseEntity<CandidateSkillResponse> getSkills(@PathVariable Long candidateId)  {
        List<CandidateSkillsProjection> skills =  skillService.findAllByCandidateId(candidateId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(skillsMapper.toSkillResponse(skills,candidateId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CandidateSkillResponse> addSkills(@Valid @RequestBody CandidateSkillsRequest skillsRequest) {
         skillService.addSkills(skillsRequest);
    }
    @PatchMapping("/{candidateId}/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSkill(@PathVariable Long skillId,@PathVariable Long candidateId,
                            @RequestParam Proficiency proficiency
    ) {
        skillService.updateSkill(new CandidateSkillKey(skillId,candidateId),proficiency);
    }
}
