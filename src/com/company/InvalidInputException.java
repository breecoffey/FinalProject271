package com.company;

/**
 * Created by breecoffey on 4/17/17.
 * Custom exception class, thrown when there is invalid input.
 */
public class InvalidInputException extends Exception {

    //Constructors
    /**
     * default constructor
     */
    public InvalidInputException() {

    }

    /**
     * Construct an exception sending the message (parameter) to the exception super .
     * @param message message from method that throws the exception.
     */
    public InvalidInputException(String message)
    {
            super(message);
    }
}

