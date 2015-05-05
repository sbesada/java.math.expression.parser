# java.math.expression.parser
java.math.expression.parser is a maven project that let you parse math expresions.

This algorithm does not use a decision tree. It is a recursive algorithm.

This algorithm is faster than JEP math expresion parser!!!

Here you can see an example:


        String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";
        
        final Point xo = new Point("x", new Double(2));
        final Point zo = new Point("z", new Double(1));

        double result = Parser.Eval(f_xs, xo, zo);
        

In the test package you can see more examples.

This version is compiled for Java 1.6
        

Enjoy!!
