package com.javachen.syn;

/**
 * synchronized关键字修饰普通方法等同于synchronized(this)
 *
 * @author june
 * @createTime 2019-07-04 16:54
 * @see
 * @since
 */
public class Demo03 {
    private int count = 10;
    public synchronized void test(){//等同于synchronized(this)，锁定的是Demo03对象的实例
        count --;
        System.out.println(Thread.currentThread().getName() + " count =" + count);
    }
}