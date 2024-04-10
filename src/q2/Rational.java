package q2;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Denominator must be positive!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    // returns positive if current instance is greater, 0 if equal, negative if other is greater.
    private int compareValue(Rational other) {
        return this.getNumerator() * other.getDenominator() -
                other.getNumerator() * this.getDenominator();
    }

    // returns whether the current instance is greater than the provided rational number
    public boolean greaterThan(Rational other) {
        return this.compareValue(other) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || !(o instanceof Rational)) return false;
        Rational other = (Rational) o; // could single-line this
        return this.equals(other);
    }

    // returns whether the current instance is equal to the provided rational number
    public boolean equals(Rational other) {
        return this.compareValue(other) == 0;
    }

    // calculates addition with another rational number
    public Rational plus(Rational other) {
        return doAddSubtract(this, other, true);
    }

    // calculates subtraction of another rational number
    public Rational minus(Rational other) {
        return doAddSubtract(this, other, false);
    }

    // calculates multiplication with another rational number
    public Rational multiply(Rational other) {
        return doMultiplyDivide(this, other, true);
    }

    // calculates division by another rational number
    public Rational divide(Rational other) throws ArithmeticException {
        if (other.getNumerator() == 0) {
            throw new ArithmeticException("Can not divide by zero!");
        }
        return doMultiplyDivide(this, other, false);
    }

    // Returns a simplified rational number, with the same numeric value
    public Rational reduce() {
        int gcd = this.getGCD();
        return new Rational(
                this.getNumerator() / gcd,
                this.getDenominator() / gcd
        );
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    // returns the greatest common divisor of the numerator and denominator of the current rational number
    private int getGCD() {
        return calculateGCD(Math.abs(this.getNumerator()), Math.abs(this.getDenominator()));
    }

    // calculates the greatest common divisor of two numbers
    private static int calculateGCD(int first, int second) {
        if (second == 0) {
            return first;
        }
        return calculateGCD(second, first % second);
    }

    // handles both addition and subtraction(since the logic is nearly identical)
    private static Rational doAddSubtract(Rational r1, Rational r2, boolean isAdd) {
        int component1 = r1.getNumerator() * r2.getDenominator();
        int component2 = r2.getNumerator() * r1.getDenominator();
        int numerator = isAdd
                ? component1 + component2
                : component1 - component2;
        int denominator = r1.getDenominator() * r2.getDenominator();
        return new Rational(numerator, denominator);
    }

    // Assumes non-zero denominator to prevent try-catch inside multiply
    // handles both multiplication and division(since the logic is nearly identical)
    private static Rational doMultiplyDivide(Rational r1, Rational r2, boolean isMultiply) {
        // with division we invert the numerator/denominator multiplication
        int multiplyByNumerator = isMultiply ? r2.getNumerator() : r2.getDenominator();
        int multiplyByDenominator = isMultiply ? r2.getDenominator() : r2.getNumerator();

        int numerator = r1.getNumerator() * multiplyByNumerator;
        int denominator = r1.getDenominator() * multiplyByDenominator;

        // denominator can't be negative. if it is- move the negative sign to the numerator.
        // also handles the case where both numerator and denominator are negative.
        if (denominator < 0) {
            denominator *= -1;
            numerator *= -1;
        }
        return new Rational(numerator, denominator);
    }
}
