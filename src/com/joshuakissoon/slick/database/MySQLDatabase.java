package com.joshuakissoon.slick.database;

import com.joshuakissoon.slick.exception.InsufficientDetailsException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Database Management Class
 *
 * @author Joshua Kissoon
 * @since 20151031
 */
public class MySQLDatabase implements Database
{

    private HikariConfig hikariConfig;
    private HikariDataSource hikariDataSource;

    private ResultSet resultSet = null;
    private Integer lastInsertId = null;
    private String lastErrorMsg;

    private final DatabaseConfiguration config;

    
    {
        this.lastErrorMsg = "";
    }

    public MySQLDatabase(final DatabaseConfiguration config, final Boolean connect)
    {
        this.config = config;

        if (!connect)
        {
            return;
        }

        try
        {
            this.connect();
        }
        catch (InsufficientDetailsException ex)
        {
            /* @todo - Handle this */
        }

    }

    @Override
    public boolean testConnection()
    {
        boolean ret;
        try
        {
            /* Setup the connection with the DB */
            String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/";
            Connection conn = DriverManager.getConnection(url, config.getUser(), config.getPassword());
            ret = true;
        }
        catch (SQLException e)
        {
            this.lastErrorMsg = e.getMessage();
            System.err.println("SQL Exception Occurred, msg: " + e.getMessage());
            ret = false;
        }

        return ret;
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return hikariDataSource.getConnection();
    }

    /**
     * Connect to the instance
     *
     * @return Boolean Whether the connection was successful or not
     *
     * @throws com.joshuakissoon.slick.exception.InsufficientDetailsException
     */
    @Override
    public final boolean connect() throws InsufficientDetailsException
    {
        /* Setup Hikari CP */
        hikariConfig = new HikariConfig();
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikariConfig.addDataSourceProperty("serverName", config.getHost());
        hikariConfig.addDataSourceProperty("port", config.getPort());
        hikariConfig.addDataSourceProperty("databaseName", config.getDatabase());
        hikariConfig.addDataSourceProperty("user", config.getUser());
        hikariConfig.addDataSourceProperty("password", config.getPassword());

        hikariDataSource = new HikariDataSource(hikariConfig);

        return true;
    }

    @Override
    public ArrayList<HashMap<String, Object>> select(final PreparedStatement stat, final String sql) throws SQLException
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        ArrayList<HashMap<String, Object>> results = new ArrayList();

        resultSet = stat.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int numColumns = md.getColumnCount();

        while (resultSet.next())
        {
            HashMap<String, Object> result = new HashMap(numColumns);
            for (int i = 1; i <= numColumns; ++i)
            {
                result.put(md.getColumnLabel(i), resultSet.getObject(i));
            }
            results.add(result);
        }
        stat.close();

        return results;
    }

    @Override
    public HashMap selectRow(final PreparedStatement stat, final String sql) throws SQLException
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        HashMap<String, Object> result = new HashMap();

        resultSet = stat.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int numColumns = md.getColumnCount();

        if (resultSet.next())
        {
            for (int i = 1; i <= numColumns; ++i)
            {
                result.put(md.getColumnLabel(i), resultSet.getObject(i));
            }
        }
        stat.close();

        return result;
    }

    @Override
    public int update(final PreparedStatement stat, final String sql) throws SQLException
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }
        int numRowsUpdated = stat.executeUpdate();
        return numRowsUpdated;
    }

    @Override
    public boolean insert(final PreparedStatement stat, final String sql) throws SQLException
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }
        int numRowsUpdated = stat.executeUpdate();

        ResultSet rs = stat.getGeneratedKeys();
        if (rs.next())
        {
            this.lastInsertId = rs.getInt(1);
        }

        return (numRowsUpdated > 0);
    }

    public int getLastInsertId()
    {
        return this.lastInsertId;
    }

}
