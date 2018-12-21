package com.lakeqiu.thread;

/**
 * 实现多线程方式：
 * 1，继承Thread类
 * 2，实现Runable接口
 * 3，实现Callable接口（高阶段），重写call方法
 *
 * 优先使用接口，避免单继承的局限，而且方便共享资源
 * 要知道开启多线程的时机很重要
 *
 * 补：如果一个对象只使用一次，那么用匿名好一些
 * Created by lakeqiu
 */
public class TestThread {
    public static void main(String[] args) {
        // 创建子类对象
        FirThread f1 = new FirThread();
        FirThread f2 = new FirThread();

        f1.start(); // start()方法只是交给cpu调用，并不能保证立即执行，这个方法会执行run方法
        f2.start(); // run()方法只是普通方法的调用，jvm会等其运行完后再执行下面代码

        // 创建实现类对象
        RunThread r1 = new RunThread();
        RunThread r2 = new RunThread();

        // 创建代理类对象
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        // 匿名
        new FirThread().start();
        new Thread(new RunThread()).start();

        System.out.println("main()方法结束了！！！");
    }
}

/**
 * 继承Thread类
 * 重写run方法
 * 启动：创建子类对象 + start()方法
 */
class FirThread extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println( "继承："+ this.getName() + "-->Thread run" + i);
        }
    }
}


/**
 * 实现Runable接口
 * 重写run方法
 * 因为没有start方法，所以用的时候需要借助Thread类代理
 * 启动：创建实现类对象 + 创建Thread类对象（代理） + start方法
 */
class RunThread implements Runnable{
    @Override
    public void run() {
        for (int i = 10; i < 20; i++){
            System.out.println("实现：" + Thread.currentThread().getName() + "-->Runable run" + i);
        }
    }
}