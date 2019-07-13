package com.javachen.syn;

/**
 * 1.run()方法没加synchronized关键字时，多个线程同时访问count，线程是不安全的
 * 2.run()方法加上synchronized关键字后，锁定的是Demo05对象的实例，因为只创建了
 * 一个Demo05的实例，多个线程访问时都要拿到Demo05的锁标记才能执行，在多个线程同时访问时也是线程安全的
 *
 * @author june
 * @createTime 2019-07-04 16:56
 * @see
 * @since
 */
public class Demo05 implements Runnable{

    private int count = 10;
    @Override
    public void run(){
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
    public static void main(String[] args) {
        Demo05 demo05 = new Demo05();
        for (int i = 0; i < 5; i++) {
            new Thread(demo05,"THREAD" + i).start();
        }
    }
}