package com.peng.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.peng.concurrency.example.threadlocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		log.info("do filter, {}, {}",Thread.currentThread().getId(),request.getServletPath());
		RequestHolder.add(Thread.currentThread().getId());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
