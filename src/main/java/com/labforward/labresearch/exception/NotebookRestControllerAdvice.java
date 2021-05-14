package com.labforward.labresearch.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotebookRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmptyNotebookException.class)
    protected ResponseEntity<Object> handleEmptyNotebookException(EmptyNotebookException ex, WebRequest request) {

        return handleExceptionInternal(ex, "Notebook is Empty, Please add Notebook entries",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

