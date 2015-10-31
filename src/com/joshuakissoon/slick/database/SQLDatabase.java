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
public class SQLDatabase
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

    public SQLDatabase(final DatabaseConfiguration config, final Boolean connect)
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

    public Connection getHikariConnection() throws SQLException
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
    public final boolean connect() throws InsufficientDetailsException
    {
        if (!this.hasAuthenticationDetails())
        {
            throw new InsufficientDetailsException("Please enter all of the necessary database connection details");
        }

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

    /**
     * Checks whether the necessary authentication details have been entered to do a proper instance connection
     *
     * @return Boolean Whether the details are available
     */
    private boolean hasAuthenticationDetails()
    {
        return !(config.getDatabase().trim().equals("")
                || config.getUser().trim().equals("")
                || config.getPassword().trim().equals("")
                || config.getHost().trim().equals(""));
    }

    public ArrayList<HashMap<String, Object>> select(final PreparedStatement stat, final String sql)
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        return select(stat);
    }

    /**
     * Query the instance and return a resultset in the form of a list
     *
     * @param stat the sql prepared statement to execute
     *
     * @return ArrayList<HashMap> The data returned by the query
     */
    public ArrayList<HashMap<String, Object>> select(final PreparedStatement stat)
    {
        long startTime = System.currentTimeMillis();
        ArrayList<HashMap<String, Object>> results = new ArrayList();

        try
        {
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
        }
        catch (SQLException ex)
        {
            System.err.println("Database.select() - An error was encountered when doing the select. Msg: " + ex.getMessage() + "\nSQL: " + stat.toString());
        }
        long endTime = System.currentTimeMillis();

        if (config.printQueries())
        {
            System.out.println("Database Query time: " + (endTime - startTime));
        }

        return results;
    }

    public HashMap selectRow(final PreparedStatement stat, final String sql)
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        return selectRow(stat);
    }

    /**
     * Query the instance and return a single row
     *
     * @param stat
     *
     * @return
     */
    public HashMap selectRow(final PreparedStatement stat)
    {
        long startTime = System.currentTimeMillis();
        HashMap<String, Object> result = new HashMap();

        try
        {
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
        }
        catch (SQLException ex)
        {
            System.err.println("Database.selectRow() - An error occurred. Msg: " + ex.getMessage());
        }
        long endTime = System.currentTimeMillis();

        if (config.printQueries())
        {
            System.out.println("Database Query time: " + (endTime - startTime));
        }

        return result;
    }

    public int update(final PreparedStatement stat)
    {
        int numRowsUpdated = 0;
        try
        {
            numRowsUpdated = stat.executeUpdate();
        }
        catch (Exception ex)
        {
            // @todo 
        }
        return numRowsUpdated;
    }

    public int update(final PreparedStatement stat, final String sql)
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        return update(stat);
    }

    public boolean insert(final PreparedStatement stat)
    {
        int numRowsUpdated = 0;
        try
        {
            numRowsUpdated = stat.executeUpdate();
            try
            {
                ResultSet rs = stat.getGeneratedKeys();
                if (rs.next())
                {
                    this.lastInsertId = rs.getInt(1);
                }
            }
            catch (Exception ex)
            {
                /* An exception will be thrown if we did not request generated keys... */
            }
        }
        catch (Exception ex)
        {
            // @todo 
        }
        return (numRowsUpdated > 0);
    }

    public boolean insert(final PreparedStatement stat, final String sql)
    {
        if (config.printQueries())
        {
            System.out.println("\nQuery: " + sql);
        }

        return insert(stat);
    }

    public int getLastInsertId()
    {
        return this.lastInsertId;
    }

    public void closeResources()
    {
        /* Close the instance connection and all of the statements */
//        try
//        {
//            if (this.preparedStatement != null)
//            {
//                this.preparedStatement.close();
//            }
//            if (this.statement != null)
//            {
//                this.statement.close();
//            }
//            if (this.resultSet != null)
//            {
//                this.resultSet.close();
//            }
//            if (this.conn != null)
//            {
//                this.conn.close();
//            }
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
    }

    public String getLastErrorMessage()
    {
        return this.lastErrorMsg;
    }
}
