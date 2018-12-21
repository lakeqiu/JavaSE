package com.lakeqiu.thread;

/**
 * 生产者消费者模式--管程法
 * 借助缓冲区
 * Created by lakeqiu
 */
public class Monitor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new producer(buffer).start();
        new consumer(buffer).start();
    }
}

/**
 * 生产者
 */
class producer extends Thread {
    Buffer buffer;

    public producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public producer() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.push(new goods(i));
            System.out.println("生产-->" + i);
        }
    }
}

/**
 * 消费者
 */
class consumer extends Thread {
    Buffer buffer;

    public consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public consumer() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            buffer.pop();
            System.out.println("消费-->" + i);
        }
    }
}

/**
 * 缓冲区
 */
class Buffer {
    goods[] bean; // 存放商品的容器
    int count; // 计数器

    public Buffer() {
        this.bean = new goods[100];
    }

    synchronized void push(goods good) {
        // 何时生产 容器没有装满时
        // 容器满的时候 等待
        if (count == bean.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 容器没有满的时候，生产
        bean[count++] = good;
        this.notifyAll();  // 唤醒等待的线程，通知消费者可以消费了
    }

    synchronized void pop() {
        // 何时消费 容器中还有数据存在
        // 没有数据时 等待
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 有数据，消费
        goods sun = bean[count--];
        this.notifyAll();  // 唤醒等待的线程，通知生产者继续生产

    }
}

/**
 * 商品
 */
class goods {
    int id;

    public goods(int id) {
        this.id = id;
    }
}
