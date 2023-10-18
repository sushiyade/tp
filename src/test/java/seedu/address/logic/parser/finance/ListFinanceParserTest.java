package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.finance.ListFinanceCommand;
import seedu.address.model.finance.FinanceListType;

class ListFinanceParserTest {
    private final ListFinanceParser parser = new ListFinanceParser();

    @Test
    public void parse_validArgs_returnsListFinanceCommand() {
        ListFinanceCommand expectedListAllFinanceCommand = new ListFinanceCommand(FinanceListType.ALL);
        ListFinanceCommand expectedListExpenseFinanceCommand = new ListFinanceCommand(FinanceListType.EXPENSE);
        ListFinanceCommand expectedListCommissionFinanceCommand = new ListFinanceCommand(FinanceListType.COMMISSION);

        assertParseSuccess(parser, "       ", expectedListAllFinanceCommand);
        assertParseSuccess(parser, "expense", expectedListExpenseFinanceCommand);
        assertParseSuccess(parser, "commission", expectedListCommissionFinanceCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "fail",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListFinanceCommand.MESSAGE_USAGE));
    }

}
