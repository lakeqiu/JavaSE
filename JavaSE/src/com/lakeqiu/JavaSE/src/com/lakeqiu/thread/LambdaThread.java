package com.lakeqiu.thread;

/**
 * 使用lambda精简代码
 * Created by lakeqiu
 */
public class LambdaThread {
    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++)
                System.out.println("做作业！");
            Thread.yield();
        }).start();

        new Thread(()-> System.out.println("听歌")).start();
    }
}
