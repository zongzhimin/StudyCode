package com.zzm.test.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test3 {

	public static void main(String[] args) {
		Stream<Long> fibonacci = Stream.generate(new FibonacciSupplier());
		fibonacci.skip(20).limit(10).forEach(System.out::println);
		
	}

}

class FibonacciSupplier implements Supplier<Long>{
	long a=0;
	long b=1;
	@Override
	public Long get() {
		long x = a+b;
		a=b;
		b=x;
		return a;
	}
	
}
