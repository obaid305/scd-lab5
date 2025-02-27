package com.example.DocumentManager.dto;

import java.time.LocalDateTime;

public class DocumentDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadDate;

    public DocumentDTO(Long id, String fileName, String fileType, long fileSize, LocalDateTime uploadDate) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }
}
