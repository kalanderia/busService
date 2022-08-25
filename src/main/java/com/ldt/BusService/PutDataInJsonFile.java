package com.ldt.BusService;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class PutDataInJsonFile{
    public static void main (String[] args) throws IOException{

//        List<String> list = new ArrayList<>();
//        list.add("Available");
//        list.add("BlueColor");
//        JSONArray jsonArray = new JSONArray();
//        for(int i =0; i<list.size(); i++)
//        {
//            jsonArray.put(list.get(i));
//        }



        Map<String,Object> map = new HashMap<>();
        map.put("permanentAddress","Jammu");
        map.put("city","Kathua");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("pincode","184111");
        map1.put("Area","Jammu To Chandigarh");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("Village","Hiranagar");
        map2.put("School","Govt Higher Secondary");
        map1.put("VillageAddress",map2);

        List<Object> list1 = new ArrayList<>();
        list1.add(map1);
        JSONArray jsonArray1 = new JSONArray();
        for(Object s1 : list1)
        {
            jsonArray1.put(s1);
        }

//        List<Object> list = new ArrayList<>();
//        list.add(map);
//        list.add(list1);
//        JSONArray jsonArray = new JSONArray();
//        for(Object s : list){
//            jsonArray.put(s);
//        }





        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",5);
        jsonObject.put("name","Toyota");
        jsonObject.put("type","AC");
        jsonObject.put("BusReservationStatus","BOOKED");
        jsonObject.put("LocalDateTime",LocalDateTime.now());
        jsonObject.put("PermanentAddress",map);
        jsonObject.put("TemporaryAddress",jsonArray1);

        FileWriter file = new FileWriter("json\\test1.json",false);

        file.write(jsonObject.toString());
        file.close();
        System.out.println("Created");
    }

}
