package seedu.address.model.finance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Finance}'s {@code TimeDue} is before the given time.
 */
public class TimeDueBeforePredicate implements Predicate<Finance> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime timeDue;

    public TimeDueBeforePredicate(LocalDateTime timeDue) {
        this.timeDue = timeDue;
    }

    @Override
    public boolean test(Finance finance) {
        LocalDateTime financeTimeDue = finance.getTimeDue().getTime();
        return financeTimeDue.isBefore(timeDue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeDueBeforePredicate)) {
            return false;
        }

        TimeDueBeforePredicate otherTimeDueBeforePredicate = (TimeDueBeforePredicate) other;
        return timeDue.equals(otherTimeDueBeforePredicate.timeDue);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("timeDue", timeDue).toString();
    }
}
