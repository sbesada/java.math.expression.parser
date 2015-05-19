package com.expression.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.expression.parser.util.ParserResult;
import com.expression.parser.util.Point;

public class Test_3 {

    @Test
    public void Test_three() {

        String f_x = "+3 +5*5*(+1)";

        ParserResult result = Parser.eval(f_x);
        assertTrue(result.getValue() == 28.0);

        final Point xo = new Point("x", new Double(2));

        f_x = "2.35*e^(-3)*x";

        result = Parser.eval(f_x, xo);
        assertTrue(result.getValue() == 0.2339992213289606);

        f_x = "sin(x)";

        result = Parser.eval(f_x, xo);

        if (result.isComplex()) {
            assertTrue(result.getComplexValue().getR() == 0.9092974268256817);
        } else {
            assertTrue(result.getValue() == 0.9092974268256817);
        }

        final Point yo = new Point("y", new Double(1));

        final String f_xs = "x+5*y+(3 -y)";

        result = Parser.eval(f_xs, xo, yo);
        assertTrue(result.getValue() == 9.0);
    }
}
