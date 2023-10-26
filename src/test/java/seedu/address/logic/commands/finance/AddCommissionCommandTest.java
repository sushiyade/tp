package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddCommissionCommandTest {

    @Test
    public void execute_addSuccessful() throws Exception {
        ModelStubAcceptingCommissionAdded modelStub = new ModelStubAcceptingCommissionAdded();
        Person validPerson = new PersonBuilder().withName(CommissionBuilder.DEFAULT_NAME).build();
        new AddContactCommand(validPerson).execute(modelStub);
        Commission commission = new CommissionBuilder().build();
        CommandResult commandResult = new AddCommissionCommand(commission).execute(modelStub);
        assertEquals(String.format(AddCommissionCommand.MESSAGE_SUCCESS, Messages.formatFinance(commission)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(commission), modelStub.commissionsAdded);
    }

    @Test
    public void execute_invalidClient_throwsException() {
        ModelStubAcceptingCommissionAdded modelStub = new ModelStubAcceptingCommissionAdded();
        Commission commission = new CommissionBuilder().build();
        assertThrows(CommandException.class, () -> new AddCommissionCommand(commission).execute(modelStub),
                Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        Commission alice = new CommissionBuilder().withPerson("Alice").build();
        Commission bob = new CommissionBuilder().withPerson("Bob").build();
        AddCommissionCommand addAliceCommand = new AddCommissionCommand(alice);
        AddCommissionCommand addBobCommand = new AddCommissionCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommissionCommand addAliceCommandCopy = new AddCommissionCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different commission -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommissionCommand addCommissionCommand = new AddCommissionCommand(COMMISSION_FROM_ALICE);
        String expected = AddCommissionCommand.class.getCanonicalName() + "{toAdd=" + COMMISSION_FROM_ALICE + "}";
        assertEquals(expected, addCommissionCommand.toString());
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
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person commission) {
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
        public boolean hasPerson(Person commission) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isValidClient(Person client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Set<Person> getAllMatchedClients(Set<Person> clients) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getMatchedClient(Person client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public ObservableList<Finance> getFinanceList() {
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
    }

    private class ModelStubAcceptingCommissionAdded extends ModelStub {
        final ArrayList<Commission> commissionsAdded = new ArrayList<>();
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addCommission(Commission commission) {
            requireNonNull(commission);
            commissionsAdded.add(commission);
        }

        @Override
        public boolean isValidClient(Person client) {
            requireAllNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Person getMatchedClient(Person client) {
            for (Person p : personsAdded) {
                if (p.equals(client)) {
                    return p;
                }
            }
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
