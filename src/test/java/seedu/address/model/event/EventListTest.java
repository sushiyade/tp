package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventList;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.testutil.EventBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventListTest {

    @Test
    public void contains_eventNotInList_returnsFalse() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event = new EventBuilder().withName("Event 1").build();
        assertFalse(eventList.contains(event));
    }

    @Test
    public void contains_eventInList_returnsTrue() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event = new EventBuilder().withName("Event 1").build();
        eventList.add(event);
        assertTrue(eventList.contains(event));
    }

    @Test
    public void add_validEvent_returnsTrue() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event = new EventBuilder().withName("Event 1").build();
        eventList.add(event);
    }
    @Test
    public void remove_eventNotInList_returnsFalse() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event = new EventBuilder().withName("Event 1").build();
        eventList.remove(event);
        assertFalse(eventList.contains(event));
    }

    @Test
    public void remove_eventInList_returnsFalse() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event = new EventBuilder().withName("Event 1").build();
        eventList.add(event);
        eventList.remove(event);
        assertFalse(eventList.contains(event));
    }

    @Test
    public void iterator() throws TimeStartAfterTimeEndException {
        EventList eventList = new EventList();
        Event event1 = new EventBuilder().withName("Event 1").build();
        Event event2 = new EventBuilder().withName("Event 2").build();
        eventList.add(event1);
        eventList.add(event2);
        assertTrue(eventList.iterator().hasNext());
    }
}
