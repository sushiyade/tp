package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * Represents the end time of an event in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class TimeEnd {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static final String MESSAGE_CONSTRAINTS =
            "End time should be in the format dd-MM-yyyy HH:mm (e.g., 23-09-2023 16:40)";

    /*
     * The regular expression to validate the end time format.
     */
    private static final String VALIDATION_REGEX = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";

    private final LocalDateTime timeEnd;

    private final String value;

    /**
     * Constructs a {@code TimeEnd}.
     *
     * @param time A valid end time.
     */
    public TimeEnd(LocalDateTime time) {
        requireNonNull(time);
        checkArgument(isValidTime(time.format(DATE_TIME_FORMATTER)), MESSAGE_CONSTRAINTS);
        timeEnd = time;
        value = timeToString(time);
    }

    public TimeEnd(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(stringToTime(timeString).format(DATE_TIME_FORMATTER)), MESSAGE_CONSTRAINTS);
        timeEnd = stringToTime(timeString);
        value = timeString;
    }

    /**
     * Returns true if a given string is a valid end time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns end time in LocalDateTime type.
     */
    public LocalDateTime getTime() {
        return timeEnd;
    }

    public String getValue() {
        return value;
    }

    public String timeToString(LocalDateTime time) {
        return time.format(DATE_TIME_FORMATTER);
    }

    public LocalDateTime stringToTime(String timeString) {
        return LocalDateTime.parse(timeString, DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return timeEnd.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeEnd)) {
            return false;
        }

        TimeEnd otherTimeEnd = (TimeEnd) other;
        return timeEnd.equals(otherTimeEnd.timeEnd);
    }

    @Override
    public int hashCode() {
        return timeEnd.hashCode();
    }
}
