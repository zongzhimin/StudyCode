package com.zzm.test.lambda;

import java.util.Arrays;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		
		//jdk1.8之前
		List<String> list = Arrays.asList("one","two","three","four");
		for(String s : list) {
			System.out.println(s);
		}
		
		System.out.println();
		//1.8之后
		
		list.forEach(n -> System.out.println(n));
		
		
		System.out.println();
		
		list.forEach(System.out::println);
	}

}
