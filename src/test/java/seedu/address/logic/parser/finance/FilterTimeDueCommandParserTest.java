package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeDuration;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.TimeDueBetweenPredicate;
class FilterTimeDueCommandParserTest {
    private FilterTimeDueCommandParser parser = new FilterTimeDueCommandParser();

    @Test
    public void parse_validArgs_returnsFilterTimeDueCommand() throws ParseException {
        String startArg = "Jan 19";
        String endArg = "Jan 20";
        LocalDateTime[] timeRange = parseDateTimeDuration(startArg, endArg);
        FilterTimeDueCommand expectedCommand = new FilterTimeDueCommand((new TimeDueBetweenPredicate(timeRange)));
        assertParseSuccess(parser, " " + PREFIX_TIME_START + startArg + " " + PREFIX_TIME_END + endArg,
                expectedCommand);
    }

    @Test
    public void parse_invalid_throwsParseException() {
        String args = "";

        // empty string
        assertParseFailure(parser, args, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTimeDueCommand.MESSAGE_USAGE));

        // missing start time
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PREFIX_TIME_START + "now",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTimeDueCommand.MESSAGE_USAGE));

        // missing start time
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PREFIX_TIME_END + "tomorrow",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTimeDueCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PREFIX_TIME_START + "now"
                + PREFIX_TIME_END + "tomorrow", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTimeDueCommand.MESSAGE_USAGE));

    }

}

