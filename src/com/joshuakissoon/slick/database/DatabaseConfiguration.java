package com.joshuakissoon.slick.database;

/**
 * Class containing all configurations for a database
 *
 * @author Joshua Kissoon
 * @since 20151031
 */
public class DatabaseConfiguration
{

    private String database;
    private String user;
    private String password;
    private String host;
    private Integer port;

    private Boolean printQueries = false;

    public DatabaseConfiguration(final String database, final String username,
            final String password, final String host)
    {
        this(database, username, password, host, 3306);
    }

    public DatabaseConfiguration(final String database, final String username,
            final String password, final String host, final Integer port)
    {
        this.setDatabase(database);
        this.setUser(username);
        this.setPassword(password);
        this.setHost(host);
        this.setPort(port);
    }

    public String getDatabase()
    {
        return database;
    }

    public final void setDatabase(final String database)
    {
        this.database = database;
    }

    public String getUser()
    {
        return user;
    }

    public final void setUser(final String username)
    {
        this.user = username;
    }

    public String getPassword()
    {
        return password;
    }

    public final void setPassword(final String password)
    {
        this.password = password;
    }

    public String getHost()
    {
        return host;
    }

    public final void setHost(final String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public final void setPort(final Integer port)
    {
        this.port = port;
    }

    public Boolean printQueries()
    {
        return printQueries;
    }

    public void setPrintQueries(Boolean printQueries)
    {
        this.printQueries = printQueries;
    }

}
