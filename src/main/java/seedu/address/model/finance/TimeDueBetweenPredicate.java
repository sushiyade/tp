package seedu.address.model.finance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Finance}'s {@code TimeDue} is between the given start and end times.
 */
public class TimeDueBetweenPredicate implements Predicate<Finance> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;

    /**
     * Creates a TimeDueBeforePredicate with the given time range.
     * @param timeRange Array containing start time and end time.
     */
    public TimeDueBetweenPredicate(LocalDateTime[] timeRange) {
        this.timeStart = timeRange[0];
        this.timeEnd = timeRange[1];
    }

    @Override
    public boolean test(Finance finance) {
        LocalDateTime financeTimeDue = finance.getTimeDue().getTime();
        return financeTimeDue.isBefore(timeEnd) && financeTimeDue.isAfter(timeStart);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeDueBetweenPredicate)) {
            return false;
        }

        TimeDueBetweenPredicate otherTimeDueBeforePredicate = (TimeDueBetweenPredicate) other;
        return timeStart.equals(otherTimeDueBeforePredicate.timeStart)
                && timeEnd.equals(otherTimeDueBeforePredicate.timeEnd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("timeStart", timeStart)
                .add("timeEnd", timeEnd)
                .toString();
    }
}
