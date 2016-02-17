package com.my.project.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener( "This is a demo listener" )
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized( ServletContextEvent sce ) {
        System.out.println( "=========Context Initialized..." );
    }

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {
        System.out.println( "=========Context Destroyed..." );
    }

}
