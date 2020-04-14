package com.javachen.jpa.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author june
 * @createTime 2019-07-12 17:09
 * @see
 * @since
 */
@Slf4j
public class DataSourceContextHolder {
    public final static String MASTER_DATASOURCE_KEY = "master";
    public final static String SLAVE_DATASOURCE_KEY = "slave";

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void setDataSource(String type) {
        log.info("setDataSource: [{}]",type);
        holder.set(type);
    }

    public static String getDataSource() {
        String lookUpKey = holder.get();
        return lookUpKey == null ? MASTER_DATASOURCE_KEY : lookUpKey;
    }

    public static void clear() {
        holder.remove();
    }
}