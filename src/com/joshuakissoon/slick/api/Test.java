package com.joshuakissoon.slick.api;

import com.joshuakissoon.slick.api.rest.RESTful;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

/**
 * A class to test the Restful functionality
 *
 * This class uses a basic install of codeli (https://github.com/JoshuaKissoon/codeli) to test everything.
 *
 * @author Joshua Kissoon
 * @since 20150911
 */
public class Test
{

    private final Gson gson;

    private String token;

    
    {
        gson = new Gson();
    }

    public Test()
    {

    }

    public void loginUser()
    {
        HashMap<String, String> values = new HashMap<>();
        values.put("userId", "admin");
        values.put("password", "Pass1233~");
        JsonResponse jsonResponse = RESTful.POST("http://localhost/codeli/?urlq=user/login", values);
       

        token = jsonResponse.getData().get("token");
        System.out.println(token);
    }

    public void testExampleModule()
    {
//        HashMap<String, String> values = new HashMap<>();
//        values.put("someval", "someval");
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("AuthorizationToken", token);
//        String jsonResponse = RESTful.POST("http://localhost/codeli/?urlq=example/numbers", values, headers);
//        System.out.println(jsonResponse);
    }

    public static void main(String[] args)
    {
        Test t = new Test();
        System.out.println("Logging In User");
        t.loginUser();
        System.out.println("Getting the example numbers. Has permission required. \n\n");
        t.testExampleModule();
    }

}
