package com.joshuakissoon.slick.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of an SQLite Database
 *
 * @author Joshua Kissoon
 * @since 20150902
 */
public class SQLiteDatabase
{

    private Connection conn;

    public SQLiteDatabase() throws ClassNotFoundException
    {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
    }

    public boolean testConnection()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean connect()
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:sqlite:ranchManager.db");
        }
        catch (SQLException ex)
        {
            /* @todo Setup a log here */
            return false;
        }

        return true;
    }

    public ArrayList<HashMap<String, Object>> select(PreparedStatement stat)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConnection()
    {
        return this.conn;
    }

}
