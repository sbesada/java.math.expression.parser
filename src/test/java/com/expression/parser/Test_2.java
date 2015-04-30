package com.expression.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Test_2 {

    @Test
    public void Test_two() {

        String f_x = " (2)-(5)";

        double result = Parser.Eval(f_x, null, null);
        assertTrue(result == -3.0);

        f_x = "((2)+(5))";

        result = Parser.Eval(f_x, null, null);
        assertTrue(result == 7.0);

        final Double x0 = new Double("2");

        final Double[] values = { x0 };
        f_x = "5*(x +3)";

        result = Parser.Eval(f_x, null, values);
        assertTrue(result == 25.0);

        final String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";

        final Double z0 = new Double("1");

        final Double[] values2 = { x0, z0 };
        final String[] vars = { "x", "z" };

        result = Parser.Eval(f_xs, vars, values2);
        assertTrue(result == -18.0);

    }
}
