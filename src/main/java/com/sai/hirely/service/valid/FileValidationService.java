package com.sai.hirely.service.valid;

import com.sai.hirely.exceptions.file.EmptyFileException;
import com.sai.hirely.exceptions.file.InvalidFileException;
import com.sai.hirely.exceptions.file.PayloadTooLargeException;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;


@Component
public class FileValidationService {
    private final DataSize MAX_RESUME_SIZE;
    private final DataSize MAX_IMAGE_SIZE;
    private final Set<String> resumeFileTypes;
    private final Set<String> imageFileTypes;

    public FileValidationService(@Value("${app.upload.resume.max-file-size}") DataSize maxResumeSize,
                                 @Value("${app.upload.image.max-file-size}") DataSize maxImgSize
    )  {
        this.MAX_RESUME_SIZE = maxResumeSize;
        this.MAX_IMAGE_SIZE = maxImgSize;
        this.resumeFileTypes = Set.of("application/pdf","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        this.imageFileTypes = Set.of(
                "image/png"
                ,"image/jpeg"
                ,"image/webp"
        );
    }
    public void validateResume(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            throw new EmptyFileException("File is empty");
        }
        if(file.getSize() > MAX_RESUME_SIZE.toBytes()) {
            throw new PayloadTooLargeException("File size limit is only: " + MAX_RESUME_SIZE);
        }
        String contentType = file.getContentType();
        if(contentType==null || !resumeFileTypes.contains(contentType)) {
            throw new InvalidFileException("Supports only pdf and docx");
        }
        String originalFileName = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename()) // throws null pointer exception
        );
        if(originalFileName.contains("..")){
            throw new InvalidFileException("Invalid path sequence in file name");
        }
        validateActualFileType(file,file.getContentType());
    }
    private void validateActualFileType(MultipartFile file,String contentType) {
        try {
            // Checks first few bits in order to verify the file type.
            Tika tika = new Tika();
            String detected = tika.detect(file.getInputStream());
            System.out.println(detected);
            if(detected == null || !resumeFileTypes.contains(detected)) {
                throw new InvalidFileException("File type not supported " + detected);
            }
        } catch (IOException e) {
            throw new InvalidFileException("Cannot verify file content");
        }
    }
}
