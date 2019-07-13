package com.javachen.p01;

/**
 * @author june
 * @createTime 2019-07-04 17:29
 * @see
 * @since
 */
public class ProducterComsuterDemo {

    public static void main(String[] args) {
        Bucket bc = new Bucket();
        Producter p = new Producter(bc);
        Cosumter m = new Cosumter(bc);
        new Thread(p,"生产者A: ").start();
        new Thread(p,"生产者B: ").start();
        new Thread(m,"消费者A: ").start();
    }
}

/**
 * 馒头类
 *
 * @author yangluan 2016年11月1日下午9:10:00
 */
class ManTou {
    private int id; // 馒头编号

    public ManTou(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "馒头" + id;
    }
}

/**
 * 篮子类 分析 篮子里面可以存放固定的馒头 应该是数组存放 存放馒头的时候是有序的 采用栈 先进后的方式存储 篮子方法：可以放馒头
 * 可以取馒头,但是放和取的时候是有编号的，所以应该有个标记为index
 *
 * @author yangluan 2016年11月1日下午9:11:21
 */
class Bucket {
    private int index = 0; // 篮子放馒头标记位
    private ManTou[] arrManTou = new ManTou[6]; // 可以存放6个馒头

    // 放馒头方法
    public synchronized void push(ManTou m) { // 只能有一个人在生产馒头修改标记位共享变量index
        while (index == arrManTou.length) { // 说明馒头放满了，就需要等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.notifyAll(); // 唤醒消费馒头的线程
        // 开始生产馒头
        arrManTou[index] = m;
        index++;
        System.out.println(Thread.currentThread().getName() + "生产" + m);

    }

    // 取馒头方法
    public synchronized ManTou pop() { // 只能有一个人在取馒头修改标记位共享变量index
        while (index == 0) {// 篮子为空 开始等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();// 唤醒所有生产馒头的线程
        // 开始消费
        index--;
        System.out.println(Thread.currentThread().getName() + "消费"
                +arrManTou[index] );
        return arrManTou[index];
    }
}

/**
 * 生产者类
 * 可以看见篮子
 * 可以生产馒头
 * @author yangluan
 * 2016年11月1日下午9:26:29
 */
class Producter implements Runnable{
    private Bucket bc =null;

    public Producter(Bucket bc){
        this.bc =bc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ManTou m =new ManTou(i);
            bc.push(m);
            try {
                Thread.sleep((int)(Math.random()* 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



/**
 * 消费类
 * 可以看见篮子
 * 可以生产馒头
 * @author yangluan
 * 2016年11月1日下午9:26:29
 */
class Cosumter implements Runnable{
    private Bucket bc =null;

    public Cosumter(Bucket bc){
        this.bc =bc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            ManTou m =this.bc.pop();
            try {
                Thread.sleep((int)(Math.random()* 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

