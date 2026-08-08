package com.sai.hirely.apis.resumes;


import com.sai.hirely.dto.file.FileResponse;
import com.sai.hirely.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/files/upload")
public class FileUploadApi {

    private final StorageService fileStorageService;
    private final Map<String,Object> schemaForLLM  = Map.of(
            "skills", List.of(
            Map.of(
            "skillName", "Java",
            "proficiency", "ADVANCED"
            )
            ),
            "experiences", List.of(
            Map.of(
            "roleName", "Software Engineer",
            "organizationName", "Google",
            "description", "Developed REST APIs using Spring Boot.",
            "companyName", "Google",
            "experienceInMonths", 24
            )
            )
            );
    private final StorageService storageService;

    public FileUploadApi(@Qualifier("localStorage") StorageService service, StorageService storageService) {
        this.fileStorageService = service;
        this.storageService = storageService;
    }

    // take the resume first when the user is trying to login. then process the resume meanwhile user is filling in the details
    @PostMapping(path = "/resume")
    public ResponseEntity<FileResponse> uploadResume(
            @RequestParam("file") MultipartFile file
            ) {
            // send resume to ai service to parse and extract skills and experiences.
            // and save them thru skill service and experience service
            String parsedText = storageService.extractTxt(file);
            String storedFileName = fileStorageService.storePdf(file);
//            aiService.postAfterExtraction(schema,parsedText) -> will post extracted schema types and all to frontend
            return ResponseEntity.ok(new FileResponse(
                    storedFileName,
                    file.getContentType(),
                    file.getSize()
            ));
    }
}