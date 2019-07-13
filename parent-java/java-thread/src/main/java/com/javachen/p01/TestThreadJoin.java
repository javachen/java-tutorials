package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:21
 * @see
 * @since
 */
class MyThred implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);	//休眠一会，可以更好的看出效果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--->"+i);
        }
    }

}

public class TestThreadJoin {
    public static void main(String[] args) {
        Thread t = new Thread(new MyThred(),"abcde");   //创建线程,并且为此线程设置一个名字
        t.start();
        try {
            t.join(); //线程合并
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("main thread ----->"+i);
        }
    }
}
