package com.expression.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.expression.parser.exception.CalculatorException;
import com.expression.parser.function.Complex;
import com.expression.parser.function.ComplexFunction;
import com.expression.parser.function.FunctionX;
import com.expression.parser.function.FunctionXs;
import com.expression.parser.util.ParserResult;
import com.expression.parser.util.Point;

/**
 * The Class Parser.
 */
public class Parser {

	/**
	 * Eval
	 *
	 * This a parser eval. The real parser of a function is within the Fuction
	 *
	 * FunctionX: functions with one var. Example 1+2*x --> it is more optimized
	 *
	 * FunctionXs: functions with several vars. Example: 1+2*x+3*y...
	 *
	 * ComplexFunction: Complex functions with several vars: one var or n vars. Example: 1+x+y +j
	 *
	 * @param function the function: 1+2*x+j...
	 * @param values the values x=10, y=20
	 *
	 * @return the parser result: complex or real value
	 */
	public static ParserResult eval(final String function, final Point... values) {

		final ParserResult result = new ParserResult();
		FunctionX f_x = null;
		FunctionXs f_xs = null;
		ComplexFunction complexFunction = null;

		if ((function != null) && !function.isEmpty()) {

			if (Parser.pointIsComplex(values) || function.toLowerCase().contains("j")) { // Complex

				complexFunction = new ComplexFunction(function);
				final List<Complex> valuesList = pointToComplexValue(values);
				final List<String> varsList = pointToVar(values);
				try {
					result.setComplexValue(complexFunction.getValue(valuesList, varsList));
				} catch (final CalculatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {

					if (values != null) {
						if (values.length == 1) {

							f_x = new FunctionX(function);

							if ((values[0].getStringValue() != null && !values[0].getStringValue().isEmpty())) {
								final ParserResult evaluatedValue = Parser.eval(values[0].getStringValue());
								result.setValue(f_x.getF_xo(evaluatedValue.getValue()));

							} else {
								result.setValue(f_x.getF_xo(values[0].getValue()));
							}

						} else if (values.length > 1) {
							f_xs = new FunctionXs(function);
							final List<Double> valuesList = pointToValue(values);
							final List<String> varsList = pointToVar(values);
							result.setValue(f_xs.getValue(valuesList, varsList));
						}

					} else {
						f_x = new FunctionX(function);
						result.setValue(f_x.getF_xo(0));
					}
				}

				catch (final CalculatorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * Eval.
	 *
	 * @param function the function
	 * @param vars the vars
	 * @param values the values
	 * @return the double
	 */
	public static double eval(final String function, final String[] vars, final Double[] values) {

		double result = 0;
		FunctionX f_x = null;
		FunctionXs f_xs = null;
		if ((function != null) && !function.equals("")) {
			try {
				if ((((vars == null) || (vars.length < 1)) && (values == null)) || (values.length < 1)) {
					f_x = new FunctionX(function);
					result = f_x.getF_xo(0);
				} else if ((values != null) && (values.length == 1)) {
					f_x = new FunctionX(function);
					result = f_x.getF_xo(values[0]);
				} else if ((vars != null) && (vars.length > 1) && (values != null) && (values.length > 1)) {
					f_xs = new FunctionXs(function);
					final List<Double> valuesList = Arrays.asList(values);
					final List<String> varsList = Arrays.asList(vars);
					result = f_xs.getValue(valuesList, varsList);
				}

			} catch (final CalculatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	/**
	 * Eval.
	 *
	 * @param function the function
	 * @return the parser result
	 */
	public static ParserResult eval(final String function) {

		ParserResult result = new ParserResult();
		FunctionX f_x = null;

		if ((function != null) && !function.equals("")) {
			try {

				if ((function.toLowerCase().contains("j") || function.toLowerCase().contains("i"))
						&& !function.toLowerCase().contains("x")) {

					result = eval(function, new Point("x", new Complex(1, 0)));
				} else if (!function.toLowerCase().contains("x")) {
					f_x = new FunctionX(function);
					result.setValue(f_x.getF_xo(0));

				} else {
					throw new CalculatorException("function is not well defined");
				}

			} catch (final CalculatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	/**
	 * PointToValue.
	 *
	 * @param values the values
	 * @return the list
	 */
	private static List<Double> pointToValue(final Point... values) {
		final List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < values.length; i++) {
			if ((values[i].getStringValue() != null && !values[i].getStringValue().isEmpty())) {
				final ParserResult evaluatedValue = Parser.eval(values[i].getStringValue());
				result.add(evaluatedValue.getValue());
			} else {
				result.add(values[i].getValue());
			}
		}
		return result;
	}

	/**
	 * PointToComplexValue.
	 *
	 * @param values the values
	 * @return the list
	 */
	private static List<Complex> pointToComplexValue(final Point... values) {
		final List<Complex> result = new ArrayList<Complex>();
		for (int i = 0; i < values.length; i++) {
			if (values[i].isComplex() && (values[i].getStringValue() == null || values[i].getStringValue().isEmpty())) {
				result.add(values[i].getComplexValue());
			} else if ((values[i].getStringValue() != null && !values[i].getStringValue().isEmpty())) {
				final ParserResult evaluatedValue = Parser.eval(values[i].getStringValue());
				if (evaluatedValue.isComplex()) {
					result.add(evaluatedValue.getComplexValue());
				} else {
					result.add(new Complex(evaluatedValue.getValue(), 0));
				}
			} else {
				result.add(new Complex(values[i].getValue(), 0));
			}

		}
		return result;
	}

	/**
	 * pointIsComplex.
	 *
	 * @param values the values
	 * @return true, if successful
	 */
	private static boolean pointIsComplex(final Point... values) {

		boolean result = false;
		for (int i = 0; i < values.length; i++) {
			if (values[i].isComplex() && (values[i].getStringValue() == null || values[i].getStringValue().isEmpty())) {
				result = true;
				break;
			} else {
				if (values[i].getStringValue() != null && !values[i].getStringValue().isEmpty()) {
					final ParserResult evaluatedValue = Parser.eval(values[i].getStringValue());
					if (evaluatedValue.isComplex()) {
						result = true;
						break;

					}

				}
			}

		}
		return result;
	}

	/**
	 * PointToVar.
	 *
	 * @param values the values
	 * @return the list
	 */
	private static List<String> pointToVar(final Point... values) {
		final List<String> result = new ArrayList<String>();
		for (int i = 0; i < values.length; i++) {
			result.add(values[i].getVar());
		}
		return result;
	}
}
