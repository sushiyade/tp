package seedu.address.testutil;

import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * A utility class to help with building Commission objects.
 */
public class CommissionBuilder {
    public static final String DEFAULT_AMOUNT = "$900";
    public static final String DEFAULT_DESCRIPTION = "Test Description";
    public static final String DEFAULT_NAME = "Amy Bee";
    private Person client;
    private Amount amount;
    private Description description;

    /**
     * Creates a {@code CommissionBuilder} with the default details.
     */
    public CommissionBuilder() {
        this.client = new Person(new Name(DEFAULT_NAME), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
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
    public CommissionBuilder withPerson(String clientName) {
        this.client = new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        return this;
    }
    public Commission build() {
        return new Commission(amount, client, description);
    }
}
