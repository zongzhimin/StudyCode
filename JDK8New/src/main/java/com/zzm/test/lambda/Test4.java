package com.zzm.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test4 {
	public static void main(String[] args) {
		
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> length4 = (n) -> n.length()==4;
		
		languages.stream().filter(startsWithJ.and(length4)).forEach((n)->{System.out.println(n+" ");});
			
		
	}
}
