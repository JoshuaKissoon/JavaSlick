package com.joshuakissoon.slick.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

/**
 * A class wrapper for the JsonResponse from the Server
 *
 * @author Joshua Kissoon
 * @since 20150912
 */
public class JsonResponse
{

    public final String jsonResponse;
    private final Gson gson;
    private final JsonParser parser;
    private JsonObject responseObject;
    private Boolean success;
    private String message;

    
    {
        parser = new JsonParser();
        gson = new Gson();
    }

    public JsonResponse(final String jsonResponse)
    {
        this.jsonResponse = jsonResponse;
        
        this.parseData();
    }

    /**
     * Parses the data assuming the returned json string has the following entries:
     * - status
     * - message
     * - data
     * - success
     */
    private void parseData()
    {
        responseObject = parser.parse(this.jsonResponse).getAsJsonObject();
        this.success = responseObject.get("success").getAsBoolean();
        this.message = responseObject.get("message").getAsString();

    }

    /**
     * Method to get the data returned by the API call
     *
     * @return HashMap<String, Object> The returned data
     *
     * @todo Log the exception
     */
    public HashMap<String, Object> getData()
    {
        HashMap<String, Object> data;
        try
        {
            data = gson.fromJson(responseObject.get("data"), new TypeToken<HashMap<String, Object>>()
            {
            }.getType());
        }
        catch (IllegalStateException ex)
        {
            data = new HashMap<>();
        }

        return data;
    }

    public Boolean isSuccessful()
    {
        return this.success;
    }

    public String getMessage()
    {
        return this.message;
    }

}
