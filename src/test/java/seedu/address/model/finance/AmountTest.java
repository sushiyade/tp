package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void equals() {
        Amount amount = new Amount(80);

        // same object -> returns true
        assertTrue(amount.equals(amount));

        // same values -> returns true
        assertTrue(amount.equals(new Amount(80)));

        // null -> returns false
        assertFalse(amount.equals(null));

        // different types -> returns false
        assertFalse(amount.equals(5.0f));

        // different values -> returns false
        assertFalse(amount.equals(new Amount(100)));
    }
}
