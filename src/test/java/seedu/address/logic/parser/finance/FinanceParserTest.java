package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.finance.AddCommissionCommand;
import seedu.address.logic.commands.finance.AddExpenseCommand;
import seedu.address.logic.commands.finance.DeleteFinanceCommand;
import seedu.address.logic.commands.finance.FilterClientNameCommand;
import seedu.address.logic.commands.finance.FilterTimeDueCommand;
import seedu.address.logic.commands.finance.ListFinanceCommand;
import seedu.address.logic.commands.finance.SummaryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.FinanceUtil;

public class FinanceParserTest {

    private final FinanceParser parser = new FinanceParser();

    @Test
    public void parseCommand_addCommission() throws Exception {
        Commission commission = new CommissionBuilder().build();
        AddCommissionCommand command =
                (AddCommissionCommand) parser.parseCommand(FinanceUtil.getAddCommissionCommand(commission));
        assertEquals(new AddCommissionCommand(commission), command);
    }

    @Test
    public void parseCommand_addExpense() throws Exception {
        Expense expense = new ExpenseBuilder().build();
        AddExpenseCommand command =
                (AddExpenseCommand) parser.parseCommand(FinanceUtil.getAddExpenseCommand(expense));
        assertEquals(new AddExpenseCommand(expense), command);
    }

    @Test
    public void parseCommand_deleteFinance() throws Exception {
        DeleteFinanceCommand command = (DeleteFinanceCommand) parser.parseCommand(
                DeleteFinanceCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteFinanceCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_listFinance() throws Exception {
        assertTrue(parser.parseCommand(ListFinanceCommand.COMMAND_WORD) instanceof ListFinanceCommand);
        assertTrue(parser.parseCommand(ListFinanceCommand.COMMAND_WORD + "  ") instanceof ListFinanceCommand);
        assertTrue(parser.parseCommand(
                ListFinanceCommand.COMMAND_WORD + " expense") instanceof ListFinanceCommand);
        assertTrue(parser.parseCommand(
                ListFinanceCommand.COMMAND_WORD + " commission") instanceof ListFinanceCommand);
    }
    @Test
    public void parseCommand_filterByClientNameFinance() throws Exception {
        assertTrue(parser.parseCommand(
                FilterClientNameCommand.COMMAND_WORD + " Alice") instanceof FilterClientNameCommand);
    }

    @Test
    public void parseCommand_filterByTimeDueFinance() throws Exception {
        String args = " 01-01-2023";
        assertTrue(parser.parseCommand(
                FilterTimeDueCommand.COMMAND_WORD + args) instanceof FilterTimeDueCommand);
    }
    @Test
    public void parseCommand_summaryFinance() throws Exception {
        String args = " John Doe";
        assertTrue(parser.parseCommand(
                SummaryCommand.COMMAND_WORD + args) instanceof SummaryCommand);
    }
    @Test
    public void parseCommand_tabCommand() throws ParseException {
        TabCommand command = (TabCommand) parser.parseCommand(
                TabCommand.COMMAND_WORD + " finance");
        assertEquals(new TabCommand("finance"), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }
    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }
}
