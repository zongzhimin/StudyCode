package com.zzm.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test3 {

	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		
		System.out.println("language which starts with J");
		filter2(languages , (str)-> str.startsWith("J"));
		
		System.out.println("language which starts with S");
		filter2(languages,(str)-> str.startsWith("S"));
		
		System.out.println("print all languages");
		filter2(languages,(str)-> true);
		
		System.out.println("print no language");
		filter2(languages,(str)-> false);
		
		System.out.println("print language whose length is greater than 4");
		filter2(languages,(str)-> str.length()>4);
		
	}
	
//	public static void filter(List<String> names,Predicate<String> condition) {
//		for(String name : names) {
//			if(condition.test(name)) {
//				System.out.println(name+" ");
//			}
//		}
//	}
	
	public static void filter2(List<String> names, Predicate<String> condition) {
		names.stream().filter((name) -> (condition.test(name))).forEach((name)->{
			System.out.println(name+" ");
		});
		
		
	}
}
