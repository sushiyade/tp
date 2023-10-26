package seedu.address.storage.finance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;

/**
 * Jackson-friendly version of {@link Commission}.
 */
public class JsonAdaptedCommission extends JsonAdaptedFinance {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Commission's %s field is missing!";

    private final String amount;
    private final JsonAdaptedPerson client;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedCommission} with the given commission details.
     */
    @JsonCreator
    public JsonAdaptedCommission(@JsonProperty("amount") String amount,
                                 @JsonProperty("client") JsonAdaptedPerson client,
                                 @JsonProperty("description") String description) {
        this.amount = amount;
        this.client = client;
        this.description = description;
    }

    /**
     * Converts a given {@code Commission} into this class for Jackson use.
     */
    public JsonAdaptedCommission(Commission source) {
        amount = source.getAmount().value;
        client = new JsonAdaptedPerson(source.getClient());
        description = source.getDescription().value;
    }


    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Commission} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted commission.
     */
    public Finance toModelType() throws IllegalValueException {
        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }

        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        final Amount modelAmount = new Amount(amount);

        final Person modelClient = client.toModelType();

        final Description modelDescription = new Description(description);

        return new Commission(modelAmount, modelClient, modelDescription);
    }
}
