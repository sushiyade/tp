package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EventsBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event EVENT1;

    static {
        EVENT1 = new EventBuilder().withName("Meeting with Alice")
                .withTimeStart("23-09-2023 09:00")
                .withTimeEnd("23-09-2023 10:00")
                .build();
    }

    public static final Event EVENT2;

    static {
        EVENT2 = new EventBuilder().withName("Conference with Bob")
                    .withTimeStart("24-09-2023 14:00")
                    .withTimeEnd("24-09-2023 18:00")
                    .build();
    }

    // Manually added
    public static final Event EVENT3;

    static {
        EVENT3 = new EventBuilder().withName("Team Lunch")
                .withTimeStart("25-09-2023 12:00")
                .withTimeEnd("25-09-2023 13:00")
                .build();
    }

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event EVENT4;

    static {
        EVENT4 = new EventBuilder().withName("Project Presentation")
                .withTimeStart("26-09-2023 15:00")
                .withTimeEnd("26-09-2023 16:30")
                .build();
    }

    public static final Event EVENT5;
    static {
        EVENT5 = new EventBuilder().withName("Meeting")
                .withTimeStart("01-01-2024 14:00")
                .withTimeEnd("01-01-2024 15:00")
                .withLocation("Meeting Room")
                .withEventDescription("Meeting for discussion")
                .build();
    }

    public static final Event EVENT6;
    static {
        EVENT6 = new EventBuilder().withName("Meeting")
                .withTimeStart("01-01-2099 14:00")
                .withTimeEnd("01-01-2099 15:00")
                .withLocation("Meeting Room")
                .withEventDescription("Meeting for discussion")
                .build();
    }

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventsBook} with all the typical persons.
     */
    public static EventsBook getTypicalEventsBook() {
        EventsBook eb = new EventsBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }

        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1, EVENT2, EVENT3, EVENT4, EVENT5, EVENT6));
    }
}
