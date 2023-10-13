package seedu.address.ui.tab;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FinanceTabTest {
    @Test
    public void constructor_initialize_exceptionThrown() {
        ExceptionInInitializerError thrown = assertThrows(
                ExceptionInInitializerError.class,
                FinanceTab::new,
                "Expected to throw, but it didn't"
        );

        assertNull(thrown.getMessage());
    }
}
