package com.lakeqiu.thread;

/**
 * yield 礼让 静态方法
 * 暂停线程进入就绪状态（注意不是阻塞状态）
 * Created by lakeqiu
 */
public class YieldThread {
    public static void main(String[] args) {
        new Thread(new yie(),"A").start();
        new Thread(new yie(),"B").start();
    }
}

class yie implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "-->end");
    }
}
