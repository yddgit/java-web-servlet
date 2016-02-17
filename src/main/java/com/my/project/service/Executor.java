package com.my.project.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.AsyncContext;

public class Executor implements Runnable {

    private AsyncContext                  ctx = null;
    private String                        contextPath;
    private static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSSZ" );

    public Executor( AsyncContext ctx, String contextPath ) {
        super();
        this.ctx = ctx;
        this.contextPath = contextPath;
    }

    @Override
    public void run() {
        try {
            Thread.sleep( 3000 );
            PrintWriter out = ctx.getResponse().getWriter();
            out.println( "Do Business Time: " + sdf.format( new Date() ) + ".<br>" );
            out.println( "<a href=\"" + contextPath + "\">Home</a>" );
            System.out.println( "Do Business" );
            out.flush();
            ctx.complete();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
