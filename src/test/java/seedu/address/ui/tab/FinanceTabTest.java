package seedu.address.ui.tab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FinanceTabTest {
    @Test
    public void constructor_Initialize_exceptionThrown() {
        ExceptionInInitializerError thrown = assertThrows(
                ExceptionInInitializerError.class,
                FinanceTab::new,
                "Expected to throw, but it didn't"
        );

        assertNull(thrown.getMessage());
    }
}
