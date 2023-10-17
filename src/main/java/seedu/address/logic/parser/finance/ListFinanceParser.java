package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.finance.ListFinanceCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.FinanceListType;

/**
 * Parses input arguments and creates a new ListFinanceCommand object
 */
public class ListFinanceParser implements Parser<ListFinanceCommand> {
    @Override
    public ListFinanceCommand parse(String args) throws ParseException {
        try {
            FinanceListType type = ParserUtil.parseFinanceListType(args);
            return new ListFinanceCommand(type);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListFinanceCommand.MESSAGE_USAGE), pe);
        }
    }
}
