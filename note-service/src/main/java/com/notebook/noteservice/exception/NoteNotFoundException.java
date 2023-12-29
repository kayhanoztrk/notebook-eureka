package com.notebook.noteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException{

    public NoteNotFoundException(String message){
        super(message);
    }

    public NoteNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
