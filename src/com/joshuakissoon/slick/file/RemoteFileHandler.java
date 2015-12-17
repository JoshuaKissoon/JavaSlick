package com.joshuakissoon.slick.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Class that handles uploading & downloading files to & from a remote server
 *
 * @author Joshua Kissoon
 * @since 20141219
 */
public class RemoteFileHandler
{

    private final RemoteFTPInformation config;

    /* Files Information */
    private File localFile;
    private String remoteFilename;  // The file name to which to transfer the local file

    private final FTPClient ftpClient;

    
    {
        ftpClient = new FTPClient();
    }

    public RemoteFileHandler(final RemoteFTPInformation config) throws SocketException, IOException
    {
        this.config = config;

        this.connect();
    }

    private void connect() throws SocketException, IOException
    {
        ftpClient.connect(config.getHost(), config.getPort());
        ftpClient.login(config.getUsername(), config.getPassword());
        ftpClient.enterLocalPassiveMode();

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public void setLocalFile(final File file)
    {
        this.localFile = file;
    }

    public void setLocalFile(final String fileName)
    {
        this.setLocalFile(new File(fileName));
    }

    public void setRemoteFilename(final String filename)
    {
        this.remoteFilename = filename;
    }

    public boolean uploadFile() throws IOException
    {
        try (InputStream inputStream = new FileInputStream(localFile))
        {
            return ftpClient.storeFile(remoteFilename, inputStream);
        }
    }

    public boolean downloadFile() throws IOException
    {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFile)))
        {
            return ftpClient.retrieveFile(remoteFilename, outputStream);
        }
    }

    public boolean deleteFile() throws IOException
    {
        return ftpClient.deleteFile(remoteFilename);
    }
}
