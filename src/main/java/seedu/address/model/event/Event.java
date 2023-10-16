package seedu.address.model.event;

import java.util.*;

import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 * Guarantees: field values are validated, immutable.
 */
public class Event {

    private final EventName eventName;
    private final TimeStart timeStart;
    private final TimeEnd timeEnd;
    private final Set<Person> clients = new HashSet<>();
    private final Location location;
    private final Description description;


    /**
     * Constructs an {@code Event}.
     * @param eventName A valid name.
     * @param timeStart A valid start time.
     * @param timeEnd A valid end time after start time.
     * @param clients An optional list of clients.
     * @param location An optional location.
     * @param description An optional description.
     * @throws TimeStartAfterTimeEndException if the start time is after the end time.
     */
    public Event(EventName eventName, TimeStart timeStart, TimeEnd timeEnd, Set<Person> clients,
                 Location location, Description description) throws TimeStartAfterTimeEndException {
        this.eventName = eventName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;

        if (timeStart.isAfter(timeEnd)) {
            throw new TimeStartAfterTimeEndException();
        }

        this.clients.addAll(clients);
        this.location = location;
        this.description = description;
    }

    public EventName getName() {
        return eventName;
    }

    public TimeStart getTimeStart() {
        return timeStart;
    }

    public TimeEnd getTimeEnd() {
        return timeEnd;
    }

    public Set<Person> getClients() {
        return Collections.unmodifiableSet(clients); // Return a copy of the client list
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, timeStart, timeEnd, clients, location, description);
    }
}
