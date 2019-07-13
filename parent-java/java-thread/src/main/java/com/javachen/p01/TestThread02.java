package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:18
 * @see
 * @since
 */
public class TestThread02 {

    public static void main(String[] args) {
        MyThread m = new MyThread();
        m.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main: "+i);
        }
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread: "+i);
        }
    }

}
