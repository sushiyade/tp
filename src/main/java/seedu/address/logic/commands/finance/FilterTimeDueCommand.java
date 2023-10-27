package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.finance.TimeDueBeforePredicate;


/**
 * Finds and lists all finances in FreelanceBuddy whose timeDue is before the specified time.
 */
public class FilterTimeDueCommand extends Command {

    public static final String COMMAND_WORD = "filter-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Finances whose creation or due time"
            + " is before "
            + "the specified time given as input and displays them as a list with index numbers.\n"
            + "Parameters: Time \n"
            + "Example: " + COMMAND_WORD + " 23-11-2023";

    private final TimeDueBeforePredicate timePredicate;

    public FilterTimeDueCommand(TimeDueBeforePredicate timePredicate) {
        this.timePredicate = timePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFinanceList(timePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, model.getFilteredFinanceList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterTimeDueCommand)) {
            return false;
        }

        FilterTimeDueCommand otherFilterTimeDueCommand = (FilterTimeDueCommand) other;
        return timePredicate.equals(otherFilterTimeDueCommand.timePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", timePredicate)
                .toString();
    }
}
