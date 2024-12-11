package com.saransh.filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saransh.entity.User;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        
        if (uri.startsWith(httpRequest.getContextPath() + "/resources") ||
            uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        
        User user = (User) httpRequest.getSession().getAttribute("loggedInUser");

        if (user == null && !uri.contains("/admin/login")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/login");
            return;
        }
        
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // 
    }
}
