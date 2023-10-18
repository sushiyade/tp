package seedu.address.logic.commands.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.*;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonsBook(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent;
        try {
            validEvent = new EventBuilder().build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddEventCommand(validEvent), model,
                String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                expectedModel);
    }

    @Test
    public void execute_personDoesNotExist_throwsCommandException() {
        Set<Person> invalidClients = new HashSet<>();
        invalidClients.add(new PersonBuilder().withName("Daniel").build());
        Event eventWithInvalidClients = null;
        try {
            eventWithInvalidClients = new EventBuilder().withClient(invalidClients).build();
        } catch (TimeStartAfterTimeEndException e) {
            throw new RuntimeException(e);
        }
        assertCommandFailure(new AddEventCommand(eventWithInvalidClients), model,
                AddEventCommand.MESSAGE_CLIENT_DOES_NOT_EXIST);
    }

}
