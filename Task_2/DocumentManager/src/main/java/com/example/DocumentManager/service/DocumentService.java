package com.example.DocumentManager.service;

import com.example.DocumentManager.model.Document;
import com.example.DocumentManager.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFileSize(file.getSize());
        document.setUploadDate(LocalDateTime.now());
        document.setFileData(file.getBytes());

        return documentRepository.save(document);
    }

    public List<Document> listAllDocuments() {
        return documentRepository.findAll();
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }
}
