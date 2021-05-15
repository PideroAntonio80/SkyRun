package com.sanvalero.skyrun.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public class RunnerNotFoundException extends RuntimeException{

    public RunnerNotFoundException() {
        super();
    }

    public RunnerNotFoundException(String message){
        super(message);
    }

    public RunnerNotFoundException(long id){
        super("Location not found: " + id);
    }
}
