package q2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input rational number r1");
        Rational r1 = inputRational();
        System.out.println(String.format("Provided r1: %s.", r1));

        System.out.println("Input rational number r2");
        Rational r2 = inputRational();
        System.out.println(String.format("Provided r2: %s.", r2));


    }

    private static Rational inputRational() {
        int numerator = inputNumeric("Insert numerator: ");
        int denominator = inputNumeric("Insert denominator: ");

        return new Rational(numerator, denominator);
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
