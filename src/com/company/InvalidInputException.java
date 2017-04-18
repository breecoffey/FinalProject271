package com.company;

/**
 * Created by breecoffey on 4/17/17.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {

    }
    public InvalidInputException(String message)
    {
            super(message);
    }
}

