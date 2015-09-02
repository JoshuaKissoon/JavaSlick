package com.joshuakissoon.slick.database;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import com.joshuakissoon.slick.exception.InsufficientDetailsException;

/**
 * Interface governing the databases
 *
 * @author Joshua Kissoon
 * @since 20150902
 */
public interface Database
{

    /**
     * Test the connection to the database
     *
     * @return boolean Whether the connection is working or not
     *
     * @throws com.joshuakissoon.slick.exception.InsufficientDetailsException
     */
    public boolean testConnection() throws InsufficientDetailsException;

    /**
     * Create a connection to the database
     *
     * @return Boolean Whether the connection was successful or not
     *
     * @throws com.joshuakissoon.slick.exception.InsufficientDetailsException
     */
    public boolean connect() throws InsufficientDetailsException;

    /**
     * Query the instance1 and return a resultset in the form of a list
     *
     * @param stat the sql prepared statement to execute
     *
     * @return ArrayList<HashMap> The data returned by the query
     */
    public ArrayList<HashMap<String, Object>> select(final PreparedStatement stat);
}
