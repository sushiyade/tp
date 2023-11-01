package seedu.address.model.finance;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents a Finance in the finance book.
 * Guarantees: field values are validated, immutable.
 */
public abstract class Finance {
    protected Amount amount;
    protected Person client;
    protected Description description;
    protected TimeDue timeDue;

    /**
     * Get the amount of the finance entry.
     *
     * @return The amount of the finance entry.
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get the client or payee associated with the finance entry.
     *
     * @return The client or payee associated with the finance entry.
     */
    public Person getClient(){
        return client;
    }

    /**
     * Get the description of the finance entry.
     *
     * @return The description of the finance entry.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Get the time due of the finance entry.
     *
     * @return The time due of the finance entry.
     */
    public TimeDue getTimeDue() {
        return timeDue;
    }

    public void setMatchedClientInstance(Person actualClient) {
        client = actualClient;
    }

    /**
     * Returns true if two finances have the same fields and type.
     * This is a less strict equals that is used for testing.
     */
    public boolean isSameFinance(Finance finance) {
        return this.getClass().equals(finance.getClass())
                && amount.equals(finance.getAmount())
                && client.equals((finance.getClient()))
                && description.equals(finance.getDescription())
                && timeDue.equals(finance.getTimeDue());
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
                .add("timeDue", timeDue)
                .toString();
    }
}
