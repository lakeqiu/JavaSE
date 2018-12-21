package com.lakeqiu.thread;

/**
 * 模仿抢票
 * 一份资源，多个代理
 * 当模拟网络延迟后，会出现并发问题，在ticket值上
 * Created by lakeqiu
 */
public class TestWeb12306 implements Runnable {
    private int ticket = 15; // 票

    @Override
    public void run() {
        while (true){
            try { // 模拟网络延迟，会出现并发问题
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ticket < 0){
                System.out.println(Thread.currentThread().getName() + ":没有票了！");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticket--);
        }
    }

    public static void main(String[] args) {
        // 一份资源
        TestWeb12306 st = new TestWeb12306();

        // 多个代理-->方便共享资源
        new Thread(st, "学生").start();
        new Thread(st, "上班族").start();
        new Thread(st, "黄牛").start();
    }
}
