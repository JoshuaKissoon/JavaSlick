package com.joshuakissoon.slick.rest;

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
    private HashMap<String, String> data;
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
        JsonObject jObject = parser.parse(this.jsonResponse).getAsJsonObject();

        System.out.println(this.jsonResponse);

        this.success = jObject.get("success").getAsBoolean();
        this.message = jObject.get("message").getAsString();
        JsonObject jDataObject = new JsonParser().parse(jObject.get("data").getAsString()).getAsJsonObject();
        this.data = gson.fromJson(jDataObject, new TypeToken<HashMap<String, String>>()
        {
        }.getType());

    }

    public HashMap<String, String> getData()
    {
        return this.data;
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
