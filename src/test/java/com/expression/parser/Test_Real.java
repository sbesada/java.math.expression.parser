package com.expression.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.expression.parser.util.ParserResult;
import com.expression.parser.util.Point;

public class Test_Real {

	@Test
	public void Test_one() {

		String f_x = " (2)-(5)";

		double result = Parser.eval(f_x).getValue();
		assertTrue(result == -3.0);

		f_x = "((2)+(5))";

		result = Parser.eval(f_x).getValue();
		assertTrue(result == 7.0);

		final Point xo = new Point("x", new Double(2));

		f_x = "5*(x +3)";

		result = Parser.eval(f_x, xo).getValue();
		assertTrue(result == 25.0);

		f_x = "5*(2*(sqrt((x+2)^2)) +3)";

		result = Parser.eval(f_x, xo).getValue();
		assertTrue(result == 55.0);

		f_x = "5*(2*(sqrt((x+2)^2)/2) +3)";

		result = Parser.eval(f_x, xo).getValue();
		assertTrue(result == 35.0);

		f_x = "cosh(6+(2/0))";
		System.out.println("result:" + Parser.eval(f_x, xo).getValue());

		final String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";

		final Point zo = new Point("z", new Double(1));

		result = Parser.eval(f_xs, xo, zo).getValue();
		assertTrue(result == -18.0);

		result = Parser.eval(f_xs, zo, xo).getValue();
		assertTrue(result == -18.0);

		final Point x2 = new Point("x", new Double(0));
		f_x = "cos(x)";
		ParserManager.getInstance().setDeegre(true);
		result = Parser.eval(f_x, x2).getValue();
		assertTrue(result == 1.0);
		ParserManager.getInstance().setDeegre(false);

		System.out.println("End test one");
	}

	@Test
	public void Test_two() {

		String f_x = " (2)-(5)";

		double result = Parser.eval(f_x, null, null);
		assertTrue(result == -3.0);

		f_x = "((2)+(5))";

		result = Parser.eval(f_x, null, null);
		assertTrue(result == 7.0);

		final Double x0 = new Double("2");

		final Double[] values = { x0 };
		f_x = "5*(x +3)";

		result = Parser.eval(f_x, null, values);
		assertTrue(result == 25.0);

		final String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";

		final Double z0 = new Double("1");

		final Double[] values2 = { x0, z0 };
		final String[] vars = { "x", "z" };

		result = Parser.eval(f_xs, vars, values2);
		assertTrue(result == -18.0);
		System.out.println("End test two");

	}

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
		assertTrue(result.getValue() == 0.9092974268256817);

		final Point yo = new Point("y", new Double(1));

		final String f_xs = "x+5*y+(3 -y)";

		result = Parser.eval(f_xs, xo, yo);
		assertTrue(result.getValue() == 9.0);
		System.out.println("End test three");
	}

	@Test
	public void Test_four() {

		String f_xs = "x+5*y+(3 -y)";
		final Point xo = new Point("x", "1+1");
		final Point yo = new Point("y", "0+2*0+1*5-5 +1^4");

		ParserResult result = Parser.eval(f_xs, xo, yo);
		assertTrue(result.getValue() == 9.0);

		final String f_x = "2.35*e^(-3)*x";

		result = Parser.eval(f_x, xo);
		assertTrue(result.getValue() == 0.2339992213289606);

		f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";
		final Point zo = new Point("z", new Double(1));

		result = Parser.eval(f_xs, zo, xo);

		assertTrue(result.getValue() == -18.0);
		System.out.println("End test four");

	}

}
