package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 * Guarantees: field values are validated, immutable.
 */
public class Event {

    private final Name name;
    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;
    private final List<Person> clients;
    private final Location location;
    private final Description description;


    /**
     * Constructs an {@code Event}.
     * @param name A valid name.
     * @param timeStart A valid start time.
     * @param timeEnd A valid end time after start time.
     * @param clients An optional list of clients.
     * @param location An optional location.
     * @param description An optional description.
     * @throws TimeStartAfterTimeEndException if the start time is after the end time.
     */
    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd, List<Person> clients,
                 Location location, Description description) throws TimeStartAfterTimeEndException {
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;

        if (timeStart.isAfter(timeEnd)) {
            throw new TimeStartAfterTimeEndException("TimeStart cannot be after TimeEnd.");
        }

        this.clients = new ArrayList<>(clients);
        this.location = location;
        this.description = description;
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, new ArrayList<>(), null, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd,
                 List<Person> clients) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, clients, null, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd,
                 Location location) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, new ArrayList<>(), location, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd,
                 Description description) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, new ArrayList<>(), null, description);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd,
                 List<Person> clients, Location location) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, clients, location, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd,
                 List<Person> clients, Description description) throws TimeStartAfterTimeEndException {
        this(name, timeStart, timeEnd, clients, null, description);
    }



    public Name getName() {
        return name;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public List<Person> getClients() {
        return new ArrayList<>(clients); // Return a copy of the client list
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
        return Objects.hash(name, timeStart, timeEnd, clients, location, description);
    }
}
