package com.javachen.boot.basic.web.shutdownhooks.beans;

public class Bean3 {

    public void destroy() {
        System.out.println("Shutdown triggered using bean destroy method.");
    }
}
