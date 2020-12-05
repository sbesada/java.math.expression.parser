package com.expression.parser;

import org.junit.Test;

public class SpeedTest {

	@Test
	public void testOne() {

		final long time1 = System.currentTimeMillis();
		Parser.SimpleEval(
				"6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		final long time2 = System.currentTimeMillis();
		System.out.println("time test one:" + (time2 - time1));

	}

	@Test
	public void testTwo() {

		final long time1 = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			Parser.SimpleEval(
					"6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		}

		final long time2 = System.currentTimeMillis();
		System.out.println("time test two:" + (time2 - time1));

	}

	@Test
	public void testThree() {

		final long time1 = System.currentTimeMillis();
		Parser.SimpleEval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");
		final long time2 = System.currentTimeMillis();
		System.out.println("time test three:" + (time2 - time1));

	}
}
