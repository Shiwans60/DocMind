package com.AI.DocMind_backend.dto;

import com.AI.DocMind_backend.entity.DocumentStatus;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentResponseDTO {
    private UUID id;
    private   String fileName;
    private  Long fileSize;
    private DocumentStatus status;
    private  Integer chunksCreated;
    private  String message;
    private Long userId;
}
