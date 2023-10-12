package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class TimeStartTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeStart(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Invalid Time";
        assertThrows(DateTimeParseException.class, () -> new TimeStart(LocalDateTime.parse(invalidTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> TimeStart.isValidTime(null));

        // invalid time
        assertFalse(TimeStart.isValidTime("")); // empty string
        assertFalse(TimeStart.isValidTime("23-09-2023 16:40:30")); // seconds included
        assertFalse(TimeStart.isValidTime("2023-09-23 16:40")); // year-month-day format

        // valid time
        assertTrue(TimeStart.isValidTime("23-09-2023 16:40")); // valid time format
        assertTrue(TimeStart.isValidTime("01-01-2022 00:00")); // minimum date and time
        assertTrue(TimeStart.isValidTime("31-12-9999 23:59")); // maximum date and time
    }

    @Test
    public void equals() {
        LocalDateTime time = LocalDateTime.parse("23-09-2023 16:40", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        TimeStart timeStart = new TimeStart(time);

        // same values -> returns true
        assertTrue(timeStart.equals(new TimeStart(time)));

        // same object -> returns true
        assertTrue(timeStart.equals(timeStart));

        // null -> returns false
        assertFalse(timeStart.equals(null));

        // different types -> returns false
        assertFalse(timeStart.equals(5.0f));

        // different values -> returns false
        LocalDateTime differentTime = LocalDateTime.parse("24-09-2023 16:40",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        assertFalse(timeStart.equals(new TimeStart(differentTime)));
    }
}
