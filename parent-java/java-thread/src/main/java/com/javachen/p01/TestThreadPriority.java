package com.javachen.p01;

/**
 * 线程的优先级别高，并不代表其他级别低的就不执行，等待高的执行完毕才执行，只是级别高的线程可能先执行的完毕的几率大一些
 *
 * @author june
 * @createTime 2019-07-04 17:24
 * @see
 * @since
 */
public class TestThreadPriority {
    public static void main(String[] args) {
        ThreadPriority tp1 = new ThreadPriority();
        ThreadPriority tp2 = new ThreadPriority();

        Thread t1 = new Thread(tp1,"A");
        Thread t2 = new Thread(tp2,"B");
        t1.setPriority(Thread.NORM_PRIORITY+3);   //设置线程的优先级 5+3=8
        t1.start();
        t2.start();
    }
}
class ThreadPriority extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }
}
