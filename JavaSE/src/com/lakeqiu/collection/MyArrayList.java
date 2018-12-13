package com.lakeqiu.collection;

import java.util.Arrays;

/**
 * 自己写的ArrayList底层实现
 * Created by lakeqiu
 */
public class MyArrayList<E> {
    // 集合默认容量
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;

    private int size;

    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity){
        elementData = new Object[capacity];
    }

    public void add(E element){
        // 集合达到上限，扩容
        if(size == elementData.length){
            Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
        elementData[size++] = element;
    }

    public E get(int index){
        if (index < 0 || index > size-1){
            throw new RuntimeException("索引不合法");
        }
        return (E) elementData[index];
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append('[');
        for(int i = 0; i < size; i++){
            b.append(elementData[i]);
            if (i == size - 1){
                return b.append("]").toString();
            }
            b.append(", ");
        }
        return b.toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> m = new MyArrayList<>();
        for (int i = 0; i < 300; i++){
            m.add("lake" + i);
        }
        m.get(200);
        System.out.println(m.toString());
    }
}
