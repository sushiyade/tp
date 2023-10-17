package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
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
    public void constructor_startAfterEnd_throwsTimeStartAfterTimeEndException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        assertThrows(TimeStartAfterTimeEndException.class, () -> new Event(new EventName("Sample Event"),
                new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                new Location(""), new EventDescription("")));
    }

    @Test
    public void constructor_validParameters_createsEvent() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        try {
            Event event = new Event(new EventName("Sample Event"),
                    new TimeStart(startTime), new TimeEnd(endTime), new HashSet<>(),
                    new Location(""), new EventDescription(""));
            assertEquals("Sample Event", event.getName().toString());
            assertEquals(startTime, event.getTimeStart());
            assertEquals(endTime, event.getTimeEnd());
        } catch (TimeStartAfterTimeEndException e) {
            throw new AssertionError("TimeStartAfterTimeEndException should not be thrown.");
        }
    }

    @Test
    public void getClients_returnsCopyOfClientsList() {
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 1, 13, 0);
        Set<Person> clients = new HashSet<>();
        clients.add(new PersonBuilder(ALICE).build());
        try {
            Event event = new Event(new EventName("Sample Event"),
                    new TimeStart(startTime), new TimeEnd(endTime), clients,
                    new Location(""), new EventDescription(""));
            Set<Person> eventClients = event.getClients();
            assertEquals(clients, eventClients);
            assertFalse(eventClients == clients); // Ensure it's a copy, not the same list object.
        } catch (TimeStartAfterTimeEndException e) {
            throw new AssertionError("TimeStartAfterTimeEndException should not be thrown.");
        }

    }
}
