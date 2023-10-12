package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class TimeEndTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeEnd(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Invalid Time";
        assertThrows(DateTimeParseException.class, () -> new TimeEnd(LocalDateTime.parse(invalidTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> TimeEnd.isValidTime(null));

        // invalid time
        assertFalse(TimeEnd.isValidTime("")); // empty string
        assertFalse(TimeEnd.isValidTime("23-09-2023 16:40:30")); // seconds included
        assertFalse(TimeEnd.isValidTime("2023-09-23 16:40")); // year-month-day format

        // valid time
        assertTrue(TimeEnd.isValidTime("23-09-2023 16:40")); // valid time format
        assertTrue(TimeEnd.isValidTime("01-01-2022 00:00")); // minimum date and time
        assertTrue(TimeEnd.isValidTime("31-12-9999 23:59")); // maximum date and time
    }

    @Test
    public void equals() {
        LocalDateTime time = LocalDateTime.parse("23-09-2023 16:40", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        TimeEnd timeEnd = new TimeEnd(time);

        // same values -> returns true
        assertTrue(timeEnd.equals(new TimeEnd(time)));

        // same object -> returns true
        assertTrue(timeEnd.equals(timeEnd));

        // null -> returns false
        assertFalse(timeEnd.equals(null));

        // different types -> returns false
        assertFalse(timeEnd.equals(5.0f));

        // different values -> returns false
        LocalDateTime differentTime = LocalDateTime.parse("24-09-2023 16:40",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        assertFalse(timeEnd.equals(new TimeEnd(differentTime)));
    }
}
