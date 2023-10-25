package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EventTest {
    @Test
    public void constructor_noName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event(new EventName(""),
                new TimeStart(LocalDateTime.now()), new TimeEnd(LocalDateTime.now()), new HashSet<>(),
                new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_nullTimeStart_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(new EventName("Sample Event"), null,
                new TimeEnd(LocalDateTime.now()), new HashSet<>(),
                new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_nullTimeEnd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(new EventName("Sample Event"),
                new TimeStart(LocalDateTime.now()), null, new HashSet<>(),
                new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_validParameters_createsEvent() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Event event = new Event(new EventName("Sample Event"),
                new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertEquals("Sample Event", event.getName().toString());
        assertEquals(new TimeStart(startTime), event.getTimeStart());
        assertEquals(new TimeEnd(endTime), event.getTimeEnd());
    }

    @Test
    public void getClients_returnsCopyOfClientsList() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Set<Person> clients = new HashSet<>();
        clients.add(new PersonBuilder(ALICE).build());
        Event event = new Event(new EventName("Sample Event"),
                new TimeStart(startTime), new TimeEnd(endTime), clients,
                new Location(""), new EventDescription(""));
        Set<Person> eventClients = event.getClients();
        assertEquals(clients, eventClients);
        assertFalse(eventClients == clients); // Ensure it's a copy, not the same list object.
    }

    @Test
    public void equals() {
        // Test with same event name, start time and end time
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Event event1 = new Event(new EventName("Sample Event"),
                new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                new Location(""), new EventDescription(""));
        Event event2 = new Event(new EventName("Sample Event"),
                new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertTrue(event1.equals(event2));

        // Test with different event names
        Event event3 = new Event(new EventName("Different Event"),
                new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                new Location(""), new EventDescription(""));
        assertFalse(event1.equals(event3));
    }

}
