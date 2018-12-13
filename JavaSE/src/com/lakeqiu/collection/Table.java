package com.lakeqiu.collection;

import java.util.*;

/**
 * 使用集合存储表
 * 行：list，array，map都可以，此处用map
 * 表：list，array...都可以。、，此处用list
 * 记住，只要是容器，都可以存储，看你选择而已
 * Created by lakeqiu
 */
public class Table {
    public static void main(String[] args) {

        TestTable();
    }

    private static void TestTable(){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("姓名", "lake");
        map1.put("id", 1000);
        map1.put("power", "wind");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("姓名", "qiu");
        map2.put("id", 1001);
        map2.put("power", "water");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        for (Map<String, Object> map: list){
            Set<String> set = map.keySet();
            for (String st: set){
                System.out.print(st + ": " + map.get(st) + "\t");
            }
            System.out.println();
        }
    }
}
