package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

import org.junit.jupiter.api.Test;

class FinanceListTest {
    private final FinanceList financeList = new FinanceList();
    @Test
    public void contains_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> financeList.contains(null));
    }
    @Test
    public void contains_commissionNotInList_returnsFalse() {
        assertFalse(financeList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void contains_commissionInList_returnsTrue() {
        financeList.add(COMMISSION_FROM_ALICE);
        assertTrue(financeList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> financeList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void toStringMethod() {
        assertEquals(financeList.asUnmodifiableObservableList().toString(), financeList.toString());
    }
}

