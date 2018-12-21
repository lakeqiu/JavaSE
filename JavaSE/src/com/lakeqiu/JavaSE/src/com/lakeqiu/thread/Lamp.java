package com.lakeqiu.thread;

/**
 * 生产者消费者模式-->信号灯法
 * 借助标志位
 * Created by lakeqiu
 */
public class Lamp {
    public static void main(String[] args) {
        express express = new express();
        new sender(express).start();
        new buyer(express).start();
    }
}

/**
 * 卖家
 */
class sender extends Thread{
    private express express;

    public sender(express express){
        this.express = express;
    }

    public sender(){}

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            express.send(i);
        }
    }
}

/**
 * 买家
 */
class buyer extends Thread{
    private express express;

    public buyer(express express){
        this.express = express;
    }

    public buyer(){}

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            express.recrive(i);
        }
    }
}

/**
 * 快递公司
 */
class express{
    private Boolean flag = true;  // 标志,true为发快递，false为收快递

    synchronized void send(int i){
        if (!flag){ // 货物已经发出，但用户还没收到，等待
            try {
                this.wait();   // 等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 用户已经收到货物，继续发出货物
        System.out.println("发出了货物" + i); // 发出货物
        this.notifyAll();  // 唤醒线程，提醒用户可以接受货物了
        flag = !flag; // 改变标志
    }


    synchronized void recrive(int i){
        if (flag){ // 没有货物可以接受，等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 接受货物
        System.out.println("接到了货物" + i);
        this.notifyAll();  // 唤醒线程，通知可以发出货物了
        flag = !flag;  // 改变标志
    }
}
