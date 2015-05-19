package com.expression.parser.util;

import com.expression.parser.exception.CalculatorException;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Combination {

    /**
     * 
     * calc
     * 
     * @param m
     * @param n
     * @return
     * @throws CalculatorException
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
