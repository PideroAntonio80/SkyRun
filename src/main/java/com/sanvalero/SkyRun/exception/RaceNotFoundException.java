package com.sanvalero.skyrun.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public class RaceNotFoundException extends RuntimeException{

    public RaceNotFoundException() {
        super();
    }

    public RaceNotFoundException(String message){
        super(message);
    }

    public RaceNotFoundException(long id){
        super("Race not found: " + id);
    }
}
