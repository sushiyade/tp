package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.testutil.EventBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventListTest {

    @Test
    public void contains_eventNotInList_returnsFalse() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        assertFalse(uniqueEventList.contains(event));
    }

    @Test
    public void contains_eventInList_returnsTrue() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        uniqueEventList.add(event);
        assertTrue(uniqueEventList.contains(event));
    }

    @Test
    public void add_validEvent_returnsTrue() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        uniqueEventList.add(event);
    }
    @Test
    public void remove_eventNotInList_returnsFalse() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        uniqueEventList.remove(event);
        assertFalse(uniqueEventList.contains(event));
    }

    @Test
    public void remove_eventInList_returnsFalse() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event = new EventBuilder().withName("Event 1").build();
        uniqueEventList.add(event);
        uniqueEventList.remove(event);
        assertFalse(uniqueEventList.contains(event));
    }

    @Test
    public void iterator() throws TimeStartAfterTimeEndException {
        UniqueEventList uniqueEventList = new UniqueEventList();
        Event event1 = new EventBuilder().withName("Event 1").build();
        Event event2 = new EventBuilder().withName("Event 2").build();
        uniqueEventList.add(event1);
        uniqueEventList.add(event2);
        assertTrue(uniqueEventList.iterator().hasNext());
    }
}
