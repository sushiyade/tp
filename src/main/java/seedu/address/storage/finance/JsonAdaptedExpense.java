package seedu.address.storage.finance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.ClientName;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;

/**
 * Jackson-friendly version of {@link Expense}.
 */
public class JsonAdaptedExpense extends JsonAdaptedFinance {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final String amount;
    private final String client;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("amount") String amount, @JsonProperty("client") String client,
                                 @JsonProperty("description") String description) {
        this.amount = amount;
        this.client = client;
        this.description = description;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Expense source) {
        amount = source.getAmount().value;
        client = source.getClient().fullName;
        description = source.getDescription().value;
    }


    /**
     * Converts this Jackson-friendly adapted Expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Finance toModelType() throws IllegalValueException {
        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientName.class.getSimpleName()));
        }
        if (!ClientName.isValidClientName(client)) {
            throw new IllegalValueException(ClientName.MESSAGE_CONSTRAINTS);
        }

        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        final Amount modelAmount = new Amount(amount);

        final ClientName modelClientName = new ClientName(client);

        final Description modelDescription = new Description(description);

        return new Expense(modelAmount, modelClientName, modelDescription);
    }
}
