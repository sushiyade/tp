package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the events book.
 * Guarantees: field values are validated, immutable.
 */
public class Event implements Comparable<Event> {

    private final EventName eventName;
    private final TimeStart timeStart;
    private final TimeEnd timeEnd;
    private Set<Person> clients = new HashSet<>();
    private final Location location;
    private final EventDescription eventDescription;

    /**
     * Constructs an {@code Event}.
     * @param eventName A valid name.
     * @param timeStart A valid start time.
     * @param timeEnd A valid end time after start time.
     * @param clients An optional list of clients.
     * @param location An optional location.
     * @param eventDescription An optional description.
     */
    public Event(EventName eventName, TimeStart timeStart, TimeEnd timeEnd, Set<Person> clients,
                 Location location, EventDescription eventDescription) {
        requireAllNonNull(eventName, timeStart, timeEnd);
        this.eventName = eventName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.clients.addAll(clients);
        this.location = location;
        this.eventDescription = eventDescription;
    }

    public EventName getEventName() {
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

    public EventDescription getDescription() {
        return eventDescription;
    }

    /**
     * Replace {@code clients} with set of {@code actualClients} found in the address book.
     */
    public void setMatchedClientInstance(Set<Person> actualClients) {
        clients = actualClients;
    }

    /**
     * Checks if both events have the same parameters.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event other) {
        boolean isSame = eventName.equals(other.getEventName());
        isSame = isSame && timeStart.equals(other.getTimeStart());
        isSame = isSame && timeEnd.equals(other.getTimeEnd());
        isSame = isSame && clients.equals(other.getClients());
        isSame = isSame && location.equals(other.getLocation());
        isSame = isSame && eventDescription.equals(other.getDescription());
        return isSame;
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return eventName.equals(otherEvent.eventName)
                && timeStart.equals(otherEvent.timeStart)
                && timeEnd.equals(otherEvent.timeEnd)
                && clients.equals(otherEvent.clients)
                && location.equals(otherEvent.location)
                && eventDescription.equals(otherEvent.eventDescription);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, timeStart, timeEnd, clients, location, eventDescription);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", eventName)
                .add("start", timeStart)
                .add("end", timeEnd)
                .add("clients", clients)
                .add("location", location)
                .add("description", eventDescription)
                .toString();
    }

    /**
     * Compares {@code Event} objects using the {@code TimeStart} parameter.
     *
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(Event o) {
        if (this.timeStart.isBefore(o.getTimeStart())) {
            return -1;
        } else if (o.getTimeStart().isBefore(this.timeStart)) {
            return 1;
        } else {
            return 0;
        }
    }
}
