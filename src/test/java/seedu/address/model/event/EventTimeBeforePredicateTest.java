package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EventBuilder;


public class EventTimeBeforePredicateTest {
    @Test
    public void testConstructor_validDateTimeString_success() {
        String eventTimeString = "01-01-2023 10:00";
        LocalDateTime eventTime = null;
        try {
            eventTime = parseDateTimeInstance(eventTimeString);
        } catch (ParseException e) {
            fail();
        }
        EventTimeBeforePredicate predicate = new EventTimeBeforePredicate(eventTime);
        assertNotNull(predicate);
    }

    @Test
    public void testConstructor_invalidDateTimeString_throwsException() {
        String invalidEventTimeString = "InvalidDateTime";
        assertThrows(ParseException.class, () -> new EventTimeBeforePredicate(
                parseDateTimeInstance(invalidEventTimeString)));
    }

    @Test
    public void testTest_eventTimeBeforePredicate_returnsTrue() {
        Event event = new EventBuilder().withTimeStart("01-01-2023 10:00").withTimeEnd("01-01-2023 11:00").build();
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 11:00"));
        } catch (ParseException e) {
            fail();
        }
        assertTrue(predicate.test(event));
    }

    @Test
    public void testTest_eventTimeEqualOrAfterPredicate_returnsFalse() {
        Event event = new EventBuilder().withTimeStart("01-01-2023 10:00").withTimeEnd("01-01-2023 11:00").build();
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 10:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertFalse(predicate.test(event));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        EventTimeBeforePredicate predicate1 = null;
        EventTimeBeforePredicate predicate2 = null;
        try {
            predicate1 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 10:00"));
            predicate2 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 10:00"));
        } catch (ParseException e) {
            fail();
        }
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        EventTimeBeforePredicate predicate1 = null;
        EventTimeBeforePredicate predicate2 = null;
        try {
            predicate1 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 10:00"));
            predicate2 = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 11:00"));
        } catch (ParseException e) {
            fail();
        }
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testToString_validEventTime_returnsExpectedString() {
        EventTimeBeforePredicate predicate = null;
        try {
            predicate = new EventTimeBeforePredicate(parseDateTimeInstance("01-01-2023 10:00"));
        } catch (ParseException e) {
            fail();
        }
        String expectedString = EventTimeBeforePredicate.class.getCanonicalName() + "{event time=2023-01-01T10:00}";
        assertEquals(expectedString, predicate.toString());
    }
}
