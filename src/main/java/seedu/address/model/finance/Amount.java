package seedu.address.model.finance;

/**
 * Represents an Amount received in a Commission or spent in an Expense.
 */
public class Amount {
    private int value;
    public Amount(int amount) {
        this.value = amount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) other;
        return this.value == otherAmount.value;
    }
}
