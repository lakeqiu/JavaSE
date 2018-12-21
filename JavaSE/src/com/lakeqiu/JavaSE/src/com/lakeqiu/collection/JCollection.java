package com.lakeqiu.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java自己的集合
 * Created by lakeqiu
 */
public class JCollection {
    public static void main(String[] args) {
        // ArrayList用数组实现的
        Collection<String> mc = new ArrayList<>();

        // 求长度
        System.out.println(mc.size());

        ((ArrayList<String>) mc).add("lake");
        ((ArrayList<String>) mc).add("qiu");
        System.out.println(mc.size());

        // 获取
        System.out.println(((ArrayList<String>) mc).get(0));

        // 移除，注意，此处只是删除掉集合里存放对象的地址，对象并没有被删除
        mc.remove("lake");

        // 判断对象是否在集合里面
        System.out.println( mc.contains("qiu"));

        // 清空集合
        mc.clear();
        System.out.println(mc.size());
    }
}
