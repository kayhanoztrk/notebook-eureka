package com.notebook.notebookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotebookNotFoundException.class})
    public ResponseEntity<Object> handleNotebookNotFoundException(NotebookNotFoundException e) {
        NotebookException notebookException = new NotebookException(
                e.getMessage(),
                e,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(notebookException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoteNotFoundException.class})
    public ResponseEntity<ExceptionMessage> handleNoteNotFoundException(NoteNotFoundException e){
        return new ResponseEntity<>(e.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }
}
