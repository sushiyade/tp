package seedu.address.logic.parser.events;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_TIME;

import static seedu.address.logic.commands.CommandTestUtil.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalEvents.EVENT5;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

import java.util.Set;

public class AddEventCommandParserTest {

    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_DuplicateInvalidFormat_failure() throws TimeStartAfterTimeEndException {
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                new TimeStart(VALID_TIME_START_MEETING),
                new TimeEnd(VALID_TIME_END_MEETING),
                Set.of(new PersonBuilder().build()),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        // multiple names - last name accepted
        assertParseFailure(parser, " n/Birthday Meeting n/Meeting",
                MESSAGE_INVALID_COMMAND_FORMAT);
        // multiple timeStart - last timeStart accepted
        assertParseFailure(parser, " s/01-01-2023 14:00 s/01-01-2023 15:00",
                MESSAGE_INVALID_COMMAND_FORMAT);
        // multiple timeEnd - last timeEnd accepted
        assertParseFailure(parser, " e/01-01-2023 15:00 e/01-01-2023 16:00",
                MESSAGE_INVALID_COMMAND_FORMAT);
        // multiple clients - all accepted
        assertParseFailure(parser, " c/Bob c/Alice",
                MESSAGE_INVALID_COMMAND_FORMAT);
        // multiple locations - last location accepted
        assertParseFailure(parser, " l/Meeting Room l/Conference Room",
                MESSAGE_INVALID_COMMAND_FORMAT);
        // multiple descriptions - last description accepted
        assertParseFailure(parser, " d/Meeting for discussion d/Team meeting",
                MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws ParseException, TimeStartAfterTimeEndException {
        final Event expectedEvent1;
        expectedEvent1 = new EventBuilder(EVENT5).withEventDescription("").build();

        final Event expectedEvent2;
        expectedEvent2 = new EventBuilder(EVENT5).withLocation("").build();

        final Event expectedEvent3;
        expectedEvent3 = new EventBuilder(EVENT5).withLocation("").withEventDescription("").build();

        //no description
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 l/Meeting Room",
                new AddEventCommand(expectedEvent1));
        // no location
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 d/Meeting for discussion",
                new AddEventCommand(expectedEvent2));
        // no description and location
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00",
                new AddEventCommand(expectedEvent3));
    }

    @Test
    public void parse_compulsoryFieldMissing_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " s/01-01-2023 14:00 e/01-01-2023 15:00", expectedMessage);

        // missing timeStart prefix
        assertParseFailure(parser, " n/Meeting e/01-01-2023 15:00", expectedMessage);

        // missing timeEnd prefix
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + VALID_EVENT_NAME_MEETING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // invalid name
        assertParseFailure(parser, " n/ Me@ting s/01-01-2023 14:00 e/01-01-2023 15:00", EventName.MESSAGE_CONSTRAINTS);
        // invalid timeStart
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00x e/01-01-2023 15:00", TimeStart.MESSAGE_CONSTRAINTS);
        // invalid timeEnd
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00x", TimeEnd.MESSAGE_CONSTRAINTS);
        // invalid client
        assertParseFailure(parser, " c/ Alic@", Name.MESSAGE_CONSTRAINTS);
        // invalid location
        assertParseFailure(parser, " l/Con@ference Room", Location.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_timeStartAfterTimeEnd_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_DATE_TIME);

        // timeStart after timeEnd
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 13:00",
                expectedMessage);
    }

    @Test
    public void parse_preambleNonEmpty_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // non-empty preamble
        assertParseFailure(parser, " some random string", expectedMessage);
    }

    @Test
    public void parse_validInput_success() throws ParseException, TimeStartAfterTimeEndException {
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                new TimeStart(VALID_TIME_START_MEETING),
                new TimeEnd(VALID_TIME_END_MEETING),
                Set.of(new PersonBuilder().build()),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        // valid input
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/Bob l/Meeting Room d/Meeting for discussion",
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_validClientPrefixForMultipleClients_success() throws ParseException, TimeStartAfterTimeEndException {
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                new TimeStart(VALID_TIME_START_MEETING),
                new TimeEnd(VALID_TIME_END_MEETING),
                Set.of(new PersonBuilder().withName(VALID_NAME_AMY).build(), new PersonBuilder().build()),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/Amy c/Bob l/Meeting Room d/Meeting for discussion",
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_validEventWithAllValidClients_success() throws ParseException, TimeStartAfterTimeEndException {
        // Multiple clients, including valid and invalid clients
        Event expectedEvent = new Event(new EventName(VALID_EVENT_NAME_MEETING),
                new TimeStart(VALID_TIME_START_MEETING),
                new TimeEnd(VALID_TIME_END_MEETING),
                Set.of(new PersonBuilder().build()),
                new Location(VALID_LOCATION_MEETING),
                new EventDescription(VALID_DESCRIPTION_MEETING));

        // Extra whitespace should be ignored when parsing the list of clients
        assertParseSuccess(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/Bob, c/Alice l/Meeting Room d/Meeting for discussion",
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_validEventWithInvalidClients_throwsParseException() {
        // Only invalid clients
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/Alic@ l/Meeting Room d/Meeting for discussion",
                Name.MESSAGE_CONSTRAINTS);

        // Both valid and invalid clients
        assertParseFailure(parser, " n/Meeting s/01-01-2023 14:00 e/01-01-2023 15:00 c/Bob, c/ Alic@ l/Meeting Room d/Meeting for discussion",
                Name.MESSAGE_CONSTRAINTS);
    }

}
