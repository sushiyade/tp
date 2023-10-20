package seedu.address.storage.finance;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.storage.events.JsonSerializableEventsBook;
import seedu.address.testutil.TypicalEvents;

import java.nio.file.Path;
import java.nio.file.Paths;

import static seedu.address.testutil.Assert.assertThrows;

public class JsonSerializableFinancesBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFinancesBookTest");
    private static final Path TYPICAL_FINANCES_FILE = TEST_DATA_FOLDER.resolve("typicalFinancesBook.json");
    private static final Path INVALID_FINANCES_FILE = TEST_DATA_FOLDER.resolve("invalidFinancesBook.json");
    // private static final Path DUPLICATE_FINANCES_FILE = TEST_DATA_FOLDER.resolve("duplicateEventsBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFinancesBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FINANCES_FILE,
                JsonSerializableFinancesBook.class).get();
        FinancesBook eventsBookFromFile = dataFromFile.toModelType();
        //assertEquals(eventsBookFromFile, typicalEventEventsBook);
        //TODO: Fix test
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFinancesBook dataFromFile = JsonUtil.readJsonFile(INVALID_FINANCES_FILE,
                JsonSerializableFinancesBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
