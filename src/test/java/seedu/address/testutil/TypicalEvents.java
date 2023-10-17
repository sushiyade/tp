package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;

import java.util.*;

public class TypicalEvents {

        public static final Event EVENT1;

    static {
        try {
            EVENT1 = new EventBuilder().withName("Meeting with Alice")
                    .withTimeStart("23-09-2023 09:00")
                    .withTimeEnd("23-09-2023 10:00")
                    .build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Event EVENT2;

    static {
        try {
            EVENT2 = new EventBuilder().withName("Conference with Bob")
                        .withTimeStart("24-09-2023 14:00")
                        .withTimeEnd("24-09-2023 18:00")
                        .build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
    }

    // Manually added
        public static final Event EVENT3;

    static {
        try {
            EVENT3 = new EventBuilder().withName("Team Lunch")
                    .withTimeStart("25-09-2023 12:00")
                    .withTimeEnd("25-09-2023 13:00")
                    .build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
    }

    // Manually added - Event's details found in {@code CommandTestUtil}
        public static final Event EVENT4;

    static {
        try {
            EVENT4 = new EventBuilder().withName("Project Presentation")
                    .withTimeStart("26-09-2023 15:00")
                    .withTimeEnd("26-09-2023 16:30")
                    .build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Event EVENT5;
    static {
        try {
            EVENT5 = new EventBuilder().withName("Meeting")
                    .withTimeStart("01-01-2023 14:00")
                    .withTimeEnd("01-01-2023 15:00")
                    .withLocation("Meeting Room")
                    .withEventDescription("Meeting for discussion")
                    .build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
    }

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1, EVENT2, EVENT3, EVENT4, EVENT5));
    }
}
