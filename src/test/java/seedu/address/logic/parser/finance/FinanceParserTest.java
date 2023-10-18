package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.finance.AddCommissionCommand;
import seedu.address.logic.commands.finance.AddExpenseCommand;
import seedu.address.logic.commands.finance.DeleteFinanceCommand;
import seedu.address.logic.commands.finance.ListFinanceCommand;
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
        System.out.println(commission);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
