package com.AI.DocMind_backend.Controller;


import com.AI.DocMind_backend.Service.DocumentMetadataService;
import com.AI.DocMind_backend.dto.APIResponse;
import com.AI.DocMind_backend.dto.DocumentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController()
@RequestMapping("/api/v1/documents")
@Tag(
        name = "Document Management",
        description = "Endpoints for uploading , listing and managing documents and their vector embeddings"

)
@RequiredArgsConstructor
public class DocumentController {
    private DocumentMetadataService documentService;




    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Upload and Index a document (PDF , CSV, DOCX, TEXT, MD,TEXT",
            description =  "This API is used to upload and Index the document"
            )
    public ResponseEntity<APIResponse<DocumentResponseDTO>> uploadDocument(@RequestParam("file") MultipartFile file){
        DocumentResponseDTO documentResponseDTO =  this.documentService.UploadNAdProcess(file);


        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<DocumentResponseDTO>builder().success(true).data(documentResponseDTO).timestamp(LocalDateTime.now()).message("Document uploaded and Indexed successfully").build()
        );
    }



}
