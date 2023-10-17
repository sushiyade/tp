package seedu.address.logic.commands.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.*;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        // to replace with EventBuilder
        Set<Person> clients = new HashSet<>();

        Event validEvent = null;
        try {
            validEvent = new Event(new EventName("Sample Event"),
                    new TimeStart(LocalDateTime.now()), new TimeEnd(LocalDateTime.now()), clients,
                    new Location(""), new EventDescription(""));
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
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddContactCommand(personInList), model,
                AddContactCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
