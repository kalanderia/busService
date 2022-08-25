package com.ldt.BusService.util;

public class test1{
    public static void main (String[] args){
        int[] a = {1,2,4,20,12,14};
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for(int i =0; i< a.length; i++)
        {
            if(a[i]>largest)
            {
                secondLargest= largest;
                largest = a[i];
            }
            if(a[i]>secondLargest && a[i]!= largest)
            {
                secondLargest = a[i];
            }
        }
        System.out.println(secondLargest);
    }
}
