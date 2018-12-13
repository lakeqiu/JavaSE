package com.lakeqiu.collection;

/**
 * 自己写集合测试类 + 泛型
 * Created by lakeqiu
 */
public class MyCollection {
    public static void main(String[] args) {
        MCollection<String> m = new MCollection<>();
        m.set("lake");
        m.set("qiu");
        System.out.println(m.get(0));
        System.out.println(m.get(1));
    }
}

/**
 * 泛型
 * 可以将E看成一种类型参数
 * @param <E>
 */
class MCollection<E>{
    Object obj[] = new Object[10];
    private int index = 0;

    void set(E e){
        obj[index] = e;
        index++;
    }

    E get(int index){
        return (E) obj[index];
    }
}