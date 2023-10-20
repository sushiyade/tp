package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class TabCommandTest {
    private Model model = new ModelManager();

    private Model expectedModel = new ModelManager();

    @Test
    public void execute_tabContacts_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, 0, false);
        TabCommand command = new TabCommand("contacts");
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabFinance_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, 1, false);
        TabCommand command = new TabCommand("finance");
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabEvents_success() {
        CommandResult expectedCommandResult = new CommandResult("", false, 2, false);
        TabCommand command = new TabCommand("events");
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_tabInvalid_failure() {
        CommandResult expectedCommandResult = new CommandResult("", false, null, false);
        TabCommand command = new TabCommand("");
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        TabCommand command1 = new TabCommand("contacts");
        TabCommand command2 = new TabCommand("contacts");
        TabCommand command3 = new TabCommand("finance");

        assertEquals(command1, command1);
        assertEquals(command1, command2);
        assertNotEquals(command1, 1);
        assertNotEquals(command1, null);
        assertNotEquals(command1, command3);
    }

    @Test
    public void testToStringMethod() {
        TabCommand command = new TabCommand("contacts");
        String expected = TabCommand.class.getCanonicalName() + "{tabName=contacts}";
        assertEquals(expected, command.toString());
    }
}
