package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:19
 * @see
 * @since
 */
public class TestThread01 {

    public static void main(String[] args) {
        Runner1 r = new Runner1();
//		r.run();
        Thread t = new Thread(r);
        t.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("Main: "+i);
        }
    }
}

class Runner1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Runner: "+i);
        }
    }

}
