package com.javachen.instrument;

/**
 * @author june
 * @createTime 2019-07-11 22:34
 * @see
 * @since
 */
public class TimerLog {
    static Long start = 0L;

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void end() {
        System.out.println("cost time: "+ (System.currentTimeMillis() - start));
    }

}
