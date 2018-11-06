package com.helio4.bancol.avaluos.dominio;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheFilter implements Filter {
    private static long maxAge =(86400 * 365)+1; // 86400 is a day in seconds

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (uri.contains(".js") || uri.contains(".css") || uri.contains(".svg") || uri.contains(".gif")
                || uri.contains(".woff") || uri.contains(".woff2")|| uri.contains(".png") || uri.contains(".jpg")) {
        	httpResponse.setHeader("Cache-Control", "public, "+"max-age=" + maxAge);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Cache Filter started: ");

    }

    @Override
    public void destroy() {
    }
}