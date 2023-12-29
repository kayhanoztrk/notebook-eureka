package com.notebook.notebookservice.exception;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
public record ExceptionMessage (String timestamp,
                                int status,
                                String error,
                                String message,
                                String path){}
