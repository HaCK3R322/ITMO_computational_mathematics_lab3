package com.androsov.calc;

import java.util.HashMap;
import java.util.function.Function;

@FunctionalInterface
interface QuadFunction<A, B, C, D, R> {
    R apply(A a, B b, C c, D d);
}


public class IntegralSolver {

    private static final int START_NUMBER_OF_INTERVALS = 4;
    private static final double RUNGE_K = (1.0/3.0);

    private static final QuadFunction<Function<Double, Double>,
            Double,
            Double,
            Integer,
            Double> leftRectangleMethod = (func, a, b, numberOfSteps) -> {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 0; i < numberOfSteps; i++) {
            answer += func.apply(a + i * step) * step;
        }

        return answer;
    };

    //Hash map of methods
    public static final HashMap<Integer, QuadFunction<Function<Double, Double>, Double, Double, Integer, Double>>
            functions = new HashMap<>() {{
        put(1, leftRectangleMethod);
    }};

    public static Double solve(Function<Double, Double> func, Double a, Double b, Double accuracy, Integer methodKey) {
        int n = START_NUMBER_OF_INTERVALS;

        QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> function = functions.get(methodKey);

        System.out.println(functions.get(1));
        while (RUNGE_K * Math.abs(function.apply(func, a, b, 2*n) - function.apply(func, a, b, n)) >= accuracy) {
            n *= 2;
        }

        return function.apply(func, a, b, n);
    }

    private static Double rightRectangleMethod(Function<Double, Double> func, Double a, Double b, int numberOfSteps) {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 1; i <= numberOfSteps; i++) {
            answer += func.apply(a + i * step) * step;
        }

        return answer;
    }
    public static Double rightRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = START_NUMBER_OF_INTERVALS;

        while (RUNGE_K * Math.abs(rightRectangleMethod(func, a, b, 2*n) - rightRectangleMethod(func, a, b, n)) >= accuracy) {
//            System.out.println("For n = " + n + " answer is " + rightRectangleMethod(func, a, b, n));
            n *= 2;
        }

        return rightRectangleMethod(func, a, b, n);
    }

    private static Double middleRectangleMethod(Function<Double, Double> func, Double a, Double b, int numberOfSteps) {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 0; i < numberOfSteps; i++) {
            double x0 = a + i * step;
            double x1 = a + (i + 1) * step;
            double x = (x0 + x1) / 2;

            answer += func.apply(x) * step;
        }

        return answer;
    }
    public static Double middleRectangleMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = START_NUMBER_OF_INTERVALS;

        while (RUNGE_K * Math.abs(middleRectangleMethod(func, a, b, 2*n) - middleRectangleMethod(func, a, b, n)) >= accuracy) {
//            System.out.println("For n = " + n + " answer is " + middleRectangleMethod(func, a, b, n));
            n *= 2;
        }

        return middleRectangleMethod(func, a, b, n);
    }

    private static Double trapezoidalMethod(Function<Double, Double> func, Double a, Double b, Integer n) {
        double answer = 0.0;

        double step = (b - a) / n;

        double firstFunc = (func.apply(a) + func.apply(b)) / 2;

        answer += firstFunc;
        for (int i = 0; i < n; i++) {
            answer += func.apply(a + i * step);
        }
        answer *= step;

        return answer;
    }

    public static Double trapezoidalMethod(Function<Double, Double> func, Double a, Double b, Double accuracy) {
        int n = START_NUMBER_OF_INTERVALS;

        while (RUNGE_K * Math.abs(trapezoidalMethod(func, a, b, 2*n) - trapezoidalMethod(func, a, b, n)) >= accuracy) {
//            System.out.println("For n = " + n + " answer is " + trapezoidalMethod(func, a, b, n));
            n *= 2;
        }

        System.out.println("Answer + accuracy = " + (trapezoidalMethod(func, a, b, n) + accuracy));
        return trapezoidalMethod(func, a, b, n);
    }
}
