package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:28
 * @see
 * @since
 */
public class TestThreadDeadLock implements Runnable {
    private int flag = 1; // 标记为用来区分所对象的
    private static Object o1 = new Object(); // 对象o1
    private static Object o2 = new Object(); // 对象02

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+": flag= "+flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1");
                synchronized (o2) {
                    System.out.println("2");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
                synchronized (o1) {
                    System.out.println("1");
                }
            }
        }
    }
    public static void main(String[] args) {
        //生命两个对象
        TestThreadDeadLock td1 = new TestThreadDeadLock();
        TestThreadDeadLock td2 = new TestThreadDeadLock();

        //分别加入把对象加入到两个不容的线程中
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);

        //设置程序代码的逻辑
        td1.flag=1;
        td2.flag=0;

        //线程启动
        t1.start();
        t2.start();
    }
}
