package com.lakeqiu.collection;

import java.util.*;

/**
 * 迭代器测试list，set，map
 * Created by lakeqiu
 */
public class TestIterator {
    public static void main(String[] args) {
        IteratorList();
        IteratorSet();
        IteratorMap();
    }

    private static void IteratorList(){
        List<String> list = new ArrayList<>();

        list.add("lake");
        list.add("qiu");
        list.add("wind");

        for (Iterator<String> iter = list.iterator(); iter.hasNext();){ // list.iterator返回迭代器对象
            System.out.println(iter.next());
        }
    }

    private static void IteratorSet(){
        Set<String> set = new HashSet<>();

        set.add("lake");
        set.add("qiu");
        set.add("water");

        for (Iterator<String> iter = set.iterator(); iter.hasNext();){ // list.iterator返回迭代器对象
            System.out.println(iter.next());
        }
    }

    private static void IteratorMap(){
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "lake");
        map.put(9, "qiu");
        map.put(0, "flare");

        Set<Map.Entry<Integer, String>> s = map.entrySet();  // 转为set对象

        for (Iterator<Map.Entry<Integer, String>> iter = s.iterator(); iter.hasNext();){
            Map.Entry<Integer, String> temp = iter.next();
            System.out.println(temp.getKey() + "-->" + temp.getValue());
        }

        //  第二种方法
        // 先获取key集的迭代器，再通过get方法获取value
        Set<Integer> setkey = map.keySet();
        for (Iterator<Integer> iterator = setkey.iterator(); iterator.hasNext();){
            Integer key = iterator.next();
            System.out.println(key + "-->" + map.get(key));
        }

        // 不用迭代器遍历
        Set<Integer> sk = map.keySet();
        for (Integer st: sk){
            System.out.println(st + "-->" + map.get(st));
        }
    }
}
