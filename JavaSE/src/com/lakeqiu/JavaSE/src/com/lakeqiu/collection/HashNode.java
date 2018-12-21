package com.lakeqiu.collection;

/**
 * Created by lakeqiu
 */
public class HashNode<K,V> {
    int hash; // hash值
    K key;
    V values;
    HashNode next; // 存放下一个节点
}
