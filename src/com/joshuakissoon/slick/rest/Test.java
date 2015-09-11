package com.joshuakissoon.slick.rest;

import java.util.HashMap;

/**
 *
 * @author Joshua
 * @since
 */
public class Test
{
    
    public static void main(String[] args)
    {
        HashMap<String, String> values = new HashMap<>();
        values.put("userId", "admin");
        values.put("password", "Pass1233~");
        String jsonResponse = RESTful.POST("http://localhost/codeli/?urlq=user/login", values);
        System.out.println(jsonResponse);

    }

}
