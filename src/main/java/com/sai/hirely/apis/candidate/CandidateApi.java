package com.sai.hirely.apis.candidate;
import com.sai.hirely.dto.candidate.CandidateRequest;
import com.sai.hirely.dto.candidate.CandidateResponse;
import com.sai.hirely.exceptions.candidate.CandidateNotFoundException;
import com.sai.hirely.mappers.CandidateMapper;
import com.sai.hirely.service.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidates")
public class CandidateApi
{
    private CandidateService candidateService;
    private CandidateMapper candidateMapper;
    @Autowired
    public CandidateApi(CandidateService candidateService, CandidateMapper candidateMapper) {
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidate(@PathVariable Long id) throws CandidateNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(candidateMapper.toResponse(candidateService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<CandidateResponse> addCandidate(
            @RequestBody CandidateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        candidateMapper.toResponse(
                                candidateService.addCandidate(
                                        candidateMapper.toEntity(request)
                                )
                        )
                );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequest request
    )  {
        return ResponseEntity.status(HttpStatus.OK).body(candidateMapper.toResponse(candidateService.updateCandidate(id,request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
