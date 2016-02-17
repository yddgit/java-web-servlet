package com.my.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter( // urlPatterns = "/*", // urls will be filted
        asyncSupported = true, // async support
        servletNames = { "com.my.project.servlet.MyServlet" } // serlvet name
)
public class MyFilter implements Filter {

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        System.out.println( "=========My Filter Initialized..." );
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
            ServletException {
        System.out.println( "=========Before Filter..." );
        chain.doFilter( request, response );
        System.out.println( "=========After  Filter..." );
    }

    @Override
    public void destroy() {
        System.out.println( "=========My Filter destroyed..." );
    }

}
