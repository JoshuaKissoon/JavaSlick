package com.joshuakissoon.slick.exception;

/**
 * An exception stating that the necessary details required are not entered
 *
 * @author Joshua Kissoon
 * @since 20150701
 */
public class InsufficientDetailsException extends Exception
{

    public InsufficientDetailsException()
    {
        super();
    }

    public InsufficientDetailsException(final String message)
    {
        super(message);
    }
}
