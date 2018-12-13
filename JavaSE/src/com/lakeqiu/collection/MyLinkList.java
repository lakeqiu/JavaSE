package com.lakeqiu.collection;

import java.util.LinkedList;

/**
 * 自己写的LinkList底层
 * Created by lakeqiu
 */
public class MyLinkList<E> {
    private Node first;  // 第一个节点
    private Node last;  // 最后一个节点

    private int size;  // 长度

    public void add(E e){
        Node node = new Node(e);

        if(first == null){ // 是第一个节点时
            node.element = e;
            first = node;
            last = node;
        } { // 不是第一个节点时
            node.element = e;
            node.previous = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(E e, int index){
        Node node = new Node(e);
        Node temp = getNode(index);
        if (temp != null){
            Node up = temp.previous;
            if (index == 0){
                node.next = temp;
                temp.previous = node;
                first = node;
            }else if (index == size){
                add(e);
            }else {
                up.next = node;
                node.previous = up;
                temp.previous = node;
                node.next = temp;
            }
            size++;
        }
    }

    public E get(int index){
        if (index < 0 || index > size - 1){
            throw new RuntimeException("索引不合法");
        }

        Node temp = getNode(index);

        return temp!=null?(E) temp.element:null;

    }

    // 重点看
    public void remove(int index){
        if (index < 0 || index > size - 1){
            throw new RuntimeException("索引不合法");
        }

        Node temp = getNode(index);
        if (temp != null){
            if(temp.previous != null){ // 表示temp有前节点，不一定有后节点，所以只能将后节点赋给前节点
                temp.previous.next = temp.next;
            }

            if(temp.next != null){ // 表示temp有后节点，不一定有前节点，所以只能将前节点赋给后节点
                temp.next.previous = temp.previous;
            }

            if (index == 0){
                first = temp.next;
            }

            if (index == size-1){
                last = temp.previous;
            }

            size--;
        }

    }

    public Node getNode(int index){
        Node temp = null;
        if (index <= (size >> 1)){ // 当index小于size的一半时从头找，大于时从尾找，提高效率
            temp = first;
            for (int i = 0; i < index; i++){
                temp = temp.next;
            }
        }else {
            temp = last;
            for (int i = size - 1; i > index; i--){
                temp = temp.previous;
            }
        }
        return temp;
    }

    public int length(){
        return size;
    }

    @Override
    public String toString() {
        Node temp = first;

        StringBuffer sb = new StringBuffer("[");
        while (temp != null){
            sb.append(temp.element);
            temp = temp.next;
            if(temp == null){
                return sb.append("]").toString();
            }
            sb.append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLinkList<String> lt = new MyLinkList<>();
        lt.add("123");
        lt.add("234");
        lt.add("345");
//        System.out.println(lt.get(0));
        System.out.println(lt.toString());
//        lt.remove(1);
//        System.out.println(lt.toString());
//        lt.remove(0);
        lt.add("222", 3);
        System.out.println(lt.toString());
//        System.out.println(lt.length());
    }
}
