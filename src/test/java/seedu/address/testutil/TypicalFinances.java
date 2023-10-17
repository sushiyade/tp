package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.ClientName;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;

/**
 * A utility class containing a list of {@code Finance} objects to be used in tests.
 */
public class TypicalFinances {
    public static final Commission RECEIVED_TEN = new Commission(
            new Amount("10"),
            new ClientName("T"),
            new Description("")
    );
    public static final Commission RECEIVED_TWENTY = new Commission(
            new Amount("20"),
            new ClientName("H"),
            new Description("Payday")
    );

    public static final Expense SPENT_TWENTY = new Expense(
            new Amount("20"),
            new ClientName("G"),
            new Description("")
    );
    public static final Expense SPENT_THIRTY = new Expense(
            new Amount("30"),
            new ClientName("K"),
            new Description("Extra")
    );

    /**
     * Returns an {@code AddressBook} with all the typical finances.
     */
    public static AddressBook getTypicalFinanceBook() {
        AddressBook ab = new AddressBook();
        for (Finance finance : getTypicalFinances()) {
            ab.addFinance(finance);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical expenses.
     */
    public static AddressBook getTypicalExpenseOnlyBook() {
        AddressBook ab = new AddressBook();
        for (Finance finance : getTypicalExpensesOnly()) {
            ab.addFinance(finance);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical commissions.
     */
    public static AddressBook getTypicalCommissionOnlyBook() {
        AddressBook ab = new AddressBook();
        for (Finance finance : getTypicalCommissionsOnly()) {
            ab.addFinance(finance);
        }
        return ab;
    }

    public static List<Finance> getTypicalFinances() {
        return new ArrayList<>(Arrays.asList(RECEIVED_TEN, RECEIVED_TWENTY, SPENT_TWENTY, SPENT_THIRTY));
    }

    public static List<Finance> getTypicalExpensesOnly() {
        return new ArrayList<>(Arrays.asList(SPENT_TWENTY, SPENT_THIRTY));
    }

    public static List<Finance> getTypicalCommissionsOnly() {
        return new ArrayList<>(Arrays.asList(RECEIVED_TEN, RECEIVED_TWENTY));
    }
}
