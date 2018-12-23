package com.lakeqiu.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射API动态地操作：构造器，方法，属性
 * 反射耗时（没跳过安全检查）是没反射的大概30倍
 * 跳过安全检查的话效率比没跳过提高4倍
 * Created by lakeqiu
 */
public class ReferAPI {
    public static void main(String[] args) {
        String path = "com.lakeqiu.reflection.RefExp";
        try {
            Class<RefExp> expClass = (Class<RefExp>) Class.forName(path);

            // 通过反射API调用构造方法，创建对象
            RefExp l1 = expClass.newInstance();   // 其实就是调用无参构造器
            System.out.println(l1);

            Constructor<RefExp> exp = expClass.getDeclaredConstructor(String.class, String.class, int.class);
            RefExp l2 = exp.newInstance("lake", "16", 100);
            System.out.println(l2.getName());   // 直接调用


            // 通过反射API调用普通方法
//            l1.setName("lake1"); // 直接调用，等于下面两句，但不够灵活
            Method method = expClass.getDeclaredMethod("setName", String.class); // 方法名，形参
            method.invoke(l1, "lake1");  // 对象名，实参
            System.out.println(l1.getName());


            // 通过反射API操作属性
            Field field = expClass.getDeclaredField("name"); // 获取属性名字
            field.setAccessible(true);  // 不用做安全检查，可以绕过权限
            field.set(l2, "lake2");  // 通过反射写属性,自由写，通过setAccessible方法已经绕过set方法了
            System.out.println(field.get(l2));  // 通过反射直接读取属性的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
