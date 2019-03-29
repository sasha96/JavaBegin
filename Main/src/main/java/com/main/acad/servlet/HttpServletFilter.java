package com.main.acad.servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import java.io.IOException;

public class HttpServletFilter implements Filter {
private static final String ENCODING = "UTF-8";
private static final String ENCODING_TEXT = "text/html;charset=UTF-8";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        try {
            request.setCharacterEncoding(ENCODING);
            chain.doFilter(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        response.setContentType(ENCODING_TEXT);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }
}

