package seedu.address.model.finance;

import seedu.address.model.person.Person;

/**
 * Represents a commission from a client.
 */
public class Commission {
    private Amount amount;
    private Person client;
    private Description description;

    /**
     * Constructs a Commission object with the specified amount, client, and description.
     *
     * @param amount      The amount of the commission.
     * @param client      The client or payee associated with the commission.
     * @param description A description of the commission.
     */
    public Commission(Amount amount, Person client, Description description) {
        this.amount = amount;
        this.client = client;
        this.description = description;
    }

    /**
     * Get the amount of the commission.
     *
     * @return The amount of the commission.
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get the client or payee associated with the commission.
     *
     * @return The client or payee associated with the commission.
     */
    public Person getClient() {
        return client;
    }

    /**
     * Get the description of the commission.
     *
     * @return The description of the commission.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if they are the same instance.
     */
    @Override
    public boolean equals(Object other) {
        return this == other;
    }
}
