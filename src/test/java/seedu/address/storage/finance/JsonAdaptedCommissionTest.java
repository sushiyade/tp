package seedu.address.storage.finance;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.ClientName;
import seedu.address.model.finance.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.storage.finance.JsonAdaptedCommission.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

public class JsonAdaptedCommissionTest {
    private static final String TEXT_MORE_THAN_256 = createMoreThanAllowedString();
    private static final String INVALID_CLIENT_NAME = TEXT_MORE_THAN_256;
    private static final String INVALID_AMOUNT = TEXT_MORE_THAN_256;
    private static final String INVALID_DESCRIPTION = TEXT_MORE_THAN_256;

    private static final String VALID_CLIENT_NAME = COMMISSION_FROM_ALICE.getClient().toString();
    private static final String VALID_AMOUNT = COMMISSION_FROM_ALICE.getAmount().toString();
    private static final String VALID_DESCRIPTION = COMMISSION_FROM_ALICE.getDescription().toString();

    @Test
    public void toModelType_validCommissionDetails_returnsEvent() throws Exception {
        JsonAdaptedCommission commission = new JsonAdaptedCommission(COMMISSION_FROM_ALICE);
        assertEquals(COMMISSION_FROM_ALICE, commission.toModelType());
    }


    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(INVALID_AMOUNT, VALID_CLIENT_NAME,
                        VALID_DESCRIPTION);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(null, VALID_CLIENT_NAME,
                        VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_nullClientName_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(VALID_AMOUNT, null,
                        VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClientName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(VALID_AMOUNT, VALID_CLIENT_NAME,
                        INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }
}
