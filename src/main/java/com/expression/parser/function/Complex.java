package com.expression.parser.function;

import com.expression.parser.exception.CalculatorException;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Complex {

    /**
     * real
     */
    private double r;

    /**
     * imaginary
     */
    private double i;

    /**
     * Complex
     * 
     * @param r
     * @param i
     */
    public Complex(final double r, final double i) {
        this.r = r;
        this.i = i;
    }

    /**
     * Complex
     */
    public Complex() {
        this.r = 0.0;
        this.i = 0.0;
    }

    public double getR() {
        return this.r;
    }

    public void setR(final double r) {
        this.r = r;
    }

    public double getI() {

        return this.i;
    }

    public void setI(final double i) {
        this.i = i;
    }

    /**
     * 
     * add
     * 
     * @param a
     * @param b
     * @return
     */
    public static Complex add(final Complex a, final Complex b) {
        final double real = a.r + b.r;
        final double imag = a.i + b.i;
        return new Complex(real, imag);
    }

    /**
     * 
     * add
     * 
     * @param real
     * @param c
     * @return
     */
    public static Complex add(final double real, final Complex c) {
        return new Complex(c.r + real, c.i);
    }

    /**
     * 
     * sub
     * 
     * @param a
     * @param b
     * @return
     */
    public static Complex sub(final Complex a, final Complex b) {
        final double real = a.r - b.r;
        final double imag = a.i - b.i;
        return new Complex(real, imag);
    }

    public static Complex sub(final double real, final Complex c) {
        return new Complex(c.r - real, c.i);
    }

    /**
     * 
     * multiply
     * 
     * @param a
     * @param b
     * @return
     */
    public static Complex mul(final Complex a, final Complex b) {
        final double real = (a.r * b.r) - (a.i * b.i);
        final double imag = (a.i * b.r) + (a.r * b.i);
        return new Complex(real, imag);
    }

    /**
     * 
     * conjugate
     * 
     * @param c
     * @return
     */
    public static Complex conjugate(final Complex c) {
        return new Complex(c.r, -c.i);
    }

    /**
     * 
     * div
     * 
     * @param a
     * @param b
     * @return
     * @throws CalculatorException
     */
    public static Complex div(final Complex a, final Complex b) throws CalculatorException {

        if ((b.r == 0) && (b.i == 0)) {
            throw new CalculatorException("The complex number b is 0");
        }

        final double c = Math.pow(b.r, 2);
        final double d = Math.pow(b.i, 2);

        double real;
        double imag;
        real = (a.r * b.r) + (a.i * b.i);
        real /= (c + d);
        imag = (a.i * b.r) - (a.r * b.i);
        imag /= (c + d);

        return new Complex(real, imag);
    }

    /**
     * 
     * abs
     * 
     * @param z
     * @return
     */
    public static double abs(final Complex z) {
        double x, y, ans, temp;
        x = Math.abs(z.r);
        y = Math.abs(z.i);
        if (x == 0.0) {
            ans = y;
        } else if (y == 0.0) {
            ans = x;
        } else if (x > y) {
            temp = y / x;
            ans = x * Math.sqrt(1.0 + (temp * temp));
        } else {
            temp = x / y;
            ans = y * Math.sqrt(1.0 + (temp * temp));
        }
        return ans;
    }

    /**
     * 
     * sqrt
     * 
     * @param c
     * @return
     */
    public static Complex sqrt(final Complex c) {
        double real, imag;
        double x, y, w, r;

        Complex result = null;

        if ((c.r == 0.0) && (c.i == 0.0)) {
            result = new Complex();
        } else {
            x = Math.abs(c.r);
            y = Math.abs(c.i);
            if (x >= y) {
                r = y / x;
                w = Math.sqrt(x) * Math.sqrt(0.5 * (1.0 + Math.sqrt(1.0 + (r * r))));
            } else {
                r = x / y;
                w = Math.sqrt(y) * Math.sqrt(0.5 * (r + Math.sqrt(1.0 + (r * r))));
            }
            if (c.r >= 0.0) {
                real = w;
                imag = c.i / (2.0 * w);
            } else {
                imag = (c.i >= 0) ? w : -w;
                real = c.i / (2.0 * imag);
            }
            result = new Complex(real, imag);
        }

        return result;
    }

    /**
     * 
     * Complex
     * 
     * @param x
     * @param c
     * @return
     */
    public static Complex mul(final double x, final Complex c) {
        final Complex result = new Complex();
        result.r = c.r * x;
        result.i = c.i * x;
        return result;
    }

    /**
     * 
     * div
     * 
     * @param x
     * @param c
     * @return
     * @throws CalculatorException
     */
    public static Complex div(final double x, final Complex c) throws CalculatorException {
        if (x == 0) {
            throw new CalculatorException("scalar is 0");
        }
        final Complex result = new Complex();
        result.r = c.r / x;
        result.i = c.i / x;
        return result;
    }

    /**
     * 
     * inverse
     * 
     * @return
     */
    public Complex inverse() {
        final Complex result = new Complex();
        final double a = this.r * this.r;
        final double b = this.i * this.i;
        if ((a == 0) && (b == 0)) {
            result.r = 0;
            result.i = 0;
        } else {
            result.r = this.r / (a + b);
            result.i = this.i / (a + b);
        }
        return result;

    }

    /**
     * 
     * pow
     * 
     * @param c
     * @param exp
     * @return
     * @throws CalculatorException
     */
    /*
     * public static Complex pow(final Complex c, final int exp) throws CalculatorException { double x = 0.0, y = 0.0;
     * int sign; for (int i = 0; i <= exp; i++) { sign = ((i % 2) == 0) ? 1 : -1; // real x += Combination.calc(exp, 2 *
     * i) * Math.pow(c.r, exp - (2 * i)) * Math.pow(c.i, 2 * i) * sign; if (exp == (2 * i)) { break; } // imaginary y +=
     * Combination.calc(exp, (2 * i) + 1) * Math.pow(c.r, exp - ((2 * i) + 1)) * Math.pow(c.i, (2 * i) + 1) sign; }
     * return new Complex(x, y); }
     */
    /**
     * 
     * pow
     * 
     * @param c
     * @param exp
     * @return
     */
    public static Complex pow(final Complex c, final Double exp) {
        return c.pow(exp);
    }

    /**
     * 
     * power
     * 
     * @param c
     * @param exp
     * @return
     */
    public static Complex pow(final Complex c, final Complex exp) {
        return c.pow(exp);
    }

    /**
     * 
     * module
     * 
     * @return
     */
    public double module() {
        return Math.sqrt((this.r * this.r) + (this.i * this.i));
    }

    /**
     * 
     * arg
     * 
     * @return
     */
    public double arg() {

        double angle = Math.atan2(this.i, this.r);
        if (angle < 0) {
            angle = (2 * Math.PI) + angle;
        }
        return (angle * 180) / Math.PI;

    }

    /**
     * 
     * negate
     * 
     * @return
     */
    public Complex negate() {
        return new Complex(-this.r, -this.i);
    }

    /**
     * 
     * exp
     * 
     * @return
     */
    // E^c
    public Complex exp() {
        final double exp_x = Math.exp(this.r);
        return new Complex(exp_x * Math.cos(this.i), exp_x * Math.sin(this.i));
    }

    /**
     * 
     * log10()
     * 
     * @return
     */
    public Complex log10() {

        final double rpart = Math.sqrt((this.r * this.r) + (this.i * this.i));
        double ipart = Math.atan2(this.i,
                this.r);
        if (ipart > Math.PI) {
            ipart = ipart - (2.0 * Math.PI);
        }
        return new Complex(Math.log10(rpart), (1 /
                Math.log(10)) * ipart);

    }

    /**
     * 
     * log natural log
     * 
     * @return
     */
    public Complex log() {
        return new Complex(Math.log(abs(this)), Math.atan2(this.i, this.r));

    }

    /**
     * 
     * sqrt
     * 
     * @return
     */
    public Complex sqrt() {
        final double r = Math.sqrt((this.r * this.r) + (this.i * this.i));
        final double rpart = Math.sqrt(0.5 * (r + this.r));
        double ipart = Math.sqrt(0.5 * (r - this.r));
        if (this.i < 0.0) {
            ipart = -ipart;
        }
        return new Complex(rpart, ipart);
    }

    public static Complex cbrt(final Complex a) {
        Complex z = new Complex();
        if (a.i != 0.0) {
            z.r = Math.cbrt(abs(a)) * Math.cos(a.arg() / 3.0);
            z.i = Math.cbrt(abs(a)) * Math.sin(a.arg() / 3.0);
        } else {
            z = new Complex(Math.cbrt(a.r), 0);
        }
        return z;
    }

    /**
     * 
     * pow
     * 
     * @param z
     * @return
     */
    public Complex pow(final Complex exp) {
        Complex a = this.log();
        a = mul(exp, a);
        return a.exp();
    }

    /**
     * 
     * pow
     * 
     * @param d
     * @return
     */
    public Complex pow(final double exp) {
        Complex a = this.log();
        a = mul(exp, a);
        return a.exp();
    }

    /**
     * 
     * sin
     * 
     * @return
     */
    public Complex sin() {
        return new Complex(Math.sin(this.r) * Math.cosh(this.i), Math.cos(this.r) * Math.sinh(this.i));
    }

    /**
     * 
     * cos
     * 
     * @return
     */
    public Complex cos() {
        return new Complex(Math.cos(this.r) * Math.cosh(this.i), -StrictMath.sin(this.r) * Math.sinh(this.i));
    }

    /**
     * 
     * tan
     * 
     * @return
     * @throws CalculatorException
     */
    public Complex tan() throws CalculatorException {
        return div(this.sin(), this.cos());
    }

    /**
     * 
     * asin
     * 
     * @return
     */
    public Complex asin() {
        final Complex IM = new Complex(0.0, -1.0);
        final Complex ZP = mul(this, IM);
        final Complex ZM = add((sub(new Complex(1.0, 0.0), mul(this, this))).sqrt(), ZP);
        return mul(ZM.log(), new Complex(0.0, 1.0));
    }

    /**
     * 
     * acos
     * 
     * @return
     */
    public Complex acos() {
        final Complex IM = new Complex(0.0, -1.0);
        final Complex ZM = add(mul((sub(new Complex(1.0, 0.0), mul(this, this))).sqrt(), IM), this);
        return mul(ZM.log(), new Complex(0.0, 1.0));
    }

    /**
     * 
     * atan
     * 
     * @return
     * @throws CalculatorException
     */
    public Complex atan() throws CalculatorException {
        final Complex IM = new Complex(0.0, -1.0);
        final Complex ZP = new Complex(this.r, this.i - 1.0);
        final Complex ZM = new Complex(-this.r, -this.i - 1.0);
        return div(2.0, mul(IM, (div(ZP, ZM).log())));
    }

    /**
     * 
     * sinh
     * 
     * @return
     */
    public Complex sinh() {
        return new Complex(Math.sinh(this.r) * Math.cos(this.i), Math.cosh(this.r) * Math.sin(this.i));
    }

    /**
     * 
     * cosh
     * 
     * @return
     */
    public Complex cosh() {
        return new Complex(Math.cosh(this.r) * Math.cos(this.i), Math.sinh(this.r) * Math.sin(this.i));
    }

    /**
     * 
     * tanh
     * 
     * @return
     * @throws CalculatorException
     */
    public Complex tanh() throws CalculatorException {
        return div(this.sinh(), this.cosh());
    }

    /**
     * 
     * atanh
     * 
     * @return
     * @throws CalculatorException
     */
    public Complex atanh() throws CalculatorException {
        return sub((add(1.0, this)).log(), div(2.0, ((sub(1.0, this)).negate()).log()));
    }

}
