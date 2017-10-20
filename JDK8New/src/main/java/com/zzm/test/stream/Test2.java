package com.zzm.test.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		Stream<Long> natural = Stream.generate(new NaturalSupplier());
		natural.map(x -> x*x).limit(100).forEach(System.out::println);
		
	}

}

class NaturalSupplier implements Supplier<Long> {
	
	long value = 0;
	
	@Override
	public Long get() {
		this.value = this.value+1;
		return this.value;
	}
	
}
