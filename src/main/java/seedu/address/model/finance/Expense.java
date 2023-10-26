package seedu.address.model.finance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an expense incurred by the user.
 */
public class Expense extends Finance {

    private Amount amount;
    private Person client;
    private Description description;
    private TimeDue timeDue;

    /**
     * Constructs an Expense object with the specified amount, client, and description.
     *
     * @param amount      The amount of the expense
     * @param client      The client or payee associated with the expense
     * @param description A description of the exoense
     * @param timeDue     The time due of the commission.
     */
    public Expense(Amount amount, Person client, Description description, TimeDue timeDue) {
        requireAllNonNull(amount, description);
        this.amount = amount;
        this.client = client;
        this.description = description;
        this.timeDue = timeDue;
    }

    /**
     * Get the amount of the expense.
     *
     * @return The amount of the expense.
     */
    @Override
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get the client or payee associated with the expense.
     *
     * @return The client or payee associated with the expense.
     */
    @Override
    public Person getClient() {
        return client;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public TimeDue getTimeDue() {
        return timeDue;
    }

    /**
     * Returns true if two expenses have the same fields.
     * This is a less strict equals that is used for testing.
     */
    public boolean haveSameFields(Expense expense) {
        return amount.equals(expense.getAmount())
                && client.equals((expense.getClient()))
                && description.equals(expense.getDescription())
                && timeDue.equals(expense.getTimeDue());
    }

    public void setMatchedClientInstance(Person actualClient) {
        client = actualClient;
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
