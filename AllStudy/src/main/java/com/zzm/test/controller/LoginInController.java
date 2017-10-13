package com.zzm.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzm.test.service.TestService;

@Controller
@RequestMapping("login")
public class LoginInController {
	
	@Autowired
	@Qualifier("testService")
	private TestService testService;
	
	@RequestMapping("login")
	@ResponseBody
	public Object loginPage(HttpServletRequest request ,HttpServletResponse response ,String name,String password){
		Cookie c = new Cookie("name", name);
		c.setMaxAge(5*60);
		HttpSession s = request.getSession();
		s.setAttribute("name", name);
		response.addCookie(c);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("code", 1);
		return result;
	}
	
	@RequestMapping("testDao")
	@ResponseBody
	public Object testDao(){
		return testService.testDao();
	}
	
	@RequestMapping("testJson")
	@ResponseBody
	public Object testJson(){
		return new String[]{"a","b"};
	}
	
}
