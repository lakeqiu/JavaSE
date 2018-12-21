package com.lakeqiu.thread;

/**
 * 模拟龟兔赛跑
 * Created by lakeqiu
 */
public class Recer implements Runnable {
    private String winner; // 胜利者
    @Override
    public void run() {
        int i;
        for (i = 100; i >= 0; i--){
            if ("乌龟".equals(Thread.currentThread().getName())){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 模拟兔子睡着
            if ("兔子".equals(Thread.currentThread().getName())){
                int j = (int)(Math.random()*4); // 生成1~4的整型随机数
                if (j == 1){
                    try {
                        System.out.println(Thread.currentThread().getName() + "睡着了！！！");
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println(Thread.currentThread().getName() + "距离到达终点还剩-->" + i + "步");

        }
    }

    public static void main(String[] args) {
        new Thread(new Recer(),"兔子").start();
        new Thread(new Recer(),"乌龟").start();
    }
}
