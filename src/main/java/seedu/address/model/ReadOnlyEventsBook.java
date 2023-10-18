package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public interface ReadOnlyEventsBook {
    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();
}
