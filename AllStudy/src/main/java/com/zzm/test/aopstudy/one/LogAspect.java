//package com.zzm.test.aopstudy.one;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
////@Aspect
////@Component
//public class LogAspect {
//	
//	@AfterReturning("within(com.zzm.test.aopstudy.one.service..*) && annotation(fl)")
//	public void addSuccessLog(JoinPoint jp,FileLog fl) {
//		Object[] parames = jp.getArgs();
//		
//		String className = jp.getTarget().getClass().toString();
//		
//		String signature = jp.getSignature().toString();
//
//		String methodName = signature.substring(signature.lastIndexOf(".")+1, signature.indexOf("(")); 
//		
//		String desc=fl.value();  
//	}
//	
//	@AfterThrowing(pointcut="within(com.zzm.test.aopstudy.one.service..*) && annatation(fl)" ,throwing="e")
//	public void addExceptionLog(JoinPoint jp,FileLog fl,Exception e) {
//		//错误信息写到日志里
//	}
//	
//}
