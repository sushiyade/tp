package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.TimeDueBeforePredicate;
class FilterTimeDueCommandParserTest {
    private FilterTimeDueCommandParser parser = new FilterTimeDueCommandParser();

    @Test
    public void parse_validArgs_returnsFilterTimeDueCommand() throws ParseException {
        String args = " 01-01-2023";
        String hourMinArgs = args.trim() + " 00:00";
        LocalDateTime dateTime = parseDateTimeInstance(hourMinArgs);
        FilterTimeDueCommand expectedCommand = new FilterTimeDueCommand((new TimeDueBeforePredicate(dateTime)));
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTimeDueCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTimeDueCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

}

