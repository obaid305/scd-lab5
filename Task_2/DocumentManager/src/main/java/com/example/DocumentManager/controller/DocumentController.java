package com.example.DocumentManager.controller;

import com.example.DocumentManager.model.Document;
import com.example.DocumentManager.service.DocumentService;
import com.example.DocumentManager.dto.DocumentDTO; // Create this DTO class
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<DocumentDTO> uploadDocument(@RequestParam("file") MultipartFile file) {
        try {
            Document document = documentService.uploadDocument(file);
            DocumentDTO documentDTO = new DocumentDTO(
                    document.getId(),
                    document.getFileName(),
                    document.getFileType(),
                    document.getFileSize(),
                    document.getUploadDate()
            );
            return ResponseEntity.ok(documentDTO);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> listAllDocuments() {
        List<DocumentDTO> documentDTOs = documentService.listAllDocuments().stream()
                .map(doc -> new DocumentDTO(doc.getId(), doc.getFileName(), doc.getFileType(), doc.getFileSize(), doc.getUploadDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(documentDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Document deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        Optional<Document> documentOptional = documentService.getDocumentById(id);

        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                    .body(document.getFileData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
