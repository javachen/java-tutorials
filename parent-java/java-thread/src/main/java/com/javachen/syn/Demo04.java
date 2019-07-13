package com.javachen.syn;

/**
 * 1.synchronize关键字修饰静态方法锁定的是类的.class文件
 * 2.静态方法中synchronize锁定代码块，锁定的对象不能是类的实例，只能是类的.class文件。
 * 原理如同在静态方法中不能直接调用非静态方法
 * 3.类的.class文件是唯一的，所以说synchronize修饰静态方法或者锁定的对象是类的.class文件的时候
 * 在多线程中是可以实现线程安全的
 *
 * @author june
 * @createTime 2019-07-04 16:55
 * @see
 * @since
 */
public class Demo04 {
    private static int count = 10;
    public synchronized static void test1(){ //这里等同于synchronized(Demo04.class)
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void test2(){ //考虑一下这里写synchronize(this)是否可以
        synchronized (Demo04.class) {
            count --;
        }
    }
}