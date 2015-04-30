package com.expression.parser.function;

import com.expression.parser.ParserManager;
import com.expression.parser.exception.CalculatorException;

public class FunctionX {

    public static final String SIN = "sin";
    public static final String COS = "cos";
    public static final String SINH = "sinh";
    public static final String COSH = "cosh";
    public static final String TAN = "tan";
    public static final String TANH = "tanh";
    public static final String ASIN = "asin";
    public static final String ACOS = "acos";
    public static final String ATAN = "atan";
    public static final String E = "e";
    public static final String PI = "pi";
    public static final String LN = "ln";
    public static final String LOG = "log";
    public static final String SQRT = "sqrt";
    public static final String CBRT = "cbrt";

    /**
     * setup
     */
    private boolean degree = false;

    /**
     * f(x)
     */
    private String f_x;

    /**
     * FunctionX
     * 
     * @param f_x
     *            f(x)
     */
    public FunctionX(final String f_x) {
        this.f_x = f_x.trim().replaceAll(" ", "");
        this.degree = ParserManager.getInstance().isDeegre();
    }

    /**
     * 
     * getter f(x)
     * 
     * @return
     */
    public String getF_x() {
        return this.f_x;
    }

    /**
     * setter f(x)
     * 
     * 
     * @param f_x
     */
    public void setF_x(final String f_x) {
        this.f_x = f_x;
    }

    /**
     * 
     * get f(x0)
     * 
     * @param xo
     *            point
     * @return
     * @throws CalculatorException
     */
    public double getF_xo(final double xo) throws CalculatorException {

        return eval(this.f_x, xo);
    }

    /**
     * 
     * eval
     * 
     * @param f_x
     * @param xi
     * @return
     * @throws CalculatorException
     */
    private double eval(String f_x, final double xi) throws CalculatorException {
        f_x = f_x.trim().toLowerCase();
        double value = 0;
        String number = "";
        String function = "";
        boolean hasNumber = false;
        boolean hasFunction = false;

        for (int i = 0; i < f_x.length(); i++) {
            final char character = f_x.charAt(i);
            switch (character) {
            case '*':
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
                break;
            case '+':

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
                break;

            case '-':

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
                break;
            case '/':

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
                break;
            case '^':

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

                break;
            case '0':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }

                break;
            case '1':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '2':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '3':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }

                break;
            case '4':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '5':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '6':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '7':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }

                break;
            case '8':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }
                break;
            case '9':
                hasNumber = true;
                number = number + character;
                if (i == (f_x.length() - 1)) {
                    value = new Double(number).doubleValue();
                    number = "";
                    hasNumber = false;
                }

                break;
            case '.':
                if (i == (f_x.length() - 1)) {
                    throw new CalculatorException("The function is not well-formed");
                }
                if (hasNumber && (number.length() > 0)) {
                    number = number + character;
                }
                break;
            case '(':
                if (i == (f_x.length() - 1)) {
                    throw new CalculatorException("The function is not well-formed");
                }

                final String new_f_x = f_x.substring(i + 1, nextBracket(f_x));
                if (hasFunction) {
                    if (function.equals(SIN)) {
                        if (this.degree) {
                            value = Math.sin(Math.toRadians(eval(new_f_x, xi)));
                        } else {
                            value = Math.sin(eval(new_f_x, xi));
                        }

                    } else if (function.equals(COS)) {
                        if (this.degree) {
                            value = Math.cos(Math.toRadians(eval(new_f_x, xi)));
                        } else {
                            value = Math.cos(eval(new_f_x, xi));
                        }
                    } else if (function.equals(TAN)) {
                        if (this.degree) {
                            value = Math.tan(Math.toRadians(eval(new_f_x, xi)));
                        } else {
                            value = Math.tan(eval(new_f_x, xi));
                        }

                    } else if (function.equals(SINH)) {
                        value = Math.sinh(eval(new_f_x, xi));

                    } else if (function.equals(COSH)) {
                        value = Math.cosh(eval(new_f_x, xi));

                    } else if (function.equals(TANH)) {
                        value = Math.tanh(eval(new_f_x, xi));

                    } else if (function.equals(ASIN)) {
                        if (this.degree) {
                            value = Math.asin(eval(new_f_x, xi)) * (180 / Math.PI);
                        } else {
                            value = Math.asin(eval(new_f_x, xi));
                        }
                    } else if (function.equals(ACOS)) {
                        if (this.degree) {
                            value = Math.acos(eval(new_f_x, xi)) * (180 / Math.PI);
                        } else {
                            value = Math.acos(eval(new_f_x, xi));
                        }
                    } else if (function.equals(ATAN)) {
                        if (this.degree) {
                            value = Math.atan(eval(new_f_x, xi)) * (180 / Math.PI);
                        } else {
                            value = Math.atan(eval(new_f_x, xi));
                        }
                    } else if (function.equals(LN)) {
                        value = Math.log(eval(new_f_x, xi));
                    } else if (function.equals(LOG)) {
                        value = Math.log10(eval(new_f_x, xi));
                    } else if (function.equals(SQRT)) {
                        value = Math.sqrt(eval(new_f_x, xi));
                    } else if (function.equals(CBRT)) {
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

                break;
            case ')':
                throw new CalculatorException(" '(' is not finished ");

            case ' ':
                break;
            default:
                if (isValidCharacter(character)) {
                    function = function + character;
                    hasFunction = true;

                    if (i == (f_x.length() - 1)) {

                        if (function.equals(E)) {
                            value = Math.E;

                        } else if (function.equals(PI)) {
                            value = Math.PI;
                        } else {
                            if (function.length() == 1) {
                                value = xi;
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

    private String nextFunction(String f_x) throws CalculatorException {
        String result = "";
        f_x = f_x.trim().toLowerCase();

        for (int i = 0; i < f_x.length(); i++) {
            final char character = f_x.charAt(i);

            switch (character) {
            case '*':
                i = f_x.length();
                break;
            case '/':
                i = f_x.length();
                break;
            case '+':
                i = f_x.length();
                break;
            case '-':
                i = f_x.length();
                break;
            case '^':
                result = result + character;
                break;
            case '.':
                result = result + character;
                break;
            case '(':

                final String new_f_x = f_x.substring(i, nextBracket(f_x) + 1);
                result = result + new_f_x;
                i = (i + new_f_x.length()) - 1;

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

    private String nextMinusFunction(String f_x) throws CalculatorException {
        String result = "";
        f_x = f_x.trim().toLowerCase();

        for (int i = 0; i < f_x.length(); i++) {
            final char character = f_x.charAt(i);

            switch (character) {
            case '*':
                result = result + character;
                break;
            case '/':
                result = result + character;
                break;
            case '+':
                i = f_x.length();
                break;
            case '-':
                i = f_x.length();
                break;
            case '^':
                result = result + character;
                break;
            case '.':
                result = result + character;
                break;
            case '(':

                final String new_f_x = f_x.substring(i, nextBracket(f_x) + 1);
                result = result + new_f_x;
                i = (i + new_f_x.length()) - 1;

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
     * 
     * isValidCharacter
     * 
     * @param character
     * @return
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
     * 
     * isValidNumericAndCharacter
     * 
     * @param character
     * @return
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
     * 
     * nextBracket
     * 
     * @param f_x
     *            f(x)
     * @return
     * @throws CalculatorException
     */
    private int nextBracket(final String f_x) throws CalculatorException {
        int result = 0;
        int count = 0;
        for (int i = 0; i < f_x.length(); i++) {
            final char character = f_x.charAt(i);
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
