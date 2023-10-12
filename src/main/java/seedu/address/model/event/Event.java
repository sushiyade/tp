package seedu.address.model.event;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Event {

    private final Name name;
    private final LocalDateTime timeStart;
    private final LocalDateTime timeEnd;
    private final List<Person> clients;
    private Location location;
    private Description description;


    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this(name, timeStart, timeEnd, new ArrayList<>(), null, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd, List<Person> clients) {
        this(name, timeStart, timeEnd, clients, null, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd, Location location) {
        this(name, timeStart, timeEnd, new ArrayList<>(), location, null);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd, Description description) {
        this(name, timeStart, timeEnd, new ArrayList<>(), null, description);
    }

    public Event(Name name, LocalDateTime timeStart, LocalDateTime timeEnd, List<Person> clients, Location location, Description description) {
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.clients = new ArrayList<>(clients);
        this.location = location;
        this.description = description;
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
}
