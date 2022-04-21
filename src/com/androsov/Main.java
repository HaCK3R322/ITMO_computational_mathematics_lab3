package com.androsov;

import com.androsov.calc.IntegralSolver;

import java.util.InputMismatchException;
import java.util.Locale;
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
            String accuracyStr = sc.next();
            double accuracy = Double.parseDouble(accuracyStr);
            if(accuracy < 0.00000001) {
                System.out.println("Cannot work accuracy lower than 0.00000001! Answers for accuracy = 0.00000001");
                accuracy = 0.00000001;
            }

            System.out.println("Do you want to see number of steps to achieve this accuracy? (y/anything else)");
            boolean showAccuracy = false;
            String answer = sc.next();
            if(answer.equals("y"))
                showAccuracy = true;


            System.out.println("left rectangle method: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.LEFT_RECTANGLE_METHOD).result);
            System.out.println("right rectangle method: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.RIGHT_RECTANGLE_METHOD).result);
            System.out.println("middle rectangle method: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.MIDDLE_RECTANGLE_METHOD).result);
            System.out.println("trapezoidal method: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.TRAPEZOIDAL_METHOD).result);

            if (showAccuracy) {
                System.out.println("left rectangle method number of steps: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.LEFT_RECTANGLE_METHOD).numberOfSteps);
                System.out.println("right rectangle method number of steps: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.RIGHT_RECTANGLE_METHOD).numberOfSteps);
                System.out.println("middle rectangle method number of steps: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.MIDDLE_RECTANGLE_METHOD).numberOfSteps);
                System.out.println("trapezoidal method number of steps: " + IntegralSolver.solve(function, a, b, accuracy, IntegralSolver.TRAPEZOIDAL_METHOD).numberOfSteps);
            }


        } catch (InputMismatchException e) {
            System.out.println("Please enter decimal numbers separated by commas!");
        } catch (NumberFormatException e) {
            System.out.println("Please, enter a number!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lab3.init();
    }
}
