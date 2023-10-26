package seedu.address.testutil;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

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
    public static final String DEFAULT_TIME_START = "23-09-2023 09:00";
    public static final String DEFAULT_TIME_END = "23-09-2023 10:00";
    public static final String DEFAULT_EVENT_DESCRIPTION = "Discussion";
    public static final String DEFAULT_LOCATION = "Conference Room";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

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
        timeStart = new TimeStart(LocalDateTime.parse(DEFAULT_TIME_START, DATE_TIME_FORMATTER));
        timeEnd = new TimeEnd(LocalDateTime.parse(DEFAULT_TIME_END, DATE_TIME_FORMATTER));
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
        if (!eventToCopy.getClients().isEmpty()) {
            clients = eventToCopy.getClients();
        }
        //clients = eventToCopy.getClients().isEmpty() ? null : eventToCopy.getClients();
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
        this.timeStart = new TimeStart(LocalDateTime.parse(timeStart, DATE_TIME_FORMATTER));
        return this;
    }

    /**
     * Sets the {@code TimeEnd} of the {@code Event} that we are building.
     */
    public EventBuilder withTimeEnd(String timeEnd) {
        this.timeEnd = new TimeEnd(LocalDateTime.parse(timeEnd, DATE_TIME_FORMATTER));
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
    public EventBuilder withClient(Set<Person> clients) {
        this.clients = clients;
        return this;
    }

    /**
     * Builds an event with the given attributes.
     */
    public Event build() {
        return new Event(eventName, timeStart, timeEnd, clients, location, eventDescription);
    }
}
