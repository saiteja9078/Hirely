package com.sai.hirely.dto.file;


public record FileResponse(
        String fileName,
        String contentType,
        long sizeInBytes
) {

}
