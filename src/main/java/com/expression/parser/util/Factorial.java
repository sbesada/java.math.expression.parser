package com.expression.parser.util;

import com.expression.parser.exception.CalculatorException;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Factorial {

    /**
     * 
     * cal
     * 
     * @param m
     * @param valueZero
     * @return
     * @throws CalculatorException
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
