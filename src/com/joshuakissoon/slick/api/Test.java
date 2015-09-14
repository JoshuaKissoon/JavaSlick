package com.joshuakissoon.slick.api;

import com.joshuakissoon.slick.api.rest.RESTful;
import com.google.gson.Gson;
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
    private RESTful restful;

    
    {
        gson = new Gson();
        restful = new RESTful();
    }

    public Test()
    {

    }

    public void loginUser()
    {
        HashMap<String, Object> values = new HashMap<>();
        values.put("userId", "admin");
        values.put("password", "Pass1233~");

        JsonResponse jsonResponse = restful.POST("http://localhost/codeli/?urlq=user/login", values);

        token = (String) jsonResponse.getData().get("accessToken");
        restful.setAccessToken(token);
    }

    public void testExampleModule()
    {
        HashMap<String, Object> values = new HashMap<>();
        values.put("someval", "someval");
        JsonResponse jsonResponse = restful.POST("http://localhost/codeli/?urlq=example/numbers", values);
        System.out.println(jsonResponse.getData());
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
