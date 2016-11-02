package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        if(n <= 3) {
            return arrayLists;
        }
        initArrayLists(arrayLists, n);
        
    }

    public void initArrayLists(ArrayList<ArrayList<String>> arrayLists, int n) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0; i < n; i++) {
            arrayList.add(".");
        }
        for(int i = 0; i < n; i++) {
            arrayLists.add(arrayList);
        }
    }
}
