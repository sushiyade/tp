package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.TimeDueBeforePredicate;
/**
 * Parses input arguments and creates a new FilterTimeDueCommand object
 */
public class FilterTimeDueCommandParser implements Parser<FilterTimeDueCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTimeDueCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTimeDueCommand.MESSAGE_USAGE));
        }
        return new FilterTimeDueCommand(new TimeDueBeforePredicate(parseDateTimeInstance(trimmedArgs)));
    }
}
