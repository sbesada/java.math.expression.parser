package com.expression.parser.util;

import com.expression.parser.exception.CalculatorException;

/**
 * The Class Factorial.
 */
public class Factorial {

	/**
	 * cal.
	 *
	 * @param m the m
	 * @param valueZero the value zero
	 * @return the int
	 * @throws CalculatorException the calculator exception
	 */
	public static int cal(final int m, final boolean valueZero) throws CalculatorException {
		if (m < 0) {
			throw new CalculatorException("the number must be greater than 0");
		}

		int result = 1;
		if (m == 0) {
			if (valueZero) {
				result = 0;
			} else {
				result = 1;
			}
		} else {
			for (int i = m; i > 0; i--) {
				result *= i;
			}
		}
		return result;
	}

}
