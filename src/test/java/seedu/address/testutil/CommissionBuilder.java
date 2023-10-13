package seedu.address.testutil;

import seedu.address.model.finance.Amount;
import seedu.address.model.finance.ClientName;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;

/**
 * A utility class to help with building Commission objects.
 */
public class CommissionBuilder {
    public static final String DEFAULT_AMOUNT = "80";
    public static final String DEFAULT_DESCRIPTION = "Test Description";
    public static final String DEFAULT_NAME = "Alice Parker";
    private ClientName client;
    private Amount amount;
    private Description description;

    /**
     * Creates a {@code CommissionBuilder} with the default details.
     */
    public CommissionBuilder() {
        this.client = new ClientName(DEFAULT_NAME);
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Sets the {@code Amount} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withPerson(String client) {
        this.client = new ClientName(client);
        return this;
    }
    public Commission build() {
        return new Commission(amount, client, description);
    }
}
