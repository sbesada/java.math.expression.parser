package com.expression.parser.function;

import java.util.ArrayList;
import java.util.List;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

/**
 * The Class FunctionXs.
 */
public class FunctionXs {

	/** setup. */
	public boolean degree = false;

	/**
	 * f(x,y,z,...)
	 */
	private String f;

	/**
	 * FunctionXs.
	 *
	 * @param f the f
	 */
	public FunctionXs(final String f) {
		this.f = f.trim().replaceAll(" ", "").toLowerCase();
		degree = ParserManager.getInstance().isDeegre();
	}

	/**
	 * getter f(x,y,z,...)
	 *
	 * @return the f
	 */
	public String getF() {
		return f;
	}

	/**
	 * setter f(x,y,z,...)
	 *
	 * @param f the new f
	 */
	public void setF(final String f) {
		this.f = f;
	}

	/**
	 * getValue f(x0,y0,z0...)
	 *
	 * @param values (sort the values taking into account the variables)
	 * @param variables x,y,z etc
	 * @return the value
	 * @throws CalculatorException the calculator exception
	 */
	public double getValue(final List<Double> values, final List<String> variables) throws CalculatorException {
		final List<String> vars = new ArrayList<String>();
		for (final String string : variables) {
			vars.add(string.trim().replaceAll(" ", "").toLowerCase());
		}
		return eval(f, values, vars);
	}

	/**
	 * eval.
	 *
	 * @param f the f
	 * @param values the values
	 * @param variables the variables
	 * @return the double
	 * @throws CalculatorException the calculator exception
	 */
	private double eval(final String f, final List<Double> values, final List<String> variables)
			throws CalculatorException {
		double value = 0;
		String number = "";
		String function = "";
		boolean hasNumber = false;
		boolean hasFunction = false;

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character >= '0' && character <= '9') {

				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

			} else if (character == '+') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = numb + eval(new_f, values, variables);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = f.substring(i + 1, f.length());
					value = eval(function, values, variables) + eval(new_f, values, variables);
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = f.substring(i + 1, f.length());
					value = value + eval(new_f, values, variables);
					i = i + new_f.length();
				}

			} else if (character == '*') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = numb * eval(new_f, values, variables);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = eval(function, values, variables) * eval(new_f, values, variables);
					i = i + new_f.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = value * eval(new_f, values, variables);
					i = i + new_f.length();
				}

			} else if (character == '-') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = numb - eval(new_f, values, variables);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = eval(function, values, variables) - eval(new_f, values, variables);
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = value - eval(new_f, values, variables);
					i = i + new_f.length();
				}

			} else if (character == '/') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = numb / eval(new_f, values, variables);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = eval(function, values, variables) / eval(new_f, values, variables);
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = value / eval(new_f, values, variables);
					i = i + new_f.length();
				}

			} else if (character == '^') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Math.pow(numb.doubleValue(), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Math.pow(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Math.pow(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

			} else if (character == '.') {
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}
				if (hasNumber && (number.length() > 0)) {
					number = number + character;
				}

			} else if (character == '(') {
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}

				final String new_f = f.substring(i + 1, nextBracket(f));
				if (hasFunction) {
					if (function.equals(Constants.SIN)) {
						if (degree) {
							value = Math.sin(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.sin(eval(new_f, values, variables));
						}

					} else if (function.equals(Constants.COS)) {
						if (degree) {
							value = Math.cos(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.cos(eval(new_f, values, variables));
						}
					} else if (function.equals(Constants.TAN)) {
						if (degree) {
							value = Math.tan(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.tan(eval(new_f, values, variables));
						}

					} else if (function.equals(Constants.SINH)) {
						value = Math.sinh(eval(new_f, values, variables));

					} else if (function.equals(Constants.COSH)) {
						value = Math.cosh(eval(new_f, values, variables));

					} else if (function.equals(Constants.TANH)) {
						value = Math.tanh(eval(new_f, values, variables));

					} else if (function.equals(Constants.ASIN)) {
						if (degree) {
							value = Math.asin(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.asin(eval(new_f, values, variables));
						}
					} else if (function.equals(Constants.ACOS)) {
						if (degree) {
							value = Math.acos(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.acos(eval(new_f, values, variables));
						}
					} else if (function.equals(Constants.ATAN)) {
						if (degree) {
							value = Math.atan(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.atan(eval(new_f, values, variables));
						}
					} else if (function.equals(Constants.LN)) {
						value = Math.log(eval(new_f, values, variables));
					} else if (function.equals(Constants.LOG)) {
						value = Math.log10(eval(new_f, values, variables));
					} else if (function.equals(Constants.SQRT)) {
						value = Math.sqrt(eval(new_f, values, variables));
					} else if (function.equals(Constants.CBRT)) {
						value = Math.cbrt(eval(new_f, values, variables));
					} else {
						throw new CalculatorException("The function is not well-formed");
					}

					hasFunction = false;
					function = "";

				} else {
					value = eval(new_f, values, variables);
				}
				i = i + new_f.length() + 1;

			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");

			} else if (character == ' ') {

			} else if (isValidCharacter(character)) {
				function = function + character;
				hasFunction = true;

				if (i == (f.length() - 1)) {

					if (function.equals(Constants.E)) {
						value = Math.E;
					} else if (function.equals(Constants.PI)) {
						value = Math.PI;
					} else {
						if (function.length() == 1) {
							final int n = variables.indexOf(function);
							if (n >= 0) {
								final double v = values.get(n).doubleValue();
								value = v;
							} else {
								throw new CalculatorException("function is not well defined");
							}

						} else {
							throw new CalculatorException("function is not well defined");
						}
					}
				}
			} else {
				throw new CalculatorException("Invalid character:" + character);
			}
		}
		return value;
	}

	/**
	 * nextFunction.
	 *
	 * @param f the f
	 * @return the string
	 * @throws CalculatorException the calculator exception
	 */
	private String nextFunction(final String f) throws CalculatorException {
		String result = "";

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character == '+' || character == '*' || character == '-' || character == '/') {
				i = f.length();
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");
			} else if (character == ' ') {
				result = result + character;
			} else if (isValidNumericAndCharacter(character)) {
				result = result + character;
			} else {
				throw new CalculatorException("Invalid character:" + character);
			}
		}
		return result;
	}

	/**
	 * nextMinusFunction.
	 *
	 * @param f the f
	 * @return the string
	 * @throws CalculatorException the calculator exception
	 */
	private String nextMinusFunction(String f) throws CalculatorException {
		String result = "";
		f = f.trim().toLowerCase();

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character == '+') {
				i = f.length();
			} else if (character == '*') {
				result = result + character;
			} else if (character == '-') {
				i = f.length();
			} else if (character == '/') {
				result = result + character;
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");
			} else if (character == ' ') {
				result = result + character;
			} else if (isValidNumericAndCharacter(character)) {
				result = result + character;
			} else {
				throw new CalculatorException("Invalid character:" + character);
			}
		}
		return result;

	}

	/**
	 * isValidCharacter.
	 *
	 * @param character the character
	 * @return true, if is valid character
	 */
	private boolean isValidCharacter(final char character) {
		boolean result = false;
		if ((character >= 'a' && character <= 'z')) {
			result = true;
		}
		return result;
	}

	/**
	 * isValidNumericAndCharacter.
	 *
	 * @param character the character
	 * @return true, if is valid numeric and character
	 */
	private boolean isValidNumericAndCharacter(final char character) {
		boolean result = false;
		if ((character >= 'a' && character <= 'z') || (character >= '0' && character <= '9')) {
			result = true;
		}
		return result;
	}

	/**
	 * nextBracket.
	 *
	 * @param f the f
	 * @return the int
	 * @throws CalculatorException the calculator exception
	 */
	private int nextBracket(final String f) throws CalculatorException {
		int result = 0;
		int count = 0;
		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);
			if (character == '(') {
				result = i;
				count++;
			} else if (character == ')') {
				result = i;
				count--;
				if (count == 0) {
					return i;
				}
			} else {
				result = i;
			}
		}

		if (count != 0) {
			throw new CalculatorException("( is not finished");
		}
		return result;
	}

}
