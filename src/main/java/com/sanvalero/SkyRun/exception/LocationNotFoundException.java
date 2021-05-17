package com.sanvalero.skyrun.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 15/05/2021
 */

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException() {
        super();
    }

    public LocationNotFoundException(String message){
        super(message);
    }

    public LocationNotFoundException(long id){
        super("Location not found: " + id);
    }
}
