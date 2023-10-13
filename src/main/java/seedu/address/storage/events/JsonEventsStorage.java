package seedu.address.storage.events;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * A class to access Events data stored as a json file on the hard disk.
 */
public class JsonEventsStorage implements EventsStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonEventsStorage(Path filePath) {
        this.filePath = filePath;
    }
}
