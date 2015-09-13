package com.joshuakissoon.slick.api;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;

/**
 * Class with URL management functions
 *
 * @author Joshua Kissoon
 * @since 20150911
 */
public class URLManipulator
{

    public static URI getURI(String url)
    {
        return UriBuilder.fromUri(url).build();
    }
}
