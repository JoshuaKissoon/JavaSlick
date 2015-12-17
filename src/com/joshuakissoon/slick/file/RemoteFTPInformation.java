package com.joshuakissoon.slick.file;

/**
 * Class with information to connect to a remote FTP Server
 *
 * @author Joshua Kissoon
 * @since 20151217
 */
public class RemoteFTPInformation
{

    private String host;
    private Integer port;
    private String username;
    private String password;

    public RemoteFTPInformation(final String host, final Integer port, final String username, final String password)
    {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
