package seedu.address.storage.finance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.ReadOnlyEventsBook;
import seedu.address.model.ReadOnlyFinancesBook;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Finance;
import seedu.address.storage.events.JsonAdaptedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable Finances that is serializable to JSON format.
 */
public class JsonSerializableFinancesBook {
    public static final String MESSAGE_DUPLICATE_FINANCE = "Finance list contains duplicate finance(s).";

    private final List<JsonAdaptedFinance> finances = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFinancesBook} with the given events.
     */
    @JsonCreator
    public JsonSerializableFinancesBook(@JsonProperty("finances") List<JsonAdaptedFinance> finances) {
        this.finances.addAll(finances);
    }

    /**
     * Converts a given {@code ReadOnlyFinancesBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFinancesBook}.
     */
    public JsonSerializableFinancesBook(ReadOnlyFinancesBook source) {
        //finances.addAll(source.getFinanceList().stream().map(JsonAdaptedFinance::new).collect(Collectors.toList()));
    }

    /**
     * Converts this finance book into the model's {@code FinancesBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FinancesBook toModelType() throws IllegalValueException {
        FinancesBook financesBook = new FinancesBook();
        /*
        for (JsonAdaptedFinance jsonAdaptedFinance : finances) {

            Finance finance = jsonAdaptedFinance.toModelType();
            if (financesBook.hasFinance(finance)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FINANCE);
            }
            financesBook.addFinance(finance);
        }
         */
        return financesBook;
    }
}
