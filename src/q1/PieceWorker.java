package q1;

public class PieceWorker extends Employee {
    private final double profitPerItem;
    private final int itemsProducedCount;

    public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
                       BirthDate birthDate, double profitPerItem, int itemsProducedCount) {
        super(firstName, lastName, socialSecurityNumber, birthDate);

        // NOTE: since the other constructors didn't specify 'throws IllegalArgumentException',
        // we don't specify it here as well, to maintain consistency.

        if (profitPerItem <= 0.0) { // validate profit
            throw new IllegalArgumentException("profit per item must be positive");
        }

        if (itemsProducedCount < 0.0) { // items produced
            throw new IllegalArgumentException(
                    "Count of produced items must be non-negative");
        }

        this.profitPerItem = profitPerItem;
        this.itemsProducedCount = itemsProducedCount;
    }

    @Override
    public double earnings() {
        return profitPerItem * itemsProducedCount;
    }
}
