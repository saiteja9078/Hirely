package com.sai.hirely.apis.resumes;


import com.sai.hirely.dto.file.ResumeResponse;
import com.sai.hirely.security.CurrentUser;
import com.sai.hirely.security.details.AccountType;
import com.sai.hirely.security.details.CustomUserDetails;
import com.sai.hirely.service.candidate.CandidateService;
import com.sai.hirely.service.storage.ResumeService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/files/upload")
public class FileUploadApi {

    private final ResumeService resumeService;
    private final CandidateService candidateService;

    public FileUploadApi(ResumeService resumeService, CandidateService candidateService) {
        this.resumeService = resumeService;
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/resume")
    public ResponseEntity<ResumeResponse> uploadResume(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam("file") MultipartFile file
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return ResponseEntity.ok(resumeService.upload(candidateService.findById(user.getId()), file));
    }

    @GetMapping("/resume")
    public List<ResumeResponse> listResumes(@AuthenticationPrincipal CustomUserDetails user) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        return resumeService.findByCandidateId(user.getId());
    }

    @DeleteMapping("/resume/{resumeId}")
    public ResponseEntity<Void> deleteResume(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long resumeId
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        resumeService.delete(resumeId, user.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resume/{resumeId}/download")
    public ResponseEntity<Resource> downloadResume(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable Long resumeId
    ) {
        CurrentUser.require(user, AccountType.CANDIDATE);
        Resource resource = resumeService.download(resumeId, user.getId());
        String contentType = "application/octet-stream";
        try {
            String probedContentType = Files.probeContentType(Path.of(resumeService.getResumeEntity(resumeId, user.getId()).getStoredPath()));
            if (probedContentType != null) {
                contentType = probedContentType;
            }
        } catch (IOException ex) {
            // ignore
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resumeService.getResumeEntity(resumeId, user.getId()).getActualName() + "\"")
                .body(resource);
    }
}
