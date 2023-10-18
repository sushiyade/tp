package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFinances.getTypicalCommissionOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalExpenseOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contacts.ClearContactCommand;
import seedu.address.model.*;
import seedu.address.model.finance.FinanceListType;

public class ListFinanceCommandTest {
    private Model model;
    private Model expectedAllModel;
    private Model expectedExpenseOnlyModel;
    private Model expectedCommissionOnlyModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
        expectedAllModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
        expectedExpenseOnlyModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalExpenseOnlyBook(), new UserPrefs());
        expectedCommissionOnlyModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalCommissionOnlyBook(), new UserPrefs());
    }

    @Test
    public void execute_listShowsAll_listAllShowsAllList() {
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.ALL),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedAllModel);
        assertEquals(model.getFilteredFinanceList(), expectedAllModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsAll_listExpenseShowsExpenseOnlyList() {
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.EXPENSE),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedExpenseOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedExpenseOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsAll_listCommissionShowsCommissionOnlyList() {
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.COMMISSION),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedCommissionOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedCommissionOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void execute_listShowsExpenseOrCommissionOnly_listAllShowsEverything() {
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.EXPENSE),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedExpenseOnlyModel);
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.ALL),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedExpenseOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedAllModel.getFilteredFinanceList());

        assertCommandSuccess(new ListFinanceCommand(FinanceListType.COMMISSION),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedCommissionOnlyModel);
        assertCommandSuccess(new ListFinanceCommand(FinanceListType.COMMISSION),
                model, ListFinanceCommand.MESSAGE_SUCCESS, expectedCommissionOnlyModel);
        assertEquals(model.getFilteredFinanceList(), expectedCommissionOnlyModel.getFilteredFinanceList());

    }

    @Test
    public void equals() {
        final ListFinanceCommand standardCommand = new ListFinanceCommand(FinanceListType.ALL);

        // same list type -> returns true
        ListFinanceCommand listAllFinanceCommand = new ListFinanceCommand(FinanceListType.ALL);
        assertTrue(standardCommand.equals(listAllFinanceCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearContactCommand()));

        // different list type -> returns false
        assertFalse(standardCommand.equals(new ListFinanceCommand(FinanceListType.EXPENSE)));
    }

}
