package com.lakeqiu.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多线程
 * 电影院购票
 * Created by lakeqiu
 */
public class Cinema {
    List<Integer> list;  // 剩余座位

    Cinema(){
        list = new ArrayList<>();
        List l = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.addAll(l);
    }

    Boolean sellticket(List seats){
        // 用于与seats相减
        List<Integer> copy = new ArrayList<>();
        copy.addAll(list);

        copy.removeAll(seats);
        if (copy.size() != list.size() - seats.size()){ // 是否用户所有的位置都买到了
            return false;
        }

        list = copy; // 用户买票成功，指向新的集合（里有最新剩余的票）

        return true;
    }

    void remian(){
        System.out.println("剩余的座位有：");
        for (int i: list){
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Cinema cs = new Cinema();
        acount as = new acount(cs);
        as.list.add(2);
        as.list.add(3);
        acount ad = new acount(cs);
        ad.list.add(3);
        ad.list.add(5);
        new Thread(as, "移").start();
        new Thread(ad, "与偶").start();

    }
}

/**
 * 账户
 */
class acount implements Runnable{
    List<Integer> list;
    Cinema cinema;

    acount(Cinema cinema){
        this.cinema = cinema;
        list = new ArrayList<>();
    }

    acount(){

    }

    @Override
    public void run() {
        synchronized (cinema){
            if (cinema.sellticket(list)){
                System.out.println(Thread.currentThread().getName() + "买票成功！买到如下座位：");
                for (int i: list){
                    System.out.print(i + ", ");
                }
                System.out.println();
                cinema.remian();
            }else {
                System.out.println(Thread.currentThread().getName() + "购票失败！！");
            }
        }
    }
}