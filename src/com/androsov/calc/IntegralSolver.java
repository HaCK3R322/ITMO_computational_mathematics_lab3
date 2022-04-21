package com.androsov.calc;

import java.util.HashMap;
import java.util.function.Function;

@FunctionalInterface
interface QuadFunction<A, B, C, D, R> {
    R apply(A a, B b, C c, D d);
}


public class IntegralSolver {

    public static class Result {
        public Result(Double result, Integer numberOfSteps) {
            this.result = result;
            this.numberOfSteps = numberOfSteps;
        }

        public Double result;
        public Integer numberOfSteps;
    }

    public static final int LEFT_RECTANGLE_METHOD = 1;
    public static final int RIGHT_RECTANGLE_METHOD = 2;
    public static final int MIDDLE_RECTANGLE_METHOD = 3;
    public static final int TRAPEZOIDAL_METHOD = 4;

    private static final int START_NUMBER_OF_INTERVALS = 4;
    private static final double RUNGE_K = (1.0/3.0);

    private static final QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> leftRectangleMethod = (func, a, b, numberOfSteps) -> {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 0; i < numberOfSteps; i++) {
            answer += func.apply(a + i * step) * step;
        }

        return answer;
    };

    private static final QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> rightRectangleMethod = (func, a, b, numberOfSteps) -> {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 1; i <= numberOfSteps; i++) {
            answer += func.apply(a + i * step) * step;
        }

        return answer;
    };

    private static final QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> middleRectangleMethod = (func, a, b, numberOfSteps) -> {
        double step = (b - a) / numberOfSteps;

        double answer = 0.0;
        for(int i = 0; i < numberOfSteps; i++) {
            double x0 = a + i * step;
            double x1 = a + (i + 1) * step;
            double x = (x0 + x1) / 2;

            answer += func.apply(x) * step;
        }

        return answer;
    };

    private static final QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> trapezoidalMethod = (func, a, b, numberOfSteps) -> {
        double answer = 0.0;

        double step = (b - a) / numberOfSteps;

        double firstFunc = (func.apply(a) + func.apply(b)) / 2;

        answer += firstFunc;
        for (int i = 0; i < numberOfSteps; i++) {
            answer += func.apply(a + i * step);
        }
        answer *= step;

        return answer;
    };

    //Hash map of methods
    public static final HashMap<Integer, QuadFunction<Function<Double, Double>, Double, Double, Integer, Double>>
            functions = new HashMap<>() {{
        put(1, leftRectangleMethod);
        put(2, rightRectangleMethod);
        put(3, middleRectangleMethod);
        put(4, trapezoidalMethod);
    }};

    public static Result solve(Function<Double, Double> func, Double a, Double b, Double accuracy, Integer methodKey) {
        int n = START_NUMBER_OF_INTERVALS;

        QuadFunction<Function<Double, Double>, Double, Double, Integer, Double> function = functions.get(methodKey);

        while (RUNGE_K * Math.abs(function.apply(func, a, b, 2*n) - function.apply(func, a, b, n)) >= accuracy) {
            n *= 2;
        }
        n *= 4;


        return new Result(function.apply(func, a, b, n), n);
    }
}
