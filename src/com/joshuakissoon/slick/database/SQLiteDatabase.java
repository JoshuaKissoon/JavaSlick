package com.joshuakissoon.slick.database;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of an SQLite Database
 *
 * @author Joshua Kissoon
 * @since 20150902
 */
public class SQLiteDatabase implements Database
{

    @Override
    public boolean testConnection()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean connect()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HashMap<String, Object>> select(PreparedStatement stat)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
