package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        printArrayLists(solveNQueens(4));
    }

    static ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        if(n <= 0) {
            return arrayLists;
        }
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = i;
        }
        helper(results, list, nums);
//        printLists(results);
        for(List<Integer> result : results) {
            arrayLists.add(initArrayList(result, n));
        }
        return arrayLists;
    }

    public static ArrayList<String> initArrayList(List<Integer> result, int n) {
        ArrayList<String> matrix = new ArrayList<String>();
        char[] base = new char[n];
        for(int i = 0; i < n; i++) {
            base[i] = '.';
        }
        for(int i : result) {
            base[i] = 'Q';
            matrix.add(new String(base));
            base[i] = '.';
        }
        return matrix;
    }

    public static void helper(ArrayList<List<Integer>> rst, ArrayList<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(list.size() != 0){
                if(list.contains(nums[i])) {
                    continue;
                }
                int crtX = list.size();
                int crtY = nums[i];
                boolean flag = false;
                for(int j = 0; j < list.size(); j++) {
                    if(j + list.get(j) == crtX + crtY || j - list.get(j) == crtX - crtY) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    continue;
                }
            }
            list.add(nums[i]);
            helper(rst, list, nums);
            list.remove(list.size() - 1);
        }
    }

    public static void printLists(ArrayList<List<Integer>> lists) {
        for(List<Integer> list : lists) {
            for(int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void printArrayLists(ArrayList<ArrayList<String>> arrayLists) {
        for(ArrayList<String> arrayList : arrayLists) {
            for(String str : arrayList) {
                System.out.println(str);
            }
            System.out.println();
        }
    }
}
