package com.lakeqiu.thread;

/**
 * join 合并线程，插队线程
 * 使用此方法的线程插队，当前线程会等到插队线程执行完才执行
 * 有参数，为插队时间
 * Created by lakeqiu
 */
public class joinThread {
    public static void main(String[] args) {
        new Thread(new Buyer()).start();
    }
}

class Buyer implements Runnable{
    @Override
    public void run() {
        System.out.println("网购买了东西！");
        System.out.println("卖家发货了！");
        System.out.println("等待快递中！");
        Express es = new Express();  // 如果es是实现接口，则实现不了等待
        Thread t = new Thread(es);
        t.start();
        try {
            t.join(6000);  // 插队6秒，6秒后退出插队状态
            System.out.println("拿到了快递！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Express implements Runnable{
    @Override
    public void run() {
        System.out.println("快递开始运输了！");
        for (int i = 1; i < 5; i++) {
            System.out.println("第" + i + "天，快递还在路上！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("快递到了！");
    }
}
