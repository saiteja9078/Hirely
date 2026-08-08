package com.sai.hirely.service.storage;

import com.sai.hirely.exceptions.file.InvalidFileException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public abstract class StorageService {
    protected final Tika tika = new Tika();

    @Transactional
    public void savePdf(MultipartFile file,Long candidateId) {
        storePdf(file);
        extractTxt(file);
    }
    public abstract String storePdf(MultipartFile file);
    public String extractTxt(MultipartFile file) {
        try(InputStream in = file.getInputStream()) {
            System.out.println("Text getting parse");
            return tika.parseToString(in);
        } catch (TikaException | IOException e) {
            throw new InvalidFileException("Failed to parse the file",e.getMessage());
        }
    }
}
