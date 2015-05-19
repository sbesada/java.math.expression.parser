package com.expression.parser;

import org.junit.Test;

import com.expression.parser.function.Complex;
import com.expression.parser.util.ParserResult;
import com.expression.parser.util.Point;

public class Test_Complex {

    @Test
    public void Test_one() {

        // TODO Auto-generated method stub

        String f_x = "1+j";

        ParserResult result = Parser.eval(f_x);

        System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "1+j*3";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "(1+j)*3";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "(1+2i)*3";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() +
                " imag:" + result.getComplexValue().getI());

        f_x = "(1+2i)^3";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "(1-2i)^3";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() +
                " imag:" + result.getComplexValue().getI());

        f_x = "ln((1-2i)^3)";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "log((1-2i)^3)";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "sqrt((1-2i)^3)";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() + " imag:" +
                result.getComplexValue().getI());

        f_x = "sin((2*3-2i)^0.5)";

        result = Parser.eval(f_x);
        System.out.println("real:" + result.getComplexValue().getR() +
                " imag:" + result.getComplexValue().getI());

        f_x = "cos((3/2-2i)^0.5)";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "tan((3/2-2i)^(1+j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "tan((3/2-2i)^(1+j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "2*sinh((3/2-2i)^(i+j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "(2+i)*cosh((3/2-2i)^(i+2j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " ((2+i)^2)/tanh((3/2-2i)^(i+2j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " ((2+i)^2) -asin((3/2-2i)^(i+2j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " ((2+i)^2) + acos((3/2-2i)^(5+2j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " ((2+i)/2) + atan((3/2-2i)^(5/2j))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " e^(acos((3/2-2i)^(5+2j)))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " e^(acos((3/2-2i)^(pi)))";

        result = Parser.eval(f_x);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

    }

    @Test
    public void Test_two() {

        Point xo = new Point("x", new Double(2));

        xo = new Point("x", new Complex(1, 2));

        String f_x = " e^(1*x*acos((3/2-2i)^(pi)))";

        ParserResult result = Parser.eval(f_x, xo);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        final Point yo = new Point("y", new Complex(2, 1));

        f_x = " e^(1*x*y*acos((3/2)^(pi)))";

        result = Parser.eval(f_x, xo, yo);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = " e^(1*x*y*sin((3/2)^(pi)))";

        result = Parser.eval(f_x, xo, yo);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "x+j";

        result = Parser.eval(f_x, xo, yo);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

        f_x = "x-y";

        result = Parser.eval(f_x, xo, yo);
        System.out.println("real:" +
                result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

    }

}
