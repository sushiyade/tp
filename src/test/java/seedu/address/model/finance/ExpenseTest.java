package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;
import static seedu.address.testutil.TypicalFinances.EXPENSE_THIRTY_TO_K;
import static seedu.address.testutil.TypicalFinances.EXPENSE_TWENTY_TO_G;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class ExpenseTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Expense(null, null, null, null));
    }

    @Test
    public void getAmount_returnsCorrectAmount() {
        assertEquals(EXPENSE_TWENTY_TO_G.getAmount(), new Amount("20"));
        assertEquals(EXPENSE_THIRTY_TO_K.getAmount(), new Amount("30"));
    }

    @Test
    public void getClient_returnsCorrectClient() {
        assertEquals(EXPENSE_TWENTY_TO_G.getClient().getName().fullName, "G");
        assertEquals(EXPENSE_THIRTY_TO_K.getClient().getName().fullName, "K");
    }

    @Test
    public void getDescription_returnsCorrectDescription() {
        assertEquals(EXPENSE_TWENTY_TO_G.getDescription(), new Description(""));
        assertEquals(EXPENSE_THIRTY_TO_K.getDescription(), new Description("Extra"));
    }

    @Test
    public void haveSameFields_sameFieldsReturnTrue() {
        Expense expense = new ExpenseBuilder()
                .withAmount("2103")
                .withPerson("Test")
                .withDescription("").build();
        Expense similarExpense = new ExpenseBuilder()
                .withAmount("2103")
                .withPerson("Test")
                .withDescription("").build();

        assertTrue(expense.haveSameFields(similarExpense));
    }

    @Test
    public void haveSameFields_differentFieldsReturnFalse() {
        Expense expense = new ExpenseBuilder()
                .withAmount("2103")
                .withPerson("Not test")
                .withDescription("").build();
        Expense similarExpense = new ExpenseBuilder()
                .withAmount("2103")
                .withPerson("Test")
                .withDescription("").build();

        assertFalse(expense.haveSameFields(similarExpense));
    }

    @Test
    public void equals() {
        Expense expense = new ExpenseBuilder().build();
        Expense similarExpense = new ExpenseBuilder().build();

        // same object -> returns true
        assertTrue(expense.equals(expense));

        // same values -> returns false
        assertFalse(expense.equals(similarExpense));

        // null -> returns false
        assertFalse(expense.equals(null));

        // different types -> returns false
        assertFalse(expense.equals(5.0f));
    }
    @Test
    public void toStringMethod() {
        String expected = Expense.class.getCanonicalName() + "{client=" + EXPENSE_TWENTY_TO_G.getClient()
                + ", amount=" + EXPENSE_TWENTY_TO_G.getAmount()
                + ", description=" + EXPENSE_TWENTY_TO_G.getDescription()
                + ", timeDue=" + COMMISSION_FROM_ALICE.getTimeDue() + "}";
        assertEquals(expected, EXPENSE_TWENTY_TO_G.toString());
    }
}
