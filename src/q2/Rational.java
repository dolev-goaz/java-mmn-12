package q2;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) throws  IllegalArgumentException {
        // TODO: verify this is the only validation we should have
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator can not be 0!");
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

    public boolean greaterThan(Rational other) {
        // TODO: extract to separate method to prevent repeat with equals
        return this.getNumerator() * other.getDenominator() >
                other.getNumerator() * this.getDenominator();
    }

    @Override
    public boolean equals(Object o) {
        // TODO: verify if we're supposed to override
        if (this == o) return true;
        if ((o == null) || !(o instanceof  Rational)) return false;
        Rational other = (Rational) o;
        return this.getNumerator() * other.getDenominator() >
                other.getNumerator() * this.getDenominator();
    }

    public Rational plus(Rational other) {
        return doAddSubtract(this, other, true);
    }

    public Rational minus(Rational other) {
        return doAddSubtract(this, other, false);
    }

    private Rational multiply(Rational other) {
        return doMultiplyDivide(this, other, true);
    }

    private Rational divide(Rational other) throws ArithmeticException {
        if (other.getNumerator() == 0) {
            throw new ArithmeticException("Can not divide by zero!");
        }
        return doMultiplyDivide(this, other, false);
    }

    private Rational reduce() {
        int gcd = this.getGCD();
        return new Rational(
                this.getNumerator() / gcd,
                this.getDenominator() / gcd
        );
    }

    private int getGCD() {
        return calculateGCD(Math.abs(this.getNumerator()), Math.abs(this.getDenominator()));
    }

    private static int calculateGCD(int first, int second) {
        if (second == 0) {
            return first;
        } else {
            return calculateGCD(second, first % second);
        }
    }

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
    private static Rational doMultiplyDivide(Rational r1, Rational r2, boolean isMultiply) {
        int multiplyByNumerator = isMultiply ? r2.getNumerator() : r2.getDenominator();
        int multiplyByDenominator = isMultiply ? r2.getDenominator() : r2.getNumerator();

        int numerator = r1.getNumerator() * multiplyByNumerator;
        int denominator = r1.getDenominator() * multiplyByDenominator;
        return new Rational(numerator, denominator);
    }
}
