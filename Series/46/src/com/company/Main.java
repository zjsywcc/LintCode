package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,1,1,1,2,2,2};
        List<Integer> list = Arrays.asList(a);
        ArrayList<Integer> arrayList = new ArrayList<>(list);
	    System.out.println(majorityNumber(arrayList));
    }

    public static int majorityNumber(ArrayList<Integer> nums) {
        int x = 0, count = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(count == 0) {
                x = nums.get(i);
                count++;
            } else if(x != nums.get(i)) {
                count--;
            } else {
                count++;
            }
        }
        return x;
    }

    public static int majorityNumber(int[] nums) {
        int x = 0, count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(count == 0) {
                x = nums[i];
                count++;
            } else if(x != nums[i]) {
                count--;
            } else {
                count++;
            }
        }
        return x;
    }
}
