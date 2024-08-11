package com.softserve.edu.sprint4.task3;

import java.util.*;

public class MyUtils {
    public boolean listMapCompare(List<String> list, Map<String, String> map) {

        for (String s : map.values()) {
            if (!list.contains(s)) {
                return false;
            }
        }
        return true;
    }

//    public boolean listMapCompare(List<String> list, Map<String, String> map) {
//        return map.values().stream().allMatch(list::contains);
//    }


}