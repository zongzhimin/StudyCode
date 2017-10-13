package com.zzm.test.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String URIpath = req.getRequestURI();
		if(URIpath.matches("^/AllStudy/in.*")){ 
			check(req,res);
			return ;
		}
		chain.doFilter(req, res);
		return;
		
	}

	private void check(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = getCookie(req,"name");
		if(null==name){
			String path = req.getContextPath()+"";
			res.sendRedirect(path);
		}
	}

	private String getCookie(HttpServletRequest request, String str) {
		Cookie[] cookies = request.getCookies();
		if(null==cookies){
			return null;
		}
		for(Cookie c :cookies){
			if("str".equals(c.getName())){
				return c.getValue();
			}
		}
		return null;
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
