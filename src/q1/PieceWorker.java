package q1;

// PieceWorker class extends Employee. represents an employee
// that is paid by products produced.
public class PieceWorker extends Employee {
    private final double profitPerItem; // profit gained by a product being produced
    private final int itemsProducedCount; // the amount of products that were produced

    // constructor
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

    // return profit per item
    public double getProfitPerItem() {
        return profitPerItem;
    }

    // return count of items produced
    public int getItemsProducedCount() {
        return itemsProducedCount;
    }

    // calculate earnings; override abstract method earnings in Employee
    @Override
    public double earnings() {
        return profitPerItem * itemsProducedCount;
    }

    // return String representation of PieceWorker object
    @Override
    public String toString() {
        return String.format("piece worker: %s%n%s: %,.2f$; %s: %d",
                super.toString(), "profit per item", getProfitPerItem(),
                "items produced", getItemsProducedCount());
    }
}
