package com.zzm.test.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test1 {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		Stream<Integer> stream = numbers.stream();
		stream.filter(n -> n%2==0).map(x -> x*x).forEach(System.out::println);
	}

}
