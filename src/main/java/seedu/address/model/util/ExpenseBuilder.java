package seedu.address.model.util;

import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {
    public static final String DEFAULT_AMOUNT = "$900";
    public static final String DEFAULT_DESCRIPTION = "Test Description";
    public static final String DEFAULT_NAME = "Amy Bee";
    private Person client;
    private Amount amount;
    private Description description;

    /**
     * Creates a {@code CommissionBuilder} with the default details.
     */
    public ExpenseBuilder() {
        this.client = null;
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Sets the {@code Amount} of the {@code Commission} that we are building.
     */
    public ExpenseBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Commission} that we are building.
     */
    public ExpenseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Commission} that we are building.
     */
    public ExpenseBuilder withPerson(String clientName) {
        this.client = new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        return this;
    }
    public Expense build() {
        return new Expense(amount, client, description);
    }
}
