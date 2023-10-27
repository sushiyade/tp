package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.finance.Finance;
import seedu.address.model.finance.UniqueFinanceList;

/**
 * This class implements the finance book in the application
 */
public class FinancesBook implements ReadOnlyFinancesBook {
    private final UniqueFinanceList finances;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        finances = new UniqueFinanceList();
    }
    public FinancesBook() {}

    /**
     * Creates an FinancesBook using the Finances in the {@code toBeCopied}
     */
    public FinancesBook(ReadOnlyFinancesBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the finance list with {@code finances}.
     * {@code finances} must not contain duplicate finances.
     */
    public void setFinances(List<Finance> finances) {
        this.finances.setFinances(finances);
    }

    /**
     * Resets the existing data of this {@code FinancesBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFinancesBook newData) {
        requireNonNull(newData);

        setFinances(newData.getFinanceList());
    }


    // finance-level methods

    /**
     * Returns true if a finance with the same identity as {@code finance} exists in the finances book.
     */
    //public boolean hasFinance(Finance finance) {
    //    requireNonNull(finance);
    //    return finances.contains(finance);
    //}

    public void addFinance(Finance finance) {
        finances.add(finance);
    }

    public void removeFinance(Finance target) {
        finances.remove(target);
    }

    @Override
    public ObservableList<Finance> getFinanceList() {
        return finances.asUnmodifiableObservableList();
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("finances", finances)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FinancesBook)) {
            return false;
        }

        FinancesBook otherFinancesBook = (FinancesBook) other;
        return finances.equals(otherFinancesBook.finances);
    }

    @Override
    public int hashCode() {
        return finances.hashCode();
    }
}
