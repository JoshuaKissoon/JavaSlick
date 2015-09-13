package com.joshuakissoon.slick.rest;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

/**
 * Class to handle restful operations
 *
 * @author Joshua Kissoon
 * @since 20150911
 */
public class RESTful
{

    public static JsonResponse GET(final String url)
    {
        return RESTful.GET(url, new HashMap<>());
    }

    /**
     * Method to perform a HTTP GET request
     *
     * @param url     The URL which to call the request from
     * @param headers
     *
     * @return String The output of the request
     */
    public static JsonResponse GET(final String url, final HashMap<String, String> headers)
    {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(url);

        // Get JSON
        return new JsonResponse(target.request().accept(MediaType.APPLICATION_JSON).get(String.class));
    }

    public static JsonResponse POST(final String url, final HashMap<String, String> params)
    {
        return RESTful.POST(url, params, new HashMap<>());
    }

    /**
     * Method to perform a HTTP POST request
     *
     * @param url     The URL which to call the request from
     * @param params  The parameters to send in the request
     * @param headers
     *
     * @return String The output of the request
     */
    public static JsonResponse POST(final String url, final HashMap<String, String> params, final HashMap<String, String> headers)
    {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(url);

        String data = new Gson().toJson(params);

        Entity json = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);

        for (String key : headers.keySet())
        {
            builder.header(key, headers.get(key));
        }

        String jsonResponse = builder.post(json, String.class);

        return new JsonResponse(jsonResponse);
    }

    /**
     * Method to perform a HTTP PUT request
     *
     * @param url    The URL which to call the request from
     * @param params The parameters to send in the request
     *
     * @return String The output of the request
     */
    public static JsonResponse PUT(final String url, final HashMap<String, String> params)
    {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(url);

        String data = new Gson().toJson(params);

        Entity json = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
        return new JsonResponse(builder.put(json, String.class));
    }

    /**
     * Method to perform a HTTP PUT request
     *
     * @param url The URL which to call the request from
     *
     * @return String The output of the request
     */
    public static JsonResponse DELETE(final String url)
    {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(url);

        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
        return new JsonResponse(builder.delete(String.class));
    }

}
