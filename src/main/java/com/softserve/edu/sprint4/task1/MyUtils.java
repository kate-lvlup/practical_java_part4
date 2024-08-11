package com.softserve.edu.sprint4.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtils {
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        Map<String, List<String>> nameToPhonesMap = new HashMap<>();
        for (Map.Entry<String, String> entry : phones.entrySet()) {
            String phone = entry.getKey();
            String name = entry.getValue();
            List<String> phoneList = nameToPhonesMap.get(name);
            if (phoneList == null) {
                phoneList = new ArrayList<>();
                nameToPhonesMap.put(name, phoneList);
            }
            phoneList.add(phone);
        }
        return nameToPhonesMap;
    }
}