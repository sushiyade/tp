package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents the start time of an event in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class TimeStart {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static final String MESSAGE_CONSTRAINTS =
            "Start time should be in the format dd-MM-yyyy HH:mm (e.g., 23-09-2023 16:40)";

    /*
     * The regular expression to validate the start time format.
     */
    private static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";

    private final LocalDateTime timeStart;

    public final String value = "hello";

    /**
     * Constructs a {@code TimeStart}.
     *
     * @param time A valid start time.
     */
    public TimeStart(LocalDateTime time) {
        requireNonNull(time);
        checkArgument(isValidTime(time.format(DATE_TIME_FORMATTER)), MESSAGE_CONSTRAINTS);
        timeStart = time;
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isAfter(TimeEnd timeEnd) {
        return timeStart.isAfter(timeEnd.getTime())
;    }

    @Override
    public String toString() {
        return timeStart.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeStart)) {
            return false;
        }

        TimeStart otherTimeStart = (TimeStart) other;
        return timeStart.equals(otherTimeStart.timeStart);
    }

    @Override
    public int hashCode() {
        return timeStart.hashCode();
    }
}
