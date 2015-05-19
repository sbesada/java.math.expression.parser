package com.expression.parser.util;

import com.expression.parser.function.Complex;

public class ParserResult {

    private Double value;

    private Complex complexValue;

    private boolean complex;

    public Double getValue() {
        return this.value;
    }

    public void setValue(final Double value) {
        this.value = value;
        this.complex = false;
    }

    public Complex getComplexValue() {
        return this.complexValue;
    }

    public void setComplexValue(final Complex complexValue) {
        this.complexValue = complexValue;
        this.complex = true;
    }

    public boolean isComplex() {
        return this.complex;
    }

    public void setComplex(final boolean complex) {
        this.complex = complex;
    }

}
