package com.notebook.notebookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(ExceptionMessage message){
        this.exceptionMessage = message;
    }

    public NoteNotFoundException(String message, Throwable throwable) {
        super(message,throwable);
    }

    public ExceptionMessage getExceptionMessage(){
        return exceptionMessage;
    }
}