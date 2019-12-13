# java.math.expression.parser
java math expression parser is a maven project that lets you parse or evaluate math expressions.

This algorithm does not use a decision tree. It is a kind of Recursive Ascent Parser (https://en.wikipedia.org/wiki/Recursive_ascent_parser). In fact, it is LR parser (Left-Right Parser) without backtracking.

This algorithm is faster than JEP math expresion parser!!! If you compare java.math.expression.parse and JEP, this algorithm only needs 25% of the time to parse the same expression as JEP. With other algorithms that use trees like:

                                                                                       ---------
                                                                                       |   +   |
                                                                                       ---------
                                                                                           |
                                                                                    ---------------
                                                                                    |             |
                                                                                ---------     ---------
                                                                                |   1   |     |   *   |
                                                                                ---------     ---------
                                                                                
 It is even faster than them. This library is 10 times faster and **it is tested using matlab**. The python version of this library is pymep. You can find pymep in my github repository.
 
## Features

### math functions

- sin, cos, sinh, cosh, tan, tanh, asin, acos, atan
- pi, e
- ln (natural logarithm), log (decimal logarithm)
- sqrt, cbrt
- radians or degrees
- complex or real numbers

### parentheses 

 - (...)
 
 ### variables: 
 
 - Expressions in vars
 
       String f_xs = "x+5*y+(3 -y)";
       final Point xo = new Point("x", "1+1");
       final Point yo = new Point("y", "0+2*0+1*5-5 +1^4")
 
                                    
## Examples:
 
 In the test package you can see more examples with different constructors 

### Real numbers

        String f_x = "+3 +5*5*(+1)";

        ParserResult result = Parser.eval(f_x);
        assertTrue(result.getValue() == 28.0);

        final Point xo = new Point("x", new Double(2));
        f_x = "2.35*e^(-3)*x";

        result = Parser.eval(f_x, xo);
        assertTrue(result.getValue() == 0.2339992213289606);


        final Point xo = new Point("x", new Double(2));
        final Point zo = new Point("z", new Double(1));
        String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";        
        
        Parser.Eval(f_xs, xo, zo);
        
        String f_xs = "x+5*y+(3 -y)";
        final Point xo = new Point("x", "1+1");
        final Point yo = new Point("y", "0+2*0+1*5-5 +1^4"); //math expression in vars

        ParserResult result = Parser.eval(f_xs, xo, yo);
		
        final double result = Parser.eval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4", null, null); 
        --> execution time = 4ms in i7-6500U
 
### Complex numbers

The last version supports expressions with complex numbers and multiple vars. Here an example:

        String f_x = " e^(1*x*acos((3/2-2j)^(pi)))";
        
        Point xo = new Point("x", new Complex(1, 2));
        ParserResult result = Parser.eval(f_x, xo);
        
        String f_x = "1+j +x";
        final Point xo = new Point("x", "2 +j"); //complex math expression in vars

        ParserResult result = Parser.eval(f_x, xo);
     

This version is compiled for Java 1.6

If you are interested in maths, you can visit my java numerical library in my github repository which uses java.math.expression.parser to evaluate functions.
        

Enjoy it!!

PD: If you think that my work deserves a donation, you can do it: https://sbesada.github.io/
