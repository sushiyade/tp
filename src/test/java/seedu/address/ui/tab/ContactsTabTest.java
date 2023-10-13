package seedu.address.ui.tab;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactsTabTest {

    @Test
    public void constructor_Initialize_exceptionThrown() {
        ExceptionInInitializerError thrown = assertThrows(
                ExceptionInInitializerError.class,
                ContactsTab::new,
                "Expected to throw, but it didn't"
        );

        assertNull(thrown.getMessage());
    }
}
