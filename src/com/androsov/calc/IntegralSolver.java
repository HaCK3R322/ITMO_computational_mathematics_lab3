package com.androsov.calc;

import com.androsov.calc.exceptions.NonIntegrableFunctionException;

import java.util.function.Function;

public class IntegralSolver {
    public static Double leftRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        {

            int n = (int)accuracy.doubleValue();
            double step = (b - a) / n;

//            System.out.println("a = " + a + "   b = " + b);
//            System.out.println("F(x0) = " + func.apply(a));
//            System.out.println("Length = " + (b - a));
//            System.out.println("Number of steps = " + n);
//            System.out.println("Step size = " + step);

            Double answer = (double) 0;
            for(int i = 0; i < n; i++) {
                answer += func.apply(a + i * step) * step;
                //System.out.println(answer);
            }

//            System.out.println("Integral(f, a -> b) = " + answer);

            return answer;
        }
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
