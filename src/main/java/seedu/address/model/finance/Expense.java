package seedu.address.model.finance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

/**
 * Represents an expense incurred by the user.
 */
public class Expense extends Finance {

    /**
     * Constructs an Expense object with the specified amount, client, and description.
     *
     * @param amount      The amount of the expense
     * @param client      The client or payee associated with the expense
     * @param description A description of the expense
     * @param timeDue     The time due of the expense.
     */
    public Expense(Amount amount, Person client, Description description, TimeDue timeDue) {
        requireAllNonNull(amount, description);
        this.amount = amount;
        this.client = client;
        this.description = description;
        this.timeDue = timeDue;
    }

    /**
     * Returns true if two expenses have the same fields.
     * This is a less strict equals that is used for testing.
     */
    public boolean isSameExpense(Expense expense) {
        return amount.equals(expense.getAmount())
                && client.isSamePerson((expense.getClient()))
                && description.equals(expense.getDescription())
                && timeDue.equals(expense.getTimeDue());
    }
}
