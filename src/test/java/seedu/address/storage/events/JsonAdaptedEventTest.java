package seedu.address.storage.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.testutil.TypicalEvents.EVENT5;

import org.junit.jupiter.api.Test;

public class JsonAdaptedEventTest {
    private static final String TEXT_MORE_THAN_256 = createMoreThanAllowedString();
    private static final String INVALID_NAME = TEXT_MORE_THAN_256;
    private static final String INVALID_DATETIME = "ASDFASDFASFD";
    private static final String INVALID_LOCATION = TEXT_MORE_THAN_256;
    private static final String INVALID_DESCRIPTION = TEXT_MORE_THAN_256;

    private static final String VALID_NAME = EVENT5.getName().toString();
    private static final String VALID_START_TIME = EVENT5.getTimeStart().toString();
    private static final String VALID_END_TIME = EVENT5.getTimeEnd().toString();
    private static final String VALID_LOCATION = EVENT5.getLocation().toString();
    private static final String VALID_DESCRIPTION = EVENT5.getDescription().toString();

    private static final String VALID_CLIENTS = EVENT5.getDescription().toString();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(EVENT5);
        assertEquals(EVENT5, event.toModelType());
    }

    // TODO: more tests
}
