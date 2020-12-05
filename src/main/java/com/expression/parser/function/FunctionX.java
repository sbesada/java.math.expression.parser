package com.expression.parser.function;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

/**
 * The Class FunctionX.
 */
public class FunctionX {

	/** setup. */
	private boolean degree = false;

	/** f(x). */
	private String f_x;

	/**
	 * FunctionX.
	 *
	 * @param f_x f(x)
	 */
	public FunctionX(final String f_x) {
		this.f_x = f_x.trim().replaceAll(" ", "").toLowerCase();
		degree = ParserManager.getInstance().isDeegre();
	}

	/**
	 * getter f(x).
	 *
	 * @return the f x
	 */
	public String getF_x() {
		return f_x;
	}

	/**
	 * setter f(x).
	 *
	 * @param f_x the new f x
	 */
	public void setF_x(final String f_x) {
		this.f_x = f_x;
	}

	/**
	 * get f(x0).
	 *
	 * @param xo point
	 * @return the f xo
	 * @throws CalculatorException the calculator exception
	 */
	public double getF_xo(final double xo) throws CalculatorException {
		return eval(f_x, xo);
	}

	/**
	 * eval.
	 *
	 * @param f_x the f x
	 * @param xi the xi
	 * @return the double
	 * @throws CalculatorException the calculator exception
	 */
	private double eval(final String f_x, final double xi) throws CalculatorException {
		double value = 0;
		String number = "";
		String function = "";
		boolean hasNumber = false;
		boolean hasFunction = false;

		for (int i = 0; i < f_x.length(); i++) {
			final char character = f_x.charAt(i);

			if (character >= '0' && character <= '9') {

				hasNumber = true;
				number = number + character;
				if (i == (f_x.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

			} else if (character == '+') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f_x = f_x.substring(i + 1, f_x.length());
					value = numb + eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f_x = f_x.substring(i + 1, f_x.length());
					value = eval(function, xi) + eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f_x = f_x.substring(i + 1, f_x.length());
					value = value + eval(new_f_x, xi);
					i = i + new_f_x.length();
				}

			} else if (character == '*') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = numb * eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = eval(function, xi) * eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = value * eval(new_f_x, xi);
					i = i + new_f_x.length();
				}

			} else if (character == '-') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f_x = nextMinusFunction(f_x.substring(i + 1, f_x.length()));
					value = numb - eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f_x = nextMinusFunction(f_x.substring(i + 1, f_x.length()));
					value = eval(function, xi) - eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f_x = nextMinusFunction(f_x.substring(i + 1, f_x.length()));
					value = value - eval(new_f_x, xi);
					i = i + new_f_x.length();
				}

			} else if (character == '/') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = numb / eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = eval(function, xi) / eval(new_f_x, xi);
					i = i + new_f_x.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = value / eval(new_f_x, xi);
					i = i + new_f_x.length();
				}

			} else if (character == '^') {

				if (hasNumber) {
					final Double numb = new Double(number);
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = Math.pow(numb.doubleValue(), eval(new_f_x, xi));
					i = i + new_f_x.length();
					hasNumber = false;
					number = "";
				} else if (hasFunction) {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = Math.pow(eval(function, xi), eval(new_f_x, xi));
					i = i + new_f_x.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f_x = nextFunction(f_x.substring(i + 1, f_x.length()));
					value = Math.pow(value, eval(new_f_x, xi));
					i = i + new_f_x.length();
				}

			} else if (character == '.') {

				if (i == (f_x.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}
				if (hasNumber && (number.length() > 0)) {
					number = number + character;
				}

			} else if (character == '(') {
				if (i == (f_x.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}

				final String new_f_x = f_x.substring(i + 1, nextBracket(f_x));
				if (hasFunction) {
					if (function.equals(Constants.SIN)) {

						if (degree) {
							value = Math.sin(Math.toRadians(eval(new_f_x, xi)));
						} else {
							value = Math.sin(eval(new_f_x, xi));
						}

					} else if (function.equals(Constants.COS)) {

						if (degree) {
							value = Math.cos(Math.toRadians(eval(new_f_x, xi)));
						} else {
							value = Math.cos(eval(new_f_x, xi));
						}

					} else if (function.equals(Constants.TAN)) {

						if (degree) {
							value = Math.tan(Math.toRadians(eval(new_f_x, xi)));
						} else {
							value = Math.tan(eval(new_f_x, xi));
						}

					} else if (function.equals(Constants.SINH)) {
						value = Math.sinh(eval(new_f_x, xi));

					} else if (function.equals(Constants.COSH)) {
						value = Math.cosh(eval(new_f_x, xi));

					} else if (function.equals(Constants.TANH)) {
						value = Math.tanh(eval(new_f_x, xi));

					} else if (function.equals(Constants.ASIN)) {
						if (degree) {
							value = Math.asin(eval(new_f_x, xi)) * (180 / Math.PI);
						} else {
							value = Math.asin(eval(new_f_x, xi));
						}
					} else if (function.equals(Constants.ACOS)) {
						if (degree) {
							value = Math.acos(eval(new_f_x, xi)) * (180 / Math.PI);
						} else {
							value = Math.acos(eval(new_f_x, xi));
						}
					} else if (function.equals(Constants.ATAN)) {
						if (degree) {
							value = Math.atan(eval(new_f_x, xi)) * (180 / Math.PI);
						} else {
							value = Math.atan(eval(new_f_x, xi));
						}
					} else if (function.equals(Constants.LN)) {
						value = Math.log(eval(new_f_x, xi));
					} else if (function.equals(Constants.LOG)) {
						value = Math.log10(eval(new_f_x, xi));
					} else if (function.equals(Constants.SQRT)) {
						value = Math.sqrt(eval(new_f_x, xi));
					} else if (function.equals(Constants.CBRT)) {
						value = Math.cbrt(eval(new_f_x, xi));
					} else {
						throw new CalculatorException("The function is not well-formed");
					}

					hasFunction = false;
					function = "";

				} else {
					value = eval(new_f_x, xi);
				}
				i = i + new_f_x.length() + 1;

			} else if (character == ')') {
				throw new CalculatorException(" '(' is not finished ");

			} else if (character == ' ') {

			} else if (isValidCharacter(character)) {
				function = function + character;
				hasFunction = true;

				if (i == (f_x.length() - 1)) {
					if (function.equals(Constants.E)) {
						value = Math.E;
					} else if (function.equals(Constants.PI)) {
						value = Math.PI;
					} else if (function.length() == 1) {
						value = xi;
					} else {
						throw new CalculatorException("function is not well defined");
					}
				}
			} else {
				throw new CalculatorException("Invalid character:" + character);
			}

		}
		return value;

	}

	/**
	 * Next function.
	 *
	 * @param f_x the f x
	 * @return the string
	 * @throws CalculatorException the calculator exception
	 */
	private String nextFunction(String f_x) throws CalculatorException {
		String result = "";
		f_x = f_x.trim().toLowerCase();

		for (int i = 0; i < f_x.length(); i++) {
			final char character = f_x.charAt(i);

			if (character == '+' || character == '*' || character == '-' || character == '/') {
				i = f_x.length();
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f_x = f_x.substring(i, nextBracket(f_x) + 1);
				result = result + new_f_x;
				i = (i + new_f_x.length()) - 1;
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
	 * Next minus function.
	 *
	 * @param f_x the f x
	 * @return the string
	 * @throws CalculatorException the calculator exception
	 */
	private String nextMinusFunction(final String f_x) throws CalculatorException {
		String result = "";
		for (int i = 0; i < f_x.length(); i++) {
			final char character = f_x.charAt(i);

			if (character == '+') {
				i = f_x.length();
			} else if (character == '*') {
				result = result + character;
			} else if (character == '-') {
				i = f_x.length();
			} else if (character == '/') {
				result = result + character;
			} else if (character == '^') {
				result = result + character;
			} else if (character == '.') {
				result = result + character;
			} else if (character == '(') {
				final String new_f_x = f_x.substring(i, nextBracket(f_x) + 1);
				result = result + new_f_x;
				i = (i + new_f_x.length()) - 1;
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
	 * @param f_x f(x)
	 * @return the int
	 * @throws CalculatorException the calculator exception
	 */
	private int nextBracket(final String f_x) throws CalculatorException {
		int result = 0;
		int count = 0;
		for (int i = 0; i < f_x.length(); i++) {
			final char character = f_x.charAt(i);
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
