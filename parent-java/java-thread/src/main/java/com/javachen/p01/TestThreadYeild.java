package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:22
 * @see
 * @since
 */
public class TestThreadYeild {

    public static void main(String[] args) {
        YeildThread y1 = new YeildThread();
        YeildThread y2 = new YeildThread();

        Thread t1 = new Thread(y1,"线程A");
        Thread t2 = new Thread(y2,"线程B");

        t1.start();
        t2.start();
    }
}
class YeildThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i);
            if(i==5){
                yield();			//线程的礼让
            }
        }
    }

}
