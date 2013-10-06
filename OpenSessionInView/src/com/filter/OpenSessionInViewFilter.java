package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.FlushMode;
import org.hibernate.Session;

import com.db.util.HibernateHelper;

public class OpenSessionInViewFilter implements Filter {

	public void destroy() {
		System.out.println("destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		Session session = HibernateHelper.getSession();
		session.setFlushMode(FlushMode.AUTO);
		ThreadLocal<Session> tl = new ThreadLocal<Session>();
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init");
	}

}
