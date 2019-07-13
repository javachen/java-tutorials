package com.javachen.syn;

/**
 * 执行可以知道，demo中虽然加上了synchronized关键字来修饰方法，但是线程是不安全的。为什么呢？？
 * 分析一下：synchronized修饰的是普通方法，锁定的是Demo06实例，从Main方法中可以看到，在for循环中
 * 创建了多个Demo06的实例，也就是说每个线程对应都拿到各自的锁标记，可以同时执行。
 *
 * @author june
 * @createTime 2019-07-04 16:58
 * @see
 * @since
 */
public class Demo06 implements Runnable{
    private int count = 10;

    @Override
    public synchronized void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Demo06 demo06 = new Demo06();//注意这里
            new Thread(demo06,"THREAD" + i).start();
        }
    }
}