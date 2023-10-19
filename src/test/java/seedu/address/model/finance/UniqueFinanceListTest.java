package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

import org.junit.jupiter.api.Test;

class UniqueFinanceListTest {
    private final UniqueFinanceList uniqueFinanceList = new UniqueFinanceList();
    @Test
    public void contains_nullCommission_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFinanceList.contains(null));
    }
    @Test
    public void contains_commissionNotInList_returnsFalse() {
        assertFalse(uniqueFinanceList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void contains_commissionInList_returnsTrue() {
        uniqueFinanceList.add(COMMISSION_FROM_ALICE);
        assertTrue(uniqueFinanceList.contains(COMMISSION_FROM_ALICE));
    }
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueFinanceList.asUnmodifiableObservableList().remove(0));
    }
    @Test
    public void toStringMethod() {
        assertEquals(uniqueFinanceList.asUnmodifiableObservableList().toString(), uniqueFinanceList.toString());
    }
}

