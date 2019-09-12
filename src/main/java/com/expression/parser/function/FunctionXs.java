package com.expression.parser.function;

import java.util.ArrayList;
import java.util.List;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

/**
 * The Class FunctionXs.
 */
public class FunctionXs {

	/** The Constant SIN. */
	public static final String SIN = "sin";

	/** The Constant COS. */
	public static final String COS = "cos";

	/** The Constant SINH. */
	public static final String SINH = "sinh";

	/** The Constant COSH. */
	public static final String COSH = "cosh";

	/** The Constant TAN. */
	public static final String TAN = "tan";

	/** The Constant TANH. */
	public static final String TANH = "tanh";

	/** The Constant ASIN. */
	public static final String ASIN = "asin";

	/** The Constant ACOS. */
	public static final String ACOS = "acos";

	/** The Constant ATAN. */
	public static final String ATAN = "atan";

	/** The Constant E. */
	public static final String E = "e";

	/** The Constant PI. */
	public static final String PI = "pi";

	/** The Constant LN. */
	public static final String LN = "ln";

	/** The Constant LOG. */
	public static final String LOG = "log";

	/** The Constant SQRT. */
	public static final String SQRT = "sqrt";

	/** The Constant CBRT. */
	public static final String CBRT = "cbrt";

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
		this.f = f.trim().replaceAll(" ", "");
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
			vars.add(string.toLowerCase());
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
	private double eval(String f, final List<Double> values, final List<String> variables) throws CalculatorException {
		f = f.trim().toLowerCase();
		double value = 0;
		String number = "";
		String function = "";
		boolean hasNumber = false;
		boolean hasFunction = false;

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);
			switch (character) {
			case '*':
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
				break;
			case '+':

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
				break;

			case '-':

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
				break;
			case '/':

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
				break;
			case '^':

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

				break;
			case '0':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

				break;
			case '1':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '2':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '3':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

				break;
			case '4':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '5':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '6':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '7':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

				break;
			case '8':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}
				break;
			case '9':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Double(number).doubleValue();
					number = "";
					hasNumber = false;
				}

				break;
			case '.':
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}
				if (hasNumber && (number.length() > 0)) {
					number = number + character;
				}
				break;
			case '(':
				if (i == (f.length() - 1)) {
					throw new CalculatorException("The function is not well-formed");
				}

				final String new_f = f.substring(i + 1, nextBracket(f));
				if (hasFunction) {
					if (function.equals(SIN)) {
						if (degree) {
							value = Math.sin(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.sin(eval(new_f, values, variables));
						}

					} else if (function.equals(COS)) {
						if (degree) {
							value = Math.cos(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.cos(eval(new_f, values, variables));
						}
					} else if (function.equals(TAN)) {
						if (degree) {
							value = Math.tan(Math.toRadians(eval(new_f, values, variables)));
						} else {
							value = Math.tan(eval(new_f, values, variables));
						}

					} else if (function.equals(SINH)) {
						value = Math.sinh(eval(new_f, values, variables));

					} else if (function.equals(COSH)) {
						value = Math.cosh(eval(new_f, values, variables));

					} else if (function.equals(TANH)) {
						value = Math.tanh(eval(new_f, values, variables));

					} else if (function.equals(ASIN)) {
						if (degree) {
							value = Math.asin(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.asin(eval(new_f, values, variables));
						}
					} else if (function.equals(ACOS)) {
						if (degree) {
							value = Math.acos(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.acos(eval(new_f, values, variables));
						}
					} else if (function.equals(ATAN)) {
						if (degree) {
							value = Math.atan(eval(new_f, values, variables)) * (180 / Math.PI);
						} else {
							value = Math.atan(eval(new_f, values, variables));
						}
					} else if (function.equals(LN)) {
						value = Math.log(eval(new_f, values, variables));
					} else if (function.equals(LOG)) {
						value = Math.log10(eval(new_f, values, variables));
					} else if (function.equals(SQRT)) {
						value = Math.sqrt(eval(new_f, values, variables));
					} else if (function.equals(CBRT)) {
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

				break;
			case ')':
				throw new CalculatorException(" '(' is not finished ");

			case ' ':
				break;
			default:
				if (isValidCharacter(character)) {
					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (function.equals(E)) {
							value = Math.E;

						} else if (function.equals(PI)) {
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
					throw new CalculatorException("Invalid character");
				}

				break;
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
	private String nextFunction(String f) throws CalculatorException {
		String result = "";
		f = f.trim().toLowerCase();

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);

			switch (character) {
			case '*':
				i = f.length();
				break;
			case '/':
				i = f.length();
				break;
			case '+':
				i = f.length();
				break;
			case '-':
				i = f.length();
				break;
			case '^':
				result = result + character;
				break;
			case '.':
				result = result + character;
				break;
			case '(':

				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;

				break;
			case ')':
				throw new CalculatorException(" '(' is not finished ");

			case ' ':
				result = result + character;
				break;

			default:
				if (isValidNumericAndCharacter(character)) {
					result = result + character;
				} else {
					throw new CalculatorException("Invalid character");
				}
				break;
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

			switch (character) {
			case '*':
				result = result + character;
				break;
			case '/':
				result = result + character;
				break;
			case '+':
				i = f.length();
				break;
			case '-':
				i = f.length();
				break;
			case '^':
				result = result + character;
				break;
			case '.':
				result = result + character;
				break;
			case '(':

				final String new_f = f.substring(i, nextBracket(f) + 1);
				result = result + new_f;
				i = (i + new_f.length()) - 1;

				break;
			case ')':
				throw new CalculatorException(" '(' is not finished ");

			case ' ':
				result = result + character;
				break;

			default:
				if (isValidNumericAndCharacter(character)) {
					result = result + character;
				} else {
					throw new CalculatorException("Invalid character");
				}
				break;
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
		switch (character) {
		case 'a':
			result = true;
			break;
		case 'b':
			result = true;
			break;
		case 'c':
			result = true;
			break;
		case 'd':
			result = true;
			break;
		case 'e':
			result = true;
			break;
		case 'f':
			result = true;
			break;
		case 'g':
			result = true;
			break;
		case 'h':
			result = true;
			break;
		case 'i':
			result = true;
			break;
		case 'j':
			result = true;
			break;
		case 'k':
			result = true;
			break;

		case 'l':
			result = true;
			break;
		case 'm':
			result = true;
			break;
		case 'n':
			result = true;
			break;
		case 'o':
			result = true;
			break;
		case 'p':
			result = true;
			break;
		case 'q':
			result = true;
			break;
		case 'r':
			result = true;
			break;
		case 's':
			result = true;
			break;
		case 't':
			result = true;
			break;
		case 'u':
			result = true;
			break;
		case 'v':
			result = true;
			break;
		case 'w':
			result = true;
			break;
		case 'x':
			result = true;
			break;
		case 'y':
			result = true;
			break;
		case 'z':
			result = true;
			break;
		default:
			result = false;
			break;
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
		switch (character) {
		case 'a':
			result = true;
			break;
		case 'b':
			result = true;
			break;
		case 'c':
			result = true;
			break;
		case 'd':
			result = true;
			break;
		case 'e':
			result = true;
			break;
		case 'f':
			result = true;
			break;
		case 'g':
			result = true;
			break;
		case 'h':
			result = true;
			break;
		case 'i':
			result = true;
			break;
		case 'j':
			result = true;
			break;
		case 'k':
			result = true;
			break;

		case 'l':
			result = true;
			break;
		case 'm':
			result = true;
			break;
		case 'n':
			result = true;
			break;
		case 'o':
			result = true;
			break;
		case 'p':
			result = true;
			break;
		case 'q':
			result = true;
			break;
		case 'r':
			result = true;
			break;
		case 's':
			result = true;
			break;
		case 't':
			result = true;
			break;
		case 'u':
			result = true;
			break;
		case 'v':
			result = true;
			break;
		case 'w':
			result = true;
			break;
		case 'x':
			result = true;
			break;
		case 'y':
			result = true;
			break;
		case 'z':
			result = true;
			break;
		case '0':
			result = true;
			break;
		case '1':
			result = true;
			break;
		case '2':
			result = true;
			break;
		case '3':
			result = true;
			break;
		case '4':
			result = true;
			break;
		case '5':
			result = true;
			break;
		case '6':
			result = true;
			break;
		case '7':
			result = true;
			break;
		case '8':
			result = true;
			break;
		case '9':
			result = true;
			break;
		default:
			result = false;
			break;
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
			switch (character) {
			case '(':
				result = i;
				count++;
				break;
			case ')':
				result = i;
				count--;
				if (count == 0) {
					return i;
				}
				break;
			default:
				result = i;
				break;
			}
		}

		if (count != 0) {
			throw new CalculatorException("( is not finished");
		}
		return result;
	}

}
