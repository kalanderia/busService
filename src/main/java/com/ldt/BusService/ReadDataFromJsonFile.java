package com.ldt.BusService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class ReadDataFromJsonFile{
    public static void main (String[] args) throws IOException{

        FileReader reader = new FileReader("C:\\Users\\Kalanderia\\OneDrive\\Desktop\\backend\\busservice\\Json\\test1.json");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String jsonValues = bufferedReader.readLine();
        JSONObject jsonObject = new JSONObject(jsonValues);
        getKey(jsonObject,"Village");

    }

    public static void parseObject(JSONObject json, String key)
    {
        System.out.println(json.get(key));
    }

    public static void getKey(JSONObject json, String key)
    {
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if(!exists)
        {
            keys=json.keys();
            while(keys.hasNext())
            {
                nextKeys=(String) keys.next();

                try {
                    if(json.get(nextKeys) instanceof JSONObject)
                    {
                        if(exists== false)
                        {
                            getKey(json.getJSONObject(nextKeys),key);
                        }
                    }else if(json.get(nextKeys) instanceof JSONArray)
                    {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for(int i =0; i< jsonArray.length(); i++)
                        {
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerJson = new JSONObject(jsonArrayString);
                            if(exists == false)
                            {
                                getKey(innerJson, key);
                            }
                        }
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }else
        {
            parseObject(json, key);
        }
    }
}
