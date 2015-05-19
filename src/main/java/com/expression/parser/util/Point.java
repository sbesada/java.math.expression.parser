package com.expression.parser.util;

import com.expression.parser.function.Complex;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Point {

    private String var;

    private Double value;

    private Complex complexValue;

    private boolean complex;

    public Point() {

    }

    public Point(final String var, final Double value) {
        this.var = var;
        this.value = value;
        this.complex = false;
    }

    public Point(final String var, final Complex value) {
        this.var = var;
        this.complexValue = value;
        this.complex = true;
    }

    /**
     * getter var
     * 
     * 
     * @return
     */
    public String getVar() {
        return this.var;
    }

    /**
     * 
     * setter var
     * 
     * @param var
     */
    public void setVar(final String var) {
        this.var = var;
    }

    /**
     * 
     * getter Value
     * 
     * @return
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * 
     * setter Value
     * 
     * @param value
     */
    public void setValue(final Double value) {
        this.value = value;
    }

    /**
     * 
     * getter complexValue
     * 
     * @return
     */
    public Complex getComplexValue() {
        return this.complexValue;
    }

    /**
     * 
     * setter complexValue
     * 
     * @param complexValue
     */
    public void setComplexValue(final Complex complexValue) {
        this.complexValue = complexValue;
    }

    /**
     * 
     * isComplex
     * 
     * @return
     */
    public boolean isComplex() {
        return this.complex;
    }

    /**
     * 
     * setter complex
     * 
     * @param complex
     */
    public void setComplex(final boolean complex) {
        this.complex = complex;
    }

}
