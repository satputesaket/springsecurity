package com.example.security.demospringsecurity.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

/**
 * Servlet Filter implementation class CustomSecurityFilter
 */
@WebFilter("/CustomSecurityFilter")
public class CustomSecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CustomSecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("method::destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		System.out.println("method::doFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		Principal userPrincipal = httpServletRequest.getUserPrincipal();
		System.out.println("userPrincipal"+userPrincipal);
		chain.doFilter(httpServletRequest, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("method::init");
	}

}
