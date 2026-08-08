package com.sai.hirely.service.storage;
import com.sai.hirely.exceptions.file.InvalidFileException;
import com.sai.hirely.repository.candidate.ResumeRepo;
import com.sai.hirely.service.valid.FileValidationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service("localStorage")
public class LocalStorageService extends StorageService{
    private final FileValidationService validationService;
    private final Path rootDir;
    private final ResumeRepo resumeRepo;
    public LocalStorageService(FileValidationService service,
                               @Value("${app.upload.resume.root-dir}") String rootDir, ResumeRepo resumeRepo) throws IOException {
        this.validationService = service;
        this.rootDir = Path.of(rootDir);
        Files.createDirectories(this.rootDir.resolve("resumes"));
        Files.createDirectories(this.rootDir.resolve("images"));
        this.resumeRepo = resumeRepo;
    }
    @Transactional
    public String storePdf(MultipartFile file) {
        validationService.validateResume(file);
        return storeInLocalStorage(file);
    }
    private String storeInLocalStorage(MultipartFile file) {
        String savedStr = UUID.randomUUID() +"."+ StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path path = rootDir.resolve("resumes").resolve(savedStr);
        try {
            file.transferTo(path);
            return path.toString();
        } catch (IOException e) {
            throw new InvalidFileException("Error writing file to disk",e.getMessage());
        }
    }
}
