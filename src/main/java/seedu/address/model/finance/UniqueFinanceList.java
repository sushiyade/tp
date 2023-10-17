package seedu.address.model.finance;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;

/**
 * A list of Finances that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueFinanceList implements Iterable<Finance> {
    private final ObservableList<Finance> internalList = FXCollections.observableArrayList();
    private final ObservableList<Finance> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Finance toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }
    /**
     * Returns true if the list contains an equivalent Finance as the given argument.
     */
    public boolean contains(Finance toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void setFinances(UniqueFinanceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code finances}.
     * {@code finances} must not contain duplicate finances.
     */
    public void setFinances(List<Finance> finances) {
        requireNonNull(finances);
        internalList.setAll(finances);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Finance> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<Finance> iterator() {
        return internalList.iterator();
    }
    @Override
    public String toString() {
        return internalList.toString();
    }
}
