package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    String[] strings = new String[]{"lint", "intl", "inlt", "code"};
	    String[] strings1 = new String[]{"ab", "ba", "cd", "dc", "e"};
        List<String> list = anagrams(strings1);
        for(String str : list) {
            System.out.print(str + " ");
        }
    }

    public static class Anagram {
        String str;
        Boolean flag;

        public Anagram(String str, Boolean flag) {
            this.str = str;
            this.flag = flag;
        }
    }
    public static List<String> anagrams(String[] strs) {
        HashMap<String, Anagram> hashMap = new HashMap<String, Anagram>();
        List<String> list = new ArrayList<String>();
        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            Anagram anagram = hashMap.get(sortedStr);
            if(anagram != null) {
                if(anagram.flag) {
                    list.add(anagram.str);
                    anagram.flag = false;
                }
                list.add(str);
            } else {
                hashMap.put(sortedStr, new Anagram(str, true));
            }
        }
        return list;
    }
}
