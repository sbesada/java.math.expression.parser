package com.expression.parser.util;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Point {

    private String var;

    private Double value;

    public Point() {

    }

    public Point(final String var, final Double value) {
        this.var = var;
        this.value = value;
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

}
