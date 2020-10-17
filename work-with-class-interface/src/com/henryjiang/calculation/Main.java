package com.henryjiang.calculation;

public class Main {

    public static void main(String... args) {
        performCalculations();
    }

    private static void performCalculations() {
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        for (int i = 0; i < opCodes.length; i++) {
            results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
        }

        for (double result : results) {
            System.out.println("result = " + result);
        }
    }

    private static double execute(char opCode, double leftVal, double rightVal) {
        double result;

        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;  // add
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0.0d; // divide
                break;
            case 'm':
                result = leftVal * rightVal; // multiply
                break;
            case 's':
                result = leftVal - rightVal;    // subtract
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0d;
                break;
        }
        return result;
    }
}
