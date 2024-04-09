package q2;

import java.util.Scanner;

public class Main {
    private static Character[] comparisons = {'=', '>', '<'};
    private static Character[] operations = {'+', '-', '*', '/'};

    public static void main(String[] args) {
        System.out.println("Input rational number r1");
        Rational r1 = inputRational();
        System.out.println(String.format("Provided r1: %s.", r1));

        System.out.println(); // separator

        System.out.println("Input rational number r2");
        Rational r2 = inputRational();
        System.out.println(String.format("Provided r2: %s.", r2));

        System.out.println(); // separator

        runComparisons(r1, r2);

        System.out.println(); // separator

        runOperations(r1, r2);
    }

    private static void runComparisons(Rational r1, Rational r2) {
        for (Character comparison: comparisons) {
            try {
                boolean isComparisonTrue = runComparison(r1, r2, comparison);
                String comparisonStr = isComparisonTrue
                        ? "true"
                        : "false";
                System.out.println(
                        String.format("%s %c %s is %s", r1, comparison, r2, comparisonStr)
                );
            } catch (UnsupportedOperationException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    private static boolean runComparison(Rational r1, Rational r2, Character comparisonChar) {
        switch (comparisonChar) {
            case '=':
                return r1.equals(r2);
            case '>':
                return r1.greaterThan(r2);
            case '<':
                return r2.greaterThan(r1);
            default:
                throw new UnsupportedOperationException(
                        String.format("Invalid rational comparison '%c'", comparisonChar)
                );
        }
    }

    private static void runOperations(Rational r1, Rational r2) {
        for (Character operation: operations) {
            try {
                Rational output = runOperation(r1, r2, operation);
                System.out.println(
                        String.format("%s %c %s = %s", r1, operation, r2, output.reduce())
                );
            } catch (UnsupportedOperationException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static Rational runOperation(Rational r1, Rational r2, Character operationChar)
            throws UnsupportedOperationException {
        Rational output;
        switch (operationChar) {
            case '*':
                output = r1.multiply(r2);
                break;
            case '/':
                output = r1.divide(r2);
                break;
            case '+':
                output = r1.plus(r2);
                break;
            case '-':
                output = r1.minus(r2);
                break;
            default:
                throw new UnsupportedOperationException(
                        String.format("Invalid rational operation '%c'", operationChar)
                );
        }
        return output;
    }

    private static Rational inputRational() {
        Rational output = null;
        do {
            int numerator = inputNumeric("Insert numerator: ");
            int denominator = inputNumeric("Insert denominator: ");
            try {
                output = new Rational(numerator, denominator);
            } catch (IllegalArgumentException e) {
                // I tried using System.err, but using two different streams to the console caused inconsistencies
                // with the printing order.
                System.out.println("[ERROR] " + e.getMessage());
            }
        } while (output == null);

        return output;
    }

    private static int inputNumeric(String inputMessage) {
        Integer value = null;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print(inputMessage);
            String userInput = scan.next();
            try {
                value = Integer.parseInt(userInput);
            } catch(NumberFormatException e) {
                // I tried using System.err, but using two different streams to the console caused inconsistencies
                // with the printing order.
                System.out.println("[ERROR] Invalid number! please try again.");
            }
        } while (value == null);
        return value;
    }
}
