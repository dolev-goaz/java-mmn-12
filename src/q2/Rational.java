package q2;

import java.util.Objects;

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
}
