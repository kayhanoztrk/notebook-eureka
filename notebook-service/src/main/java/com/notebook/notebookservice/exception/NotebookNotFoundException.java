package com.notebook.notebookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotebookNotFoundException extends RuntimeException {
    public NotebookNotFoundException(String message) {
        super(message);
    }

    public NotebookNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
