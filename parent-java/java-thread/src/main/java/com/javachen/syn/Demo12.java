package com.javachen.syn;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * @author june
 * @createTime 2019-07-04 17:06
 * @see
 * @since
 */
public class Demo12 {

    volatile boolean running = true;
    public void test(){
        System.out.println("test start.......");
        while (running) {

        }/*try {
            TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }*/
        System.out.println("test end........");
    }
    public static void main(String[] args) {
        Demo12 demo12 = new Demo12();
        new Thread(demo12 :: test, "t1").start(); //JDK1.8新特性
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo12.running = false;
    }
}