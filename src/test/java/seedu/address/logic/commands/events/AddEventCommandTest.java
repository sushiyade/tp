package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventsBook;
import seedu.address.model.ReadOnlyFinancesBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;





public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().withName("Daniel").build();
        new AddContactCommand(validPerson).execute(modelStub);

        Set<Person> validClients = new HashSet<>();
        validClients.add(new PersonBuilder().withName("Daniel").build());
        Event validEvent = new EventBuilder().withClient(validClients).build();
        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_invalidClients_throwsCommandException() throws CommandException {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().build();
        new AddContactCommand(validPerson).execute(modelStub);

        Person invalidPerson = new PersonBuilder().withName("Daniel").build();
        Set<Person> inValidClients = new HashSet<>();
        inValidClients.add(invalidPerson);
        Event validEvent = new EventBuilder().withClient(inValidClients).build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);

        assertThrows(CommandException.class, Messages.MESSAGE_CLIENT_DOES_NOT_EXIST, () ->
                addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event a = new EventBuilder().withName("a").build();
        Event b = new EventBuilder().withName("b").build();
        AddEventCommand addACommand = new AddEventCommand(a);
        AddEventCommand addBCommand = new AddEventCommand(b);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddEventCommand addACommandCopy = new AddEventCommand(a);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different person -> returns false
        assertFalse(addACommand.equals(addBCommand));
    }

    @Test
    public void toStringMethod() {
        AddEventCommand addEventCommand = new AddEventCommand(EVENT1);
        String expected = AddEventCommand.class.getCanonicalName() + "{toAdd=" + EVENT1 + "}";
        assertEquals(expected, addEventCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEventsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFinanceFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventsBook(ReadOnlyEventsBook eventsBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventsBook getEventsBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFinancesBook(ReadOnlyFinancesBook financesBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFinancesBook getFinancesBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event toAdd) {
            return false;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCommission(Commission commission) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Finance> getFilteredFinanceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredFinanceList(Predicate<Finance> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteFinance(Finance financeToDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isValidClient(Person client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Person> getAllMatchedClients(Set<Person> clients) {
            return clients;
        }

        @Override
        public Person getMatchedClient(Person client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<Finance> getFinanceList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public boolean isValidClient(Person client) {
            requireAllNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
