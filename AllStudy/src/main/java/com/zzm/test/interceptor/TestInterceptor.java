package com.zzm.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor {


	public boolean preHandle(HttpServletRequest req,
			HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		Object name = session.getAttribute("name");
		if(name==null){
			String path = req.getContextPath();
			res.sendRedirect(path+"/login/index.jsp");
			return false;
		}
		
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {		
	}

}
