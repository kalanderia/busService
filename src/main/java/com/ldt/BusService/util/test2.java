package com.ldt.BusService.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class test2{
    public static void main (String[] args){
        int[] a = {0,1,23,11,43,12,10,12,15,12,26,11,13,22,25,26};
        Arrays.sort(a);
        int temp = 0;
//        for(int i = 0; i < a.length; i++){
//            for(int j = i + 1; j < a.length; j++){
//                if(a[i] > a[j]){
//                    temp = a[i];
//                    a[i] = a[j];
//                    a[j] = temp;
//                }
//            }
//        }
        for(int k =0; k<a.length; k++)
        {
            if( a.length-1!=k &&a[k]!=a[k+1] || a.length-1 == k)
            {
                System.out.println(a[k]);
            }
        }
    }
}
