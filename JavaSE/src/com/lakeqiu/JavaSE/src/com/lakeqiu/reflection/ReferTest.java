package com.lakeqiu.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射的用法
 * 通过API获取类的属性，方法，构造器
 * Created by lakeqiu
 */
public class ReferTest {
    public static void main(String[] args) {
        String path = "com.lakeqiu.reflection.RefExp";

        try {
            Class cl = Class.forName(path);

            // 获取类的名字
            System.out.println(cl.getName()); // 获得包名+类名
            System.out.println(cl.getSimpleName()); // 获得类名

            // 获取类的属性
//            Field[] fields = cl.getFields();  // 只能获取public的属性
            Field[] fields = cl.getDeclaredFields();  // 获取所有属性
            Field c = cl.getDeclaredField("sno");  // 获取对应属性
            System.out.println(c);
            for (Field field : fields) {
                System.out.println("属性-->" + field);
            }

            // 获取类的方法信息
            Method[] methods = cl.getDeclaredMethods(); // 获取所有方法
            Method met = cl.getDeclaredMethod("getSno", null);  // 表示无参数,好区分重载的方法
            System.out.println(met);
            Method meh = cl.getDeclaredMethod("setName", String.class);  // 表示参数的类型
            System.out.println(meh);
            for (Method method : methods) {
                System.out.println("方法-->" + method);
            }

            // 获取类的构造方法
            Constructor[] con = cl.getDeclaredConstructors(); // 获取所有构造方法
            Constructor co = cl.getDeclaredConstructor(String.class, String.class, int.class);
            System.out.println(co);
            for (Constructor constructor : con) {
                System.out.println("构造器-->" + constructor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
