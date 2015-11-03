package com.joshuakissoon.slick.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Class that manages system logs
 *
 * @author Joshua Kissoon
 * @since 20151031
 */
public class LogsManager
{

    private final String filename;

    /**
     * @param filename The name of the file where logs are to be written
     */
    public LogsManager(final String filename)
    {
        this.filename = filename;
    }

    /**
     * Take in an Throwable (exception superclass) and write it's stack trace to log
     *
     * @param e The throwable
     */
    public void writeMessage(final Throwable e)
    {
        /* Get the exception's stack trace in a string format */
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        this.writeMessage(errors.toString());
    }

    /**
     * Writes a given message to the log file
     *
     * @param message The message to write
     */
    public void writeMessage(final String message)
    {
        try (Writer output = new BufferedWriter(new FileWriter(this.filename, true)))
        {
            /* Write the message to the log file */
            output.append(message + "\n");

            System.err.println(message);
        }
        catch (IOException ex)
        {
            System.err.println("An error occured when trying to write to the log file. Msg: " + ex.getMessage());
        }
    }
}
