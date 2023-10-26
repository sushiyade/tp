package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.testutil.EventBuilder;


public class EventTimeBeforePredicateTest {
    @Test
    public void testConstructor_validDateTimeString_success() {
        String eventTimeString = "01-01-2023 10:00";
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(eventTimeString);
        assertNotNull(predicate);
    }

    @Test
    public void testConstructor_invalidDateTimeString_throwsException() {
        String invalidEventTimeString = "InvalidDateTime";
        assertThrows(DateTimeParseException.class, () -> new EventTimeBeforePredicate(invalidEventTimeString));
    }

    @Test
    public void testTest_eventTimeBeforePredicate_returnsTrue() {
        try {
            Event event = new EventBuilder().withTimeStart("01-01-2023 10:00").withTimeEnd("01-01-2023 11:00").build();
            EventTimeBeforePredicate predicate = new EventTimeBeforePredicate("01-01-2023 11:00");
            assertTrue(predicate.test(event));
        } catch (TimeStartAfterTimeEndException e) {
            fail();
        }
    }

    @Test
    public void testTest_eventTimeEqualOrAfterPredicate_returnsFalse() {
        try {
            Event event = new EventBuilder().withTimeStart("01-01-2023 10:00").withTimeEnd("01-01-2023 11:00").build();
            EventTimeBeforePredicate predicate = new EventTimeBeforePredicate("01-01-2023 10:00");
            assertFalse(predicate.test(event));
        } catch (TimeStartAfterTimeEndException e) {
            fail();
        }

    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        EventTimeBeforePredicate predicate1 = new EventTimeBeforePredicate("01-01-2023 10:00");
        EventTimeBeforePredicate predicate2 = new EventTimeBeforePredicate("01-01-2023 10:00");
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        EventTimeBeforePredicate predicate1 = new EventTimeBeforePredicate("01-01-2023 10:00");
        EventTimeBeforePredicate predicate2 = new EventTimeBeforePredicate("01-01-2023 11:00");
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testToString_validEventTime_returnsExpectedString() {
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate("01-01-2023 10:00");
        String expectedString = EventTimeBeforePredicate.class.getCanonicalName() + "{event time=2023-01-01T10:00}";
        assertEquals(expectedString, predicate.toString());
    }
}
