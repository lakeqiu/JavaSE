package com.lakeqiu.thread;

/**
 * 静态代理
 * 实现公共接口
 * 1，真实角色
 * 2，代理角色
 *
 * 记录日志用
 * Created by lakeqiu
 */
public class StaticProxy {
    public static void main(String[] args) {
        Write s = new Proxywon(new Realwon());
        s.hwrite();
    }
}

/**
 * 接口
 */
interface Write{
    void hwrite();
}

/**
 * 真实角色类
 */
class Realwon implements Write{
    @Override
    public void hwrite() {
        System.out.println("真实角色！！！");
    }
}

/**
 * 代理角色类
 */
class Proxywon implements Write{

    private Realwon realwon; // 真实角色

    public Proxywon(){

    }

    public Proxywon(Realwon realwon){
        this.realwon = realwon;
    }

    @Override
    public void hwrite(){
        pro();
        realwon.hwrite();
        end();
    }

    public void pro(){
        System.out.println("这是代理角色，代理！");
    }

    public void end(){
        System.out.println("代理结束!");
    }
}
