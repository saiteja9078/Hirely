package com.sai.hirely.apis.candidate;

import com.sai.hirely.dto.skill.candidate.CandidateSkillDto;
import com.sai.hirely.dto.skill.candidate.CandidateSkillsRequest;
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
    @Autowired
    public CandidateSkillApi(CandidateSkillService skillService) {
        this.skillService = skillService;
    }
    @GetMapping("/{candidateId}")
    public ResponseEntity<List<CandidateSkillDto>> getSkills(@PathVariable Long candidateId)  {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.findAllByCandidateId(candidateId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSkills(@Valid @RequestBody CandidateSkillsRequest skillsRequest) {
         skillService.addSkills(skillsRequest);
    }
    @PatchMapping("/{candidateId}/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSkill(@PathVariable Long skillId,@PathVariable Long candidateId,
                            @RequestParam Proficiency proficiency
    ) {
        skillService.updateSkill(new CandidateSkillKey(skillId,candidateId),proficiency);
    }

    @DeleteMapping("/{candidateId}/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSkill(@PathVariable Long candidateId, @PathVariable Long skillId) {
        skillService.deleteSkill(new CandidateSkillKey(skillId, candidateId));
    }
}
