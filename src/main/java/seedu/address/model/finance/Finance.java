package seedu.address.model.finance;

import seedu.address.model.person.Person;

/**
 * The parent class of Commission and Expense.
 */
public abstract class Finance {
    /**
     * Get the amount of the finance entry.
     *
     * @return The amount of the finance entry.
     */
    public abstract Amount getAmount();

    /**
     * Get the client or payee associated with the finance entry.
     *
     * @return The client or payee associated with the finance entry.
     */
    public abstract Person getClient();

    /**
     * Get the description of the finance entry.
     *
     * @return The description of the finance entry.
     */
    public abstract Description getDescription();

    /**
     * Get the time due of the finance entry.
     *
     * @return The time due of the finance entry.
     */
    public abstract TimeDue getTimeDue();
}
