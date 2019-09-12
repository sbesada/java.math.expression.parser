package com.expression.parser.function;

import java.util.ArrayList;
import java.util.List;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

// TODO: Auto-generated Javadoc
/**
 * The Class ComplexFunction.
 */
public class ComplexFunction {

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
	public ComplexFunction(final String f) {
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
	public Complex getValue(final List<Complex> values, final List<String> variables) throws CalculatorException {
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
	 * @return the complex
	 * @throws CalculatorException the calculator exception
	 */
	private Complex eval(String f, final List<Complex> values, final List<String> variables)
			throws CalculatorException {
		f = f.trim().toLowerCase();
		Complex value = new Complex(0, 0);
		String number = "";
		String function = "";

		boolean hasNumber = false;
		boolean hasFunction = false;
		boolean isImaginary = false;

		for (int i = 0; i < f.length(); i++) {
			final char character = f.charAt(i);
			switch (character) {
			case '*':
				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(new Complex(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";

				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(new Complex(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";
				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.mul(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}
				break;
			case '+':

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(new Complex(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(new Complex(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";
				} else if (hasFunction) {
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = f.substring(i + 1, f.length());
					value = Complex.add(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}
				break;

			case '-':

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(new Complex(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(new Complex(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextMinusFunction(f.substring(i + 1, f.length()));
					value = Complex.sub(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}
				break;
			case '/':

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(new Complex(numb, 0), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(new Complex(0, numb), eval(new_f, values, variables));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.div(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}
				break;
			case '^':

				if (hasNumber && !isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(new_f, values, variables), numb);
					i = i + new_f.length();
					hasNumber = false;
					number = "";
				} else if (hasNumber && isImaginary) {
					final Double numb = new Double(number);
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(new_f, values, variables), new Complex(0, numb));
					i = i + new_f.length();
					hasNumber = false;
					isImaginary = false;
					number = "";

				} else if (hasFunction) {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(eval(function, values, variables), eval(new_f, values, variables));
					i = i + new_f.length();
					hasFunction = false;
					function = "";

				} else {
					final String new_f = nextFunction(f.substring(i + 1, f.length()));
					value = Complex.pow(value, eval(new_f, values, variables));
					i = i + new_f.length();
				}

				break;
			case '0':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}

				break;
			case '1':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '2':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '3':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}

				break;
			case '4':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '5':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '6':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '7':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}

				break;
			case '8':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
					number = "";
					hasNumber = false;
				}
				break;
			case '9':
				hasNumber = true;
				number = number + character;
				if (i == (f.length() - 1)) {
					value = new Complex(new Double(number), 0);
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
						value = eval(new_f, values, variables).sin();

					} else if (function.equals(COS)) {
						value = eval(new_f, values, variables).cos();

					} else if (function.equals(TAN)) {
						value = eval(new_f, values, variables).tan();

					} else if (function.equals(SINH)) {
						value = eval(new_f, values, variables).sinh();

					} else if (function.equals(COSH)) {
						value = eval(new_f, values, variables).cosh();

					} else if (function.equals(TANH)) {
						value = eval(new_f, values, variables).tanh();

					} else if (function.equals(ASIN)) {
						value = eval(new_f, values, variables).asin();

					} else if (function.equals(ACOS)) {
						value = eval(new_f, values, variables).acos();

					} else if (function.equals(ATAN)) {
						value = eval(new_f, values, variables).atan();
					} else if (function.equals(LN)) {
						value = eval(new_f, values, variables).log();
					} else if (function.equals(LOG)) {
						value = eval(new_f, values, variables).log10();
					} else if (function.equals(SQRT)) {
						value = eval(new_f, values, variables).sqrt();
					} else if (function.equals(CBRT)) {
						value = Complex.cbrt(eval(new_f, values, variables));
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

			case 'i':
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

						if (function.equals(E)) {
							value = new Complex(Math.E, 0);

						} else if (function.equals(PI)) {
							value = new Complex(Math.PI, 0);
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

					break;
				}
				break;

			case 'j':
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

						if (function.equals(E)) {
							value = new Complex(Math.E, 0);

						} else if (function.equals(PI)) {
							value = new Complex(Math.PI, 0);
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

					break;
				}
				break;
			default:
				if (isValidCharacter(character)) {
					function = function + character;
					hasFunction = true;

					if (i == (f.length() - 1)) {

						if (function.equals(E)) {
							value = new Complex(Math.E, 0);

						} else if (function.equals(PI)) {
							value = new Complex(Math.PI, 0);
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
		// case 'i':
		// result = true;
		// break;
		// case 'j':
		// result = true;
		// break;
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
