package com.labforward.labresearch.exception;


public class EmptyNotebookException extends RuntimeException{

    public EmptyNotebookException(){
        super();
    }

    public EmptyNotebookException(String message) {
        super(message);
    }
}
