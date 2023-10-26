package seedu.address.model.finance;

import seedu.address.model.person.Person;

/**
 * The parent class of Commission and Expense.
 */
public abstract class Finance {
    /**
     * Get the amount of the commission.
     *
     * @return The amount of the commission.
     */
    public abstract Amount getAmount();

    /**
     * Get the client or payee associated with the commission.
     *
     * @return The client or payee associated with the commission.
     */
    public abstract Person getClient();

    /**
     * Get the description of the commission.
     *
     * @return The description of the commission.
     */
    public abstract Description getDescription();
}
