package com.androsov.calc;

import com.androsov.calc.exceptions.NonIntegrableFunctionException;

import java.util.function.Function;

public class IntegralSolver {
    private static final int START_NUMBER_OF_INTERVALS = 4;
    private static final double RUNGE_K = (1.0/3.0);


    private static Double leftRectangleMethod(Function<Double, Double> func, Double a, Double b, int numberOfSteps) {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 0; i < numberOfSteps; i++) {
            answer += func.apply(a + i * step) * step;
        }

        return answer;
    }
    public static Double leftRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = START_NUMBER_OF_INTERVALS;

        while (Math.abs(leftRectangleMethod(func, a, b, 2*n) - leftRectangleMethod(func, a, b, n)) >= accuracy) {
            System.out.println("For n = " + n);
            n *= 2;
        }

        return leftRectangleMethod(func, a, b, n);
    }

    public static Double rightRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = (int)accuracy.doubleValue();
        double step = (b - a) / n;

//            System.out.println("a = " + a + "   b = " + b);
//            System.out.println("F(x0) = " + func.apply(a));
//            System.out.println("Length = " + (b - a));
//            System.out.println("Number of steps = " + n);
//            System.out.println("Step size = " + step);

        Double answer = (double) 0;
        for(int i = 1; i <= n; i++) {
            answer += func.apply(a + i * step) * step;
            //System.out.println(answer);
        }

//            System.out.println("Integral(f, a -> b) = " + answer);

        return answer;
    }

    public static Double middleRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = (int)accuracy.doubleValue();
        double step = (b - a) / n;

        Double answer = (double) 0;
        for(int i = 0; i < n; i++) {
            double x0 = a + i * step;
            double x1 = a + (i + 1) * step;
            double x = (x0 + x1) / 2;

            answer += func.apply(x) * step;
            //System.out.println(answer);
        }

//            System.out.println("Integral(f, a -> b) = " + answer);

        return answer;
    }
}
