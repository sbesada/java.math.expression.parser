package com.expression.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.expression.parser.exception.CalculatorException;
import com.expression.parser.function.FunctionX;
import com.expression.parser.function.FunctionXs;
import com.expression.parser.util.Point;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Parser {

    public static double Eval(final String function) {

        double result = 0;
        FunctionX f_x = null;

        if ((function != null) && !function.equals("")) {
            try {
                f_x = new FunctionX(function);
                result = f_x.getF_xo(0);

            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;

    }

    /**
     * 
     * Eval
     * 
     * @param function
     * @param vars
     * @param values
     * @return
     */
    public static double Eval(final String function, final String[] vars, final Double[] values) {

        double result = 0;
        FunctionX f_x = null;
        FunctionXs f_xs = null;
        if ((function != null) && !function.equals("")) {
            try {
                if ((((vars == null) || (vars.length < 1)) && (values == null)) || (values.length < 1)) {
                    f_x = new FunctionX(function);
                    result = f_x.getF_xo(0);
                } else if ((values != null) && (values.length == 1)) {
                    f_x = new FunctionX(function);
                    result = f_x.getF_xo(values[0]);
                } else if ((vars != null) && (vars.length > 1) && (values != null) && (values.length > 1)) {
                    f_xs = new FunctionXs(function);
                    final List<Double> valuesList = Arrays.asList(values);
                    final List<String> varsList = Arrays.asList(vars);
                    result = f_xs.getValue(valuesList, varsList);
                }

            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;

    }

    /**
     * 
     * Eval
     * 
     * @param function
     * @param values
     * @return
     */
    public static double Eval(final String function, final Point... values) {

        double result = 0;
        FunctionX f_x = null;
        FunctionXs f_xs = null;

        if ((function != null) && !function.equals("")) {
            try {

                if (values != null) {
                    if (values.length == 1) {
                        f_x = new FunctionX(function);
                        result = f_x.getF_xo(values[0].getValue());
                    } else if (values.length > 1) {
                        f_xs = new FunctionXs(function);
                        final List<Double> valuesList = PointToValue(values);
                        final List<String> varsList = PointToVar(values);
                        result = f_xs.getValue(valuesList, varsList);
                    }

                } else {
                    f_x = new FunctionX(function);
                    result = f_x.getF_xo(0);
                }
            }

            catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 
     * PointToValue
     * 
     * @param values
     * @return
     */
    private static List<Double> PointToValue(final Point... values) {
        final List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < values.length; i++) {
            result.add(values[i].getValue());
        }
        return result;
    }

    /**
     * 
     * PointToVar
     * 
     * @param values
     * @return
     */
    private static List<String> PointToVar(final Point... values) {
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < values.length; i++) {
            result.add(values[i].getVar());
        }
        return result;
    }
}
