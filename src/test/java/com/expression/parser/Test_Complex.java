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
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "(1+j)*3";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "(1+2j)*3";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "(1+2j)^3";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "(1-2j)^3";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "ln((1-2j)^3)";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "log((1-2j)^3)";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "sqrt((1-2j)^3)";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "sin((2*3-2j)^0.5)";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "cos((3/2-2j)^0.5)";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "tan((3/2-2j)^(1+j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "tan((3/2-2j)^(1+j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "2*sinh((3/2-2j)^(1+j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "(2+j)*cosh((3/2-2j)^(j+2j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " ((2+j)^2)/tanh((3/2-2j)^(j+2j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " ((2+j)^2) -asin((3/2-2j)^(j+2j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " ((2+j)^2) + acos((3/2-2j)^(5+2j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " ((2+j)/2) + atan((3/2-2j)^(5/2j))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " e^(acos((3/2-2j)^(5+2j)))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " e^(acos((3/2-2j)^(pi)))";

		result = Parser.eval(f_x);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

	}

	@Test
	public void Test_two() {

		final Point xo = new Point("x", new Complex(1, 2));

		String f_x = " e^(1*x*acos((3/2-2j)^(pi)))";

		ParserResult result = Parser.eval(f_x, xo);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		final Point yo = new Point("y", new Complex(2, 1));

		f_x = " e^(1*x*y*acos((3/2)^(pi)))";

		result = Parser.eval(f_x, xo, yo);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = " e^(1*x*y*sin((3/2)^(pi)))";

		result = Parser.eval(f_x, xo, yo);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "x+j";

		result = Parser.eval(f_x, xo, yo);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

		f_x = "x-y";

		result = Parser.eval(f_x, xo, yo);
		System.out.println("real:" + result.getComplexValue().getR() + " imag:" + result.getComplexValue().getI());

	}

	@Test
	public void Test_three() {

		String f_x = "1+j +x";
		final Point xo = new Point("x", "2 +j");

		ParserResult result = Parser.eval(f_x, xo);
		System.out.println("String Expressions-->real:" + result.getComplexValue().getR() + " imag:"
				+ result.getComplexValue().getI());

		f_x = "1+j +x+y";
		final Point yo = new Point("y", "2*1+1");

		result = Parser.eval(f_x, xo, yo);
		System.out.println("String Expressions-->real:" + result.getComplexValue().getR() + " imag:"
				+ result.getComplexValue().getI());

		f_x = "1 +x + y";

		result = Parser.eval(f_x, xo, yo);
		System.out.println("String Expressions-->real:" + result.getComplexValue().getR() + " imag:"
				+ result.getComplexValue().getI());

	}
}
