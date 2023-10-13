package seedu.address.storage.finance;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * A class to access Finance data stored as a json file on the hard disk.
 */
public class JsonFinanceStorage implements FinancesStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonFinanceStorage(Path filePath) {
        this.filePath = filePath;
    }
}
