package com.androsov;

import com.androsov.calc.IntegralSolver;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Double a = (double)(-2);
        Double b = (double)10;

        Function<Double, Double> f1 = x -> 2*x*x;


        for (int i = 2; i < 1000000; i *= 2) {
            Double answer = IntegralSolver.leftRectangleMethod(f1, a, b, (double)i);
            System.out.println("(LeftRect)  Number of steps: " + i + "  Answer: " + answer + "  Difference: " + ((double)672 - answer) +
                    "   Accuracy: " + ( (answer)/672 )*100 + "%");
        }
        System.out.println("--------------------------------------------------------");
        for (int i = 2; i < 1000000; i *= 4) {
            Double answer = IntegralSolver.rightRectangleMethod(f1, a, b, (double)i);
            System.out.println("(RightRect) Number of steps: " + i + "  Answer: " + answer + "  Difference: " + ((double)672 - answer) +
                    "   Accuracy: " + ( 672/answer )*100 + "%");
        }
        System.out.println("--------------------------------------------------------");
        for (int i = 2; i < 1000000; i *= 2) {
            Double answer = IntegralSolver.middleRectangleMethod(f1, a, b, (double)i);
            System.out.println("(MiddleRect) Number of steps: " + i + "  Answer: " + answer + "  Difference: " + ((double)672 - answer) +
                    "   Accuracy: " + ( answer/672 )*100 + "%");
        }
    }
}
