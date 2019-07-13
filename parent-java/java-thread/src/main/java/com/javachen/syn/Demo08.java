package com.javachen.syn;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，同时也要对业务读方法加锁，否则容易产生脏读问题
 *
 */
public class Demo08 {

    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name = name;
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }
    public double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Demo08 demo08 = new Demo08();
        new Thread(()->demo08.set("zhangsan",100.0)).start(); //JDK1.8新特性
        System.out.println(demo08.getBalance("zhangsan"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo08.getBalance("zhangsan"));
    }
}