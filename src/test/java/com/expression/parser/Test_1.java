package com.expression.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.expression.parser.util.Point;

public class Test_1 {

    @Test
    public void Test_one() {

        String f_x = " (2)-(5)";

        double result = Parser.Eval(f_x);
        assertTrue(result == -3.0);

        f_x = "((2)+(5))";

        result = Parser.Eval(f_x);
        assertTrue(result == 7.0);

        final Point xo = new Point("x", new Double(2));

        f_x = "5*(x +3)";

        result = Parser.Eval(f_x, xo);
        assertTrue(result == 25.0);

        f_x = "5*(2*(sqrt((x+2)^2)) +3)";

        result = Parser.Eval(f_x, xo);
        assertTrue(result == 55.0);

        f_x = "5*(2*(sqrt((x+2)^2)/2) +3)";

        result = Parser.Eval(f_x, xo);
        assertTrue(result == 35.0);

        f_x = "cosh(6+(2/0))";
        System.out.println("result:" + Parser.Eval(f_x, xo));

        final String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";

        final Point zo = new Point("z", new Double(1));

        result = Parser.Eval(f_xs, xo, zo);
        assertTrue(result == -18.0);

        result = Parser.Eval(f_xs, zo, xo);
        assertTrue(result == -18.0);

        final Point x2 = new Point("x", new Double(0));
        f_x = "cos(x)";
        ParserManager.getInstance().setDeegre(true);
        result = Parser.Eval(f_x, x2);
        assertTrue(result == 1.0);
        ParserManager.getInstance().setDeegre(false);

    }
}
