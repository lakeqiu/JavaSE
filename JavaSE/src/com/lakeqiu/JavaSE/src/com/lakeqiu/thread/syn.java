package com.lakeqiu.thread;

/**
 * synchronized-->同步锁
 * 锁的是方法所属的对象的资源
 * 所以使用的时候要注意用对
 *
 * 不要锁数字--等，因为对象（地址）一直在变，锁不住，
 * 要锁对象（地址）不变的
 *
 * 范围太小-->锁不住
 * 范围太大-->性能效率低下
 * 锁的范围要把握好（不是指代码，指数据的安全性）
 *
 * 同步方法
 * 同步块
 * Created by lakeqiu
 */
public class syn {
    public static void main(String[] args) {
//        Web12306 st = new Web12306();
//
//        // 多个代理-->方便共享资源
//        new Thread(st, "学生").start();
//        new Thread(st, "上班族").start();
//        new Thread(st, "黄牛").start();

        Acount acount = new Acount();
        acount.money = 100;

        Rob rob = new Rob(acount, "you", 70);
        Rob rob1 = new Rob(acount, "I", 60);

        rob1.start();
        rob.start();
    }
}

/**
 * 同步锁加在正确的方法上
 */
class Web12306 implements Runnable{
    private int ticket = 15; // 票

    @Override
    public synchronized void run() {  // 加上同步锁，有效的解决了问题
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
}

class Acount{
    public int money; // 账户余额
}

/**
 * getmoney()方法是Rob类的，因此加锁加到Rob类上了
 * 要修改的资源却是Acount类的
 */
class Rob extends Thread{
    public Acount acount;
    public int y;

    Rob(Acount acount, String name, int y){
        super(name);
        this.acount = acount;
        this.y = y;
    }


    Rob(){}

    @Override
    public void run() {
        getmoney();
    }

//    public synchronized void getmoney(){ // 锁没加到正确的资源上
//        if (acount.money - y >= 0) {
//            try {
//                Thread.sleep(900);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            acount.money -= y;
//            System.out.print(this.getName() + "取" + y + "元");
//            System.out.println("    账户余额：" + acount.money);
//        }else {
//            System.out.println("余额不足！");
//        }
//    }

    void getmoney(){
        if (acount.money == 0){  // 提高性能，重要
            return;
        }
        synchronized (acount){ // 同步块，会先检查acount对象有没有加锁，有的话就等待
            if (acount.money - y >= 0) {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            acount.money -= y;
            System.out.print(this.getName() + "取" + y + "元");
            System.out.println("    账户余额：" + acount.money);
        }else {
            System.out.println("余额不足！");
        }
        }
    }
}
