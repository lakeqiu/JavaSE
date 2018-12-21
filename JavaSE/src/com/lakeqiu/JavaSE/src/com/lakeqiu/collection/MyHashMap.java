package com.lakeqiu.collection;

import java.util.Arrays;

/**
 * 自定义HashMap
 * Created by lakeqiu
 */
public class MyHashMap<K,V> {
    HashNode[] table; // 位桶数组
    int size; // 存放多少元素

    MyHashMap(){
        table = new HashNode[16]; // 一般定义为2的整数次幂
    }

    /**
     * @param v hashCode计算出的值
     * @param lenght  数组长度
     * @return 应该存放在哪个位置
     */
    private int myhash(int v, int lenght){
        return v&(lenght - 1); // 效率比v%lenght高
    }

    private void put(K key, V values){
        // 创建新的节点对象
        HashNode node = new HashNode();
        node.key = key;
        node.values = values;
        node.hash = myhash(key.hashCode(), table.length-1);
        node.next = null;

        HashNode temp = table[node.hash];
        HashNode iterlast = null; // 正在遍历的最后一个元素
        boolean KeyRepeat = false; // 记录是否发生hash重复，默认为否

        // 存放进位桶数组
        if (temp == null){ // 数组当前位置未存放元素
            table[node.hash] = node;
            size++;
        }else { // 数组当前位置已经存在元素，则遍历链表
            while (temp != null){
                if(temp.key.equals(key)){ // 如果key相等，则覆盖
                    KeyRepeat = true;
                    temp.values = values; // 只改values，其他值不变
                    break;
                }else { // key不相等，则遍历下一个
                    iterlast = temp;
                    temp =  temp.next;
                }
            }
            if(!KeyRepeat){ // 没有发生hash重复的话
                iterlast.next = node;
                size++;
            }
        }
    }

    /**
     * 对比hash，找到对应位置，再遍历列表
     * @param key 要找的值对应的键
     * @return values|null
     */
    private K get(K key){
        int hash = myhash(key.hashCode(), table.length-1);
        K value = null;

        if (table[hash] != null){ // 对应位置有元素
            HashNode temp = table[hash];
            while (temp != null){
                if (temp.key.equals(key)){ // 找到对应的值了
                    value = (K) temp.values;
                    break;
                }else { // 没找到，继续遍历
                    temp = (HashNode)temp.next;
                }
            }
        }

        return value;
    }

    private int length(){
        return size;
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer("{");

        for (HashNode temp: table){ // 遍历位桶数组
//            HashNode temp = table[i];

            while (temp != null){ // 遍历列表
                b.append(temp.key + ": " + temp.values + ", ");
                temp = temp.next;
            }
        }
        b.setCharAt(b.length()-2, '}'); // ,后有空格，所以是减2
        return b.toString();
    }


    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("lake", "qiu");
        map.put("sun", "moon");
        map.put("river", "water");
        map.put("flare", "wind");
        System.out.println(map.get("lake"));
        System.out.println(map);
        System.out.println(map.length());

    }
}
