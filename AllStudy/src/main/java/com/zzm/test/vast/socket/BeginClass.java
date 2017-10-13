package com.zzm.test.vast.socket;

import javax.servlet.http.HttpServlet;

public class BeginClass extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		System.out.println("开始初始化");
		say();
	}
	
	public void say(){
		System.out.println("执行。。。。。。。。。。。。。。。。。。。。");
	}
}
