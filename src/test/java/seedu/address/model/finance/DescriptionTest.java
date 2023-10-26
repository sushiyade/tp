package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }
    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));
        // invalid description
        assertFalse(Description.isValidDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // 257 characters

        assertFalse(Description.isValidDescription(" ")); // whitespace
        //valid description
        assertTrue(Description.isValidDescription("")); // blank
        assertTrue(Description.isValidDescription("Test description 123")); // alphanumeric only
    }

    @Test
    public void equals() {
        Description description = new Description("Test Description");

        // same object -> returns true
        assertTrue(description.equals(description));

        // same values -> returns true
        assertTrue(description.equals(new Description("Test Description")));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Description("Other Description")));
    }
}
