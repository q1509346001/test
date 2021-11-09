package com.no1.cz.component.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        app.setAttribute("hi", "hello");
        log.info("应用程序启动...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce ) {
        log.info("应用程序销毁...");
    }
}
