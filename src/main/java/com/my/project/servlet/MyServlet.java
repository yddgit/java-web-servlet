package com.my.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.project.service.Executor;

@WebServlet( urlPatterns = { "/myservlet" }, // url mapping
        asyncSupported = true, // async support
        // name = "simpleServlet", // servlet name, default: full class name
        displayName = "MySimpleServlet", // display name
        loadOnStartup = -1, // load on startup priority
        initParams = { @WebInitParam( name = "name", value = "Tom" ) } // init params
)
public class MyServlet extends HttpServlet {

    private static final long             serialVersionUID = 5513592499016657828L;
    private static final SimpleDateFormat sdf              = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSSZ" );

    @Override
    public void init() throws ServletException {
        System.out.println( "name = " + this.getInitParameter( "name" ) + " > This param initialized by @WebInitParam" );
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        resp.setContentType( "text/html;charset=UTF-8" );
        PrintWriter out = resp.getWriter();
        out.println( "Enter Servlet Time: " + sdf.format( new Date() ) + ".<br>" );
        System.out.println( "Enter Servlet" );
        out.flush();

        // 在子线程中执行业务调用，并由其负责输出响应，主线程退出
        AsyncContext ctx = req.startAsync();
        ctx.addListener( new AsyncListener() {

            @Override
            public void onComplete( AsyncEvent event ) throws IOException {
                System.out.println( "Async Thread Complete..." );
            }

            @Override
            public void onTimeout( AsyncEvent event ) throws IOException {
                System.out.println( "Async Thread Timeout..." );
            }

            @Override
            public void onError( AsyncEvent event ) throws IOException {
                System.out.println( "Async Thread Error..." );
            }

            @Override
            public void onStartAsync( AsyncEvent event ) throws IOException {
                System.out.println( "Async Thread Start..." );
            }

        } );

        new Thread( new Executor( ctx, getServletContext().getContextPath() ) ).start();

        out.println( "Exit  Servlet Time: " + sdf.format( new Date() ) + ".<br>" );
        System.out.println( "Exit  Servlet" );
        out.flush();
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        doGet( req, resp );
    }

}
