package seedu.address.model.finance;

/**
 * Represents an Amount received in a Commission or spent in an Expense.
 */
public class Amount {
    public static final String VALIDATION_REGEX = "^[+]?([.]\\d+|\\d+([.]\\d+)?)$";
    public static final String MESSAGE_CONSTRAINTS =
            "Amounts should be strictly positive and up to 2dp";
    private String value;
    public Amount(String amount) {
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
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}
