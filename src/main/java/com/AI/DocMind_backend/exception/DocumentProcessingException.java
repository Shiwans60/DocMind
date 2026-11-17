package com.AI.DocMind_backend.exception;

public class DocumentProcessingException extends RuntimeException{
    public DocumentProcessingException(String message){
        super(message);
    }
    public DocumentProcessingException(){
        super("Error in processing Document");
    }

    public DocumentProcessingException(String message,Throwable cause){
        super(message,cause);
    }
}
