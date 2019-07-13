package com.javachen.syn;

/**
 * 1.synchronized(this)锁定的是当前类的实例,demo中锁定的是Demo02类的实例
 * 2.此demo中如果Demo02类是单例的话可以保证在多线程访问时是线程安全的，
 * 如果存在有多个Demo02的实例的话在多线程中不能保证线程安全，
 * 因为方法中的锁不唯一了。(堆内存中的地址不一样)
 *
 * @author june
 * @createTime 2019-07-04 16:53
 * @see
 * @since
 */
public class Demo02 {
    private int count = 10;
    public void test(){
        synchronized (this) { //任何线程要执行下面的代码，必须先拿到Demo02对象实例的锁
            count --;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}