package com.expression.parser.util;

import com.expression.parser.exception.CalculatorException;

/**
 * The Class Combination.
 */
public class Combination {

	/**
	 * calc.
	 *
	 * @param m the m
	 * @param n the n
	 * @return the double
	 * @throws CalculatorException the calculator exception
	 */
	public static double calc(final int m, final int n) throws CalculatorException {
		if (n < 0) {
			throw new CalculatorException("n cannot be <0");
		}

		double result = 0.0;
		if (m == 0) {
			result = 0.0;
		} else {
			result = (double) Factorial.cal(m, false)
					/ (double) (Factorial.cal(m - n, false) * Factorial.cal(n, false));
		}

		return result;

	}

}
