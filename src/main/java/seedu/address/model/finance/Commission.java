package seedu.address.model.finance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a commission from a client.
 */
public class Commission extends Finance {
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
        requireAllNonNull(amount, client, description);
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
     * Returns true if two commissions have the same fields.
     * This is a less strict equals that is used for testing.
     */
    public boolean haveSameFields(Commission commission) {
        return amount.equals(commission.getAmount())
                && client.equals((commission.getClient()))
                && description.equals(commission.getDescription());
    }

    public void setMatchedClientInstance(Person actualClient) {
        client = actualClient;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("client", client)
                .add("amount", amount)
                .add("description", description)
                .toString();
    }
}
