package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.CommissionBuilder;
class TimeDueBeforePredicateTest {

    @Test
    public void testConstructor_validDateTimeString_success() {
        String eventTimeString = "01-01-2024 10:00";
        LocalDateTime timeDue = null;
        try {
            timeDue = parseDateTimeInstance(eventTimeString);
        } catch (ParseException e) {
            fail();
        }
        TimeDueBeforePredicate predicate = new TimeDueBeforePredicate(timeDue);
        assertNotNull(predicate);
    }

    @Test
    public void testConstructor_invalidDateTimeString_throwsException() {
        String invalidTimeDueString = "InvalidDateTime";
        assertThrows(ParseException.class, () -> new TimeDueBeforePredicate(
                parseDateTimeInstance(invalidTimeDueString)));
    }

    @Test
    public void testTest_timeDueBeforePredicate_returnsTrue() {
        Finance finance = new CommissionBuilder().withTimeDue("01-01-2024 11:00").build();
        TimeDueBeforePredicate predicate = null;
        try {
            predicate = new TimeDueBeforePredicate(parseDateTimeInstance("01-01-2024 12:00"));
        } catch (ParseException e) {
            fail();
        }
        assertTrue(predicate.test(finance));
    }

    @Test
    public void testTest_timeDueEqualOrAfterPredicate_returnsFalse() {
        Finance finance = new CommissionBuilder().withTimeDue("01-01-2024 11:00").build();
        TimeDueBeforePredicate predicate = null;
        try {
            predicate = new TimeDueBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertFalse(predicate.test(finance));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        try {
            TimeDueBeforePredicate predicate1 = new TimeDueBeforePredicate(
                    parseDateTimeInstance("01-01-2024 10:00"));
            TimeDueBeforePredicate predicate2 = new TimeDueBeforePredicate(
                    parseDateTimeInstance("01-01-2024 10:00"));
            assertEquals(predicate1, predicate2);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        try {
            TimeDueBeforePredicate predicate1 = new TimeDueBeforePredicate(
                    parseDateTimeInstance("01-01-2024 10:00"));
            TimeDueBeforePredicate predicate2 = new TimeDueBeforePredicate(
                    parseDateTimeInstance("01-01-2024 11:00"));
            assertNotEquals(predicate1, predicate2);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void testEquals_sameObject_returnsTrue() {
        TimeDueBeforePredicate predicate = new TimeDueBeforePredicate(LocalDateTime.now());
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void testEquals_nullObject_returnsFalse() {
        TimeDueBeforePredicate predicate = new TimeDueBeforePredicate(LocalDateTime.now());
        assertFalse(predicate.equals(null));
    }

    @Test
    public void testEquals_differentClass_returnsFalse() {
        TimeDueBeforePredicate predicate = new TimeDueBeforePredicate(LocalDateTime.now());
        assertNotEquals("NotAPredicate", predicate);
    }

    @Test
    public void testEquals_equalTimeDueDifferentObject_returnsTrue() {
        LocalDateTime timeDue = null;
        try {
            timeDue = parseDateTimeInstance("01-01-2024 10:00");
        } catch (ParseException e) {
            fail();
        }
        TimeDueBeforePredicate predicate1 = new TimeDueBeforePredicate(timeDue);
        TimeDueBeforePredicate predicate2 = new TimeDueBeforePredicate(timeDue);
        assertEquals(predicate1, predicate2);
        assertNotSame(predicate1, predicate2);
    }

    @Test
    public void testToString_nullTimeDue_returnsExpectedString() {
        TimeDueBeforePredicate predicate = new TimeDueBeforePredicate(null);
        String expectedString = TimeDueBeforePredicate.class.getCanonicalName() + "{timeDue=null}";
        assertEquals(expectedString, predicate.toString());
    }

    @Test
    public void testToString_validTimeDue_returnsExpectedString() {
        TimeDueBeforePredicate predicate = null;
        try {
            predicate = new TimeDueBeforePredicate(parseDateTimeInstance("01-01-2024 10:00"));
        } catch (ParseException e) {
            fail();
        }
        String expectedString = TimeDueBeforePredicate.class.getCanonicalName() + "{timeDue=2024-01-01T10:00}";
        assertEquals(expectedString, predicate.toString());
    }

}

