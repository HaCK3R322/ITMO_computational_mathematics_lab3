package com.androsov;

import com.androsov.calc.IntegralSolver;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

class Lab3 {
    public static void init() {
        final Scanner sc = new Scanner(System.in);

        Function<Double, Double> f1 = x -> x;
        Function<Double, Double> f2 = x -> x*x;
        Function<Double, Double> f3 = x-> Math.sqrt(x);
        Function<Double, Double> f4 = x -> -x*x*x - x*x - 2*x + 1;

        System.out.println("Functions you can integrate: ");
        System.out.println("1: f(x) = x");
        System.out.println("2: f(x) = x^(2)");
        System.out.println("3: f(x) = x^(1/2)");
        System.out.println("4: f(x) = -x^(3) - x^(2) - 2x + 1");

        System.out.println("Choose function (int): ");
        try {
            int funcId = sc.nextInt();

            Function<Double, Double> function;
            switch (funcId) {
                case 1 -> function = f1;
                case 2 -> function = f2;
                case 3 -> function = f3;
                case 4 -> function = f4;
                default -> {
                    System.out.println("NO SUCH FUNCTION!");
                    return;
                }
            }
            System.out.println("Enter start of interval: ");
            double a = sc.nextDouble();

            System.out.println("Enter end of interval: ");
            double b = sc.nextDouble();

            System.out.println("Enter accuracy: ");
            double accuracy = sc.nextDouble();
            if(accuracy < 0.00000001) {
                System.out.println("Cannot work accuracy lower than 0.00000001! Answers for accuracy = 0.00000001");
                accuracy = 0.00000001;
            }

            System.out.println("left rectangle method: " + IntegralSolver.leftRectangleMethod(function, a, b, accuracy));
            System.out.println("right rectangle method: " + IntegralSolver.rightRectangleMethod(function, a, b, accuracy));
            System.out.println("middle rectangle method: " + IntegralSolver.middleRectangleMethod(function, a, b, accuracy));

        } catch (InputMismatchException e) {
            System.out.println("Please enter decimal numbers separated by commas!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lab3.init();
    }
}
