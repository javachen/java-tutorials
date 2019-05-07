package com.javachen.servlet.normal;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AttrListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("servlet-context-attr", "test");
        System.out.println("context init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("context destroy");
    }

}
