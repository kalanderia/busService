package com.ldt.BusService.util;

import java.security.Key;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class test3{
    public static void main (String[] args){

//        String str = "My name is Sahil";
//        String str1 = str.toLowerCase(Locale.ROOT);
//        int[] frequency = new int[str1.length()];
//        char string[] = str1.toCharArray();
//        for(int i = 0; i < str1.length(); i++){
//            frequency[i] = 1;
//            for(int j = i + 1; j < str1.length(); j++){
//                if(string[i] == string[j]){
//                    frequency[i]++;
//                    string[j] = '0';
//                }
//
//            }
//        }
//        for(int k = 0; k < frequency.length; k++){
//            if(string[k]!=' '&& string[k]!='0')
//            {
//                System.out.println(string[k]+" "+ frequency[k]);
//            }
//        }

        String str = "My name is Sahil";
        String str1 = str.replaceAll(" ","");
        String str2 = str1.toLowerCase(Locale.ROOT);

        Map<Character,Integer> map = new HashMap<>();
        char[] str3 = str2.toCharArray();

        for(char c: str3
            ){
            if(map.containsKey(c))
        {
            map.put(c, map.get(c)+1);
        }
        else
        {
            map.put(c,1);
        }
        }
        for(Map.Entry e: map.entrySet()){
            System.out.println(e.getKey()+"-"+ e.getValue());
        }
    }
}
