package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "Meeting with Alice";
    public static final String DEFAULT_TIME_START = "next year 09:00";
    public static final String DEFAULT_TIME_END = "next year 10:00";
    public static final String DEFAULT_EVENT_DESCRIPTION = "Discussion";
    public static final String DEFAULT_LOCATION = "Conference Room";

    private EventName eventName;
    private TimeStart timeStart;
    private TimeEnd timeEnd;
    private EventDescription eventDescription;
    private Location location;
    private Set<Person> clients = new HashSet<>();

    /**
     * Creates an {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_NAME);
        try {
            timeStart = new TimeStart(DateTimeParser.parseDateTimeInstance(DEFAULT_TIME_START));
            timeEnd = new TimeEnd(DateTimeParser.parseDateTimeInstance(DEFAULT_TIME_END));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        eventDescription = new EventDescription(DEFAULT_EVENT_DESCRIPTION);
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getName();
        timeStart = eventToCopy.getTimeStart();
        timeEnd = eventToCopy.getTimeEnd();
        eventDescription = eventToCopy.getDescription();
        location = eventToCopy.getLocation();
        clients = eventToCopy.getClients().isEmpty() ? null : eventToCopy.getClients();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code TimeStart} of the {@code Event} that we are building.
     */
    public EventBuilder withTimeStart(String timeStart) {
        try {
            this.timeStart = new TimeStart(DateTimeParser.parseDateTimeInstance(timeStart));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * Sets the {@code TimeEnd} of the {@code Event} that we are building.
     */
    public EventBuilder withTimeEnd(String timeEnd) {
        try {
            this.timeEnd = new TimeEnd(DateTimeParser.parseDateTimeInstance(timeEnd));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDescription(String description) {
        this.eventDescription = new EventDescription(description);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Person} associated with the {@code Event} that we are building.
     */
    public EventBuilder withClient(Person person) {
        this.clients.add(person);
        return this;
    }

    /**
     * Builds an event with the given attributes.
     */
    public Event build() {
        return new Event(eventName, timeStart, timeEnd, clients, location, eventDescription);
    }
}
