package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddCommissionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addSuccessful() throws Exception {
        CommandResult commandResult = new AddCommissionCommand(null).execute(model);
        assertEquals(commandResult, null);
    }
}
