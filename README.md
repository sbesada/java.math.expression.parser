# java.math.expression.parser
java math expression parser is a maven project that let you parse math expressions.

This algorithm does not use a decision tree. It is a recursive algorithm.

This algorithm is faster than JEP math expresion parser!!! If you compare java.math.expression.parse and JEP, this algorithm only need 25% of the time to parse the same expression as JEP. With other algorithms that use trees like:

                                                                                       ---------
                                                                                       |   +   |
                                                                                       ---------
                                                                                           |
                                                                                    ---------------
                                                                                    |             |
                                                                                ---------     ---------
                                                                                |   1   |     |   *   |
                                                                                ---------     ---------
                                                                                
 It is even faster than them. This library is 10 times faster and it is tested using matlab.
                                    
Here you can see an example:


        String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";
        
        final Point xo = new Point("x", new Double(2));
        final Point zo = new Point("z", new Double(1));

        double result = Parser.Eval(f_xs, xo, zo);
        
        final double result1 = Parser.eval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4", null, null);
 

In the test package you can see more examples.

The last version supports complex expressions. Here an example:

        String f_x = " e^(1*x*acos((3/2-2i)^(pi)))";
        
        Point xo = new Point("x", new Complex(1, 2));
        ParserResult result = Parser.eval(f_x, xo);
     

This version is compiled for Java 1.6
        

Enjoy it!!
