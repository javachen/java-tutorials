package com.javachen.p01;

import java.util.Date;

/**
 * @author june
 * @createTime 2019-07-04 17:19
 * @see
 * @since
 */
public class TestThreadInterrupt {
    public static void main(String[] args) {
        MyThead m = new MyThead();
        Thread t = new Thread(m);
        t.start();//线程启动
        try {
            Thread.sleep(10000);//主线程休眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();//线程终端 不建议这么用,如果要中断线程可以使用标记位 在MyThread 加入
//		m.flag=false;
    }
}
class MyThead implements Runnable{
    public boolean flag=true;
    @Override
    public void run() {
        while(flag){
            try {
                Thread.sleep(1000);	//线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" -->"+new Date());
        }
    }

}
