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

        Parser.simpleEval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4"); --> for real functions

        String f_x = "+3 +5*5*(+1)";

        ParserResult result = Parser.eval(f_x); --> for real or complex functions
        assertTrue(result.getValue() == 28.0);

        final Point xo = new Point("x", new Double(2));
        f_x = "2.35*e^(-3)*x";

        result = Parser.eval(f_x, xo);  --> for real or complex functions with real or complex vars
        assertTrue(result.getValue() == 0.2339992213289606);

        final Point xo = new Point("x", new Double(2));
        final Point zo = new Point("z", new Double(1));
        String f_xs = " 2*(-(((z*3)*sqrt(x^(2)))+3))";        
        
        Parser.eval(f_xs, xo, zo); --> multiple vars
        
        String f_xs = "x+5*y+(3 -y)";
        final Point xo = new Point("x", "1+1");
        final Point yo = new Point("y", "0+2*0+1*5-5 +1^4"); //math expression in vars

        ParserResult result = Parser.eval(f_xs, xo, yo);
		
       
      
### Complex numbers
        
	    String f_x = " e^(1*x*acos((3/2-2j)^(pi)))";
        
        Point xo = new Point("x", new Complex(1, 2)); --> complex var: 1+ 2j
        ParserResult result = Parser.eval(f_x, xo);
        
        String f_x = "1+j +x";
        final Point xo = new Point("x", "2 +j"); //complex math expression in vars

        ParserResult result = Parser.eval(f_x, xo);
     
### Execution time
 
       These are the results for the version 3.0 (master). You can check the speedTests in the project
  
	   Parser.simpleEval("6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3
                              + (3.5^3+7/2)^3 -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 
			      -(5*4/(2-3))*4 + 6.5*7.8^2.3 + (3.5^3+7/2)^3 -(5*4/(2-3))*4");

       CPU: i7-6500U
       
       test 1: one execution: 3ms
       test 2: 100000 executions : 2100 ms --> mean time 0.021 ms per execution 
                                   (with graalvm-jdk-17.0.8+9.1 the total time is 754ms) 
       test 3: one million executions: 16500 ms --> mean time 0.0165 ms per execution 
                                   (with graalvm-jdk-17.0.8+9.1 the total time is 7980ma) 


This version is compiled for Java 1.6

If you are interested in maths, you can visit my java numerical library in my github repository which uses java.math.expression.parser to evaluate functions.

## Professional Services
If you are interested in logical parsers or any task related to parsers, you can consult my professional services page https://github.com/sbesada/professional.services        

## Donation
If you think that my work deserves a donation, you can do it: https://sbesada.github.io/
