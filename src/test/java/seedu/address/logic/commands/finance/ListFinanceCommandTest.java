package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.finance.FinanceListType;

public class ListFinanceCommandTest {
    private final Model model = new ModelManager(getTypicalPersonsBook(), new UserPrefs());

    @Test
    public void execute_addSuccessful() throws Exception {
        CommandResult commandResult = new ListFinanceCommand(FinanceListType.ALL).execute(model);
        assertNull(commandResult);
    }
}
