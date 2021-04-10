package com.expression.parser.function;

import java.util.ArrayList;
import java.util.List;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

/**
 * The Class ComplexFunction.
 */
public class ComplexFunction {

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
	public ComplexFunction(final String f) {
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
	public Complex getValue(final List<Complex> values, final List<String> variables) throws CalculatorException {
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
	 * @return the complex
	 * @throws CalculatorException the calculator exception
	 */
	private Complex eval(final String f, final List<Complex> values, final List<String> variables)
			throws CalculatorException {
		Complex value = new Complex(0, 0);
		String number = "";
		String function = "";

		boolean hasNumber = false;
		boolean hasFunction = false;
		boolean isImaginary = false;

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (character >= '0' && character <= '9') {

				hasNumber = true;
				number += character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}

			} else if (character == '+') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(new Complex(numb, 0), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(new Complex(0, numb), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(eval(function, values, variables), eval(new_f, values, variables));
					i += new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(value, eval(new_f, values, variables));
					i += new_f.length();
				}

			} else if (character == '*') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(new Complex(numb, 0), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					number = "";

				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(new Complex(0, numb), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(eval(function, values, variables), eval(new_f, values, variables));
					i += new_f.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(value, eval(new_f, values, variables));
					i += new_f.length();
				}

			} else if (character == '-') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(new Complex(numb, 0), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(new Complex(0, numb), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(eval(function, values, variables), eval(new_f, values, variables));
					i += new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(value, eval(new_f, values, variables));
					i += new_f.length();
				}

			} else if (character == '/') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(new Complex(numb, 0), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(new Complex(0, numb), eval(new_f, values, variables));
					i += new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(eval(function, values, variables), eval(new_f, values, variables));
					i += new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(value, eval(new_f, values, variables));
					i += new_f.length();
				}

			} else if (character == '^') {

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(new_f, values, variables), numb);
					i += new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(new_f, values, variables), new Complex(0, numb));
					i += new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(function, values, variables), eval(new_f, values, variables));
					i += new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(value, eval(new_f, values, variables));
					i += new_f.length();
				}

			} else if (character == '.') {
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}
				if (hasNumber && (number.length() > 0)) {
					number += character;
				}

			} else if (character == '(') {
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}

				final String new_f = f.substring(i + 1, nextBracket(f));
				if (hasFunction) {
					if (Constants.SIN.equals(function)) {
						value = eval(new_f, values, variables).sin();

					} else if (Constants.COS.equals(function)) {
						value = eval(new_f, values, variables).cos();

					} else if (Constants.TAN.equals(function)) {
						value = eval(new_f, values, variables).tan();

					} else if (Constants.SINH.equals(function)) {
						value = eval(new_f, values, variables).sinh();

					} else if (Constants.COSH.equals(function)) {
						value = eval(new_f, values, variables).cosh();

					} else if (Constants.TANH.equals(function)) {
						value = eval(new_f, values, variables).tanh();

					} else if (Constants.ASIN.equals(function)) {
						value = eval(new_f, values, variables).asin();

					} else if (Constants.ACOS.equals(function)) {
						value = eval(new_f, values, variables).acos();

					} else if (Constants.ATAN.equals(function)) {
						value = eval(new_f, values, variables).atan();
					} else if (Constants.LN.equals(function)) {
						value = eval(new_f, values, variables).log();
					} else if (Constants.LOG.equals(function)) {
						value = eval(new_f, values, variables).log10();
					} else if (Constants.SQRT.equals(function)) {
						value = eval(new_f, values, variables).sqrt();
					} else if (Constants.CBRT.equals(function)) {
						value = Complex.cbrt(eval(new_f, values, variables));
					} else {
						throw new CalculatorException("The function is not well-formed");
					}

					hasFunction = false;
					function = "";

				} else {
					value = eval(new_f, values, variables);
				}
				i += new_f.length() + 1;

			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");

			} else if (character == ' ') {

			} else if (character == 'i') {
				if (!hasFunction) {
					if (hasNumber) {

						value = new Complex(0, new Double(number));
						number = "";
						isImaginary = true;
					} else {
						value = new Complex(0, 1);
						isImaginary = true;
					}
				} else {

					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (Constants.E.equals(function)) {
							value = new Complex(StrictMath.E, 0);
						} else if (Constants.PI.equals(function)) {
							value = new Complex(StrictMath.PI, 0);
						} else {
							if (function.length() == 1) {
								final int n = variables.indexOf(function);
								if (n >= 0) {
									value = values.get(n);
								} else {
									throw new CalculatorException("function is not well defined");
								}

							} else {
								throw new CalculatorException("function is not well defined");
							}
						}
					}
				}

			} else if (character == 'j') {
				if (!hasFunction) {
					if (hasNumber) {
						value = new Complex(0, new Double(number));
						isImaginary = true;
					} else {
						value = new Complex(0, 1);
						isImaginary = true;
					}
				} else {
					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (function.equals(Constants.E)) {
							value = new Complex(StrictMath.E, 0);
						} else if (function.equals(Constants.PI)) {
							value = new Complex(StrictMath.PI, 0);
						} else {
							if (function.length() == 1) {
								final int n = variables.indexOf(function);
								if (n >= 0) {
									value = values.get(n);
								} else {
									throw new CalculatorException("function is not well defined");
								}

							} else {
								throw new CalculatorException("function is not well defined");
							}
						}

					}
				}

			} else if (isValidCharacter(character)) {
				function = function + character;
				hasFunction = true;

				if (i == (f.length() - 1)) {

					if (Constants.E.equals(function)) {
						value = new Complex(StrictMath.E, 0);

					} else if (Constants.PI.equals(function)) {
						value = new Complex(StrictMath.PI, 0);
					} else {
						if (function.length() == 1) {
							final int n = variables.indexOf(function);
							if (n >= 0) {
								value = values.get(n);
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

			if (isValidNumericAndCharacter(character)) {
				result += character;
			} else if (character == '+' || character == '*' || character == '-' || character == '/') {
				i = f.length();
			} else if (character == '.' || character == '^') {
				result += character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result += new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");
			} else if (character == ' ') {
				result += character;
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
	private String nextMinusFunction(final String f) throws CalculatorException {
		String result = "";

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			if (isValidNumericAndCharacter(character)) {
				result += character;
			} else if (character == '+' || character == '-') {
				i = f.length();
			} else if (character == '*' || character == '/' || character == '.' || character == '^') {
				result += character;
			} else if (character == '(') {
				final String new_f = f.substring(i, nextBracket(f) + 1);
				result += new_f;
				i = (i + new_f.length()) - 1;
			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");
			} else if (character == ' ') {
				result += character;
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
