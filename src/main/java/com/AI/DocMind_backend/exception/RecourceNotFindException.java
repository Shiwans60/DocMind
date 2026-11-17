package com.AI.DocMind_backend.exception;

public class RecourceNotFindException extends RuntimeException{
    public RecourceNotFindException(){
        super("Document Not Found");
    }
    public RecourceNotFindException(String message){
        super(message);
    }
    public RecourceNotFindException(String message,Throwable cause){

        super(message,cause);
    }
}
