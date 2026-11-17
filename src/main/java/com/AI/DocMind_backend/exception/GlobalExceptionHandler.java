package com.AI.DocMind_backend.exception;


import com.AI.DocMind_backend.dto.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecourceNotFindException.class )
     public ResponseEntity<?> HandleNotFound(RecourceNotFindException ex){
         log.warn("Resource not found : {} " ,ex.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.builder()
                 .success(false)
                 .message(ex.getMessage())
                         .data(null)
                         .timestamp(LocalDateTime.now())
                 .build());

     }

     @ExceptionHandler(DocumentProcessingException.class)
     public ResponseEntity<APIResponse<Object>> HandleProcessingError(DocumentProcessingException ex){
         log.error("Document Processing Exception : {} " ,ex.getMessage());
         return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(
                 APIResponse.builder()
                         .success(false)
                 .message( "Failed to process document :"+ ex.getMessage())
                 .data(null)
                 .timestamp(LocalDateTime.now())
                 .build()
         );
     }
     @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<APIResponse<Object>> handleMaxSize(MaxUploadSizeExceededException ex){
         log.warn("Max Uploaded Size Exception : {} " ,ex.getMessage());
         return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(
                 APIResponse.builder()
                         .success(false)
                         .message("File size exceeds the maximum allowed size :"+ ex.getMessage() )
                         .data(null)
                         .timestamp(LocalDateTime.now())
                         .build()
         );
     }
     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationError(MethodArgumentNotValidException ex){
         Map<String,String> errors = new HashMap<>();
         for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
             errors.put(fieldError.getField(),fieldError.getDefaultMessage());
         }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                 APIResponse.<Map<String, String>>builder()
                         .success(false)
                         .message("Validation failed")
                         .data(errors)
                         .timestamp(LocalDateTime.now())
                         .build()

         );
     }
     @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
         log.warn("Illegal Argument Exception : {} " ,ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                 APIResponse.builder()
                         .success(false)
                         .message(ex.getMessage())
                         .data(null)
                         .timestamp(LocalDateTime.now())
                         .build()

         );
     }
     @ExceptionHandler(Exception.class)
     public ResponseEntity<? > handleException(Exception ex){
         log.warn("Exception : {} " ,ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                 APIResponse.builder()
                         .success(false)
                         .message( "An unexpected error occur red" + ex.getMessage())
                         .data(null)
                         .timestamp(LocalDateTime.now())
                         .build()
         );

     }

}
