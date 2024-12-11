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

public class AuthorizationFilter implements Filter {

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
        
        if (user.getRole().equals("admin")) chain.doFilter(request, response);
        else httpResponse.sendRedirect(httpRequest.getContextPath() + "/customer/list");
        
        return;
    }


    @Override
    public void destroy() {
        // 
    }
}
