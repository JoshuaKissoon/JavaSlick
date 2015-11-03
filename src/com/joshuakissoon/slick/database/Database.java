package com.joshuakissoon.slick.database;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import com.joshuakissoon.slick.exception.InsufficientDetailsException;
import java.sql.Connection;
import java.sql.SQLException;

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
     * Get the active connection to the database
     *
     * @return Connection The active db connection
     *
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException;

    /**
     * Query the instance1 and return a resultset in the form of a list
     *
     * @param stat the sql prepared statement to execute
     * @param sql
     *
     * @return ArrayList The data returned by the query
     *
     * @throws java.sql.SQLException
     */
    public ArrayList<HashMap<String, Object>> select(final PreparedStatement stat, final String sql) throws SQLException;

    /**
     * Query the instance and return a single row
     *
     * @param stat The SQL Statement
     * @param sql
     *
     * @return HashMap<String, Object> The row in the form of a hashmap
     *
     * @throws java.sql.SQLException
     */
    public HashMap<String, Object> selectRow(final PreparedStatement stat, final String sql) throws SQLException;

    /**
     * Runs an update query
     *
     * @param stat The update statement to execute
     * @param sql
     *
     * @return The number of rows updated
     *
     * @throws java.sql.SQLException
     */
    public int update(final PreparedStatement stat, final String sql) throws SQLException;

    /**
     * Query to insert a new row into the database
     *
     *
     * @param stat
     * @param sql
     *
     * @return Whether the insertions was successful
     *
     * @throws java.sql.SQLException
     */
    public boolean insert(final PreparedStatement stat, final String sql) throws SQLException;
}
