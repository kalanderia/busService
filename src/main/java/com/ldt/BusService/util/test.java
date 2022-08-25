package com.ldt.BusService.util;

import java.util.Arrays;

public class test {
    public static void main(String[] args){
        int a[]={1,2,8,0,0,7,2,0,12,10,1,7,0,7,1,3,3,2,5,11,23,24};
//        int b[]=new int[100];
        int j=0;

        for(int i=0; i < a.length; i++) {

//            if(a[i] != 0 && a[i] % 2 != 0 && a[i] != a[i + 1])
            if (a[i] != 0){
                int temp=a[j];
                a[j]=a[i];
                a[i]=temp;
                j++;
//                b[i]=a[i];
//                System.out.println(b[i] + "");
            }
        }

        System.out.println(Arrays.toString(a));
    }
}

