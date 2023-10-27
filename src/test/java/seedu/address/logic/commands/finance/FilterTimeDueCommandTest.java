package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.TimeDueBeforePredicate;
class FilterTimeDueCommandTest {
    @Test
    public void equals() {
        TimeDueBeforePredicate predicate1 = null;
        TimeDueBeforePredicate predicate2 = null;
        try {
            predicate1 = new TimeDueBeforePredicate(parseDateTimeInstance("23-01-2023"));
            predicate2 = new TimeDueBeforePredicate(parseDateTimeInstance("24-01-2023"));
        } catch (ParseException e) {
            fail();
        }

        FilterTimeDueCommand command1 = new FilterTimeDueCommand(predicate1);
        FilterTimeDueCommand command2 = new FilterTimeDueCommand(predicate1);
        FilterTimeDueCommand command3 = new FilterTimeDueCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    public void toStringMethod() {
        TimeDueBeforePredicate predicate = null;
        try {
            predicate = new TimeDueBeforePredicate(parseDateTimeInstance("23-01-2023"));
        } catch (ParseException e) {
            fail();
        }
        FilterTimeDueCommand command = new FilterTimeDueCommand(predicate);
        String expected = FilterTimeDueCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}

