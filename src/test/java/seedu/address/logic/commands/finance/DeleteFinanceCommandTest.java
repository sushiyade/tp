package seedu.address.logic.commands.finance;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFinances.getTypicalFinanceBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.finance.Finance;

public class DeleteFinanceCommandTest {
    private final Model model = new ModelManager(getTypicalFinanceBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Finance financeToDelete = model.getFilteredFinanceList().get(INDEX_FIRST.getZeroBased());
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFinanceCommand.MESSAGE_DELETE_FINANCE_SUCCESS,
                Messages.formatFinance(financeToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteFinance(financeToDelete);

        assertCommandSuccess(deleteFinanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFinanceList().size() + 1);
        DeleteFinanceCommand deleteFinanceCommand = new DeleteFinanceCommand(outOfBoundIndex);

        assertCommandFailure(deleteFinanceCommand, model, Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
    }
}
