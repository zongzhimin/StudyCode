package com.zzm.test.aopstudy.two;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler {
	
	private Object targetObject;
	
	public Object createProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		attack();
		Object ret = method.invoke(targetObject, args);
		attack();
		return ret;
	}
	
	private void attack() {
		System.out.println("使用普通攻击。。。");
	}
}
