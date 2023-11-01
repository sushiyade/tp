package seedu.address.logic.commands.finance;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.events.EditEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.event.*;
import seedu.address.model.finance.*;
import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class EditFinanceCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the finance identified "
            + "by the index number used in the displayed finance list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_CLIENT + "CLIENT] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TIME_DUE + "TIME_DUE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_AMOUNT + "300 "
            + PREFIX_DESCRIPTION + "Photoshop subscription";

    public static final String MESSAGE_EDIT_FINANCE_SUCCESS = "Edited Finance: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_FINANCE = "This finance already exists in the finance book";

    private final Index index;
    private final EditFinanceDescriptor editFinanceDescriptor;

    /**
     * @param index of the event in the filtered finance list to edit
     * @param editFinanceDescriptor details to edit the finance with
     */
    public EditFinanceCommand(Index index, EditFinanceDescriptor editFinanceDescriptor) {
        requireNonNull(index);
        requireNonNull(editFinanceDescriptor);

        this.index = index;
        this.editFinanceDescriptor = new EditFinanceDescriptor(editFinanceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Finance> lastShownList = model.getFilteredFinanceList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
        }

        Finance financeToEdit = lastShownList.get(index.getZeroBased());
        Finance editedFinance = null;

        editedFinance = createEditedFinance(financeToEdit, editFinanceDescriptor);

        if (!financeToEdit.isSameFinance(editedFinance) && model.hasFinance(editedFinance)) {
            throw new CommandException(MESSAGE_DUPLICATE_FINANCE);
        }

        if (financeToEdit instanceof Expense && editedFinance instanceof Expense) {
            model.setExpense((Expense) financeToEdit, (Expense) editedFinance);
        } else if (financeToEdit instanceof Commission && editedFinance instanceof Commission) {
            model.setCommission((Commission) financeToEdit, (Commission) editedFinance);
        }
        model.updateFilteredFinanceList(Model.PREDICATE_SHOW_ALL_FINANCES);
        return new CommandResult(String.format(MESSAGE_EDIT_FINANCE_SUCCESS, Messages.formatFinance(editedFinance)));
    }


    /**
     * Creates and returns a {@code Finance} with the details of {@code financeToEdit}
     * edited with {@code editFinanceDescriptor}.
     */
    private static Finance createEditedFinance(Finance financeToEdit, EditFinanceDescriptor editFinanceDescriptor)
            throws CommandException {
        assert financeToEdit != null;

        Amount updatedAmount = editFinanceDescriptor.getAmount().orElse(financeToEdit.getAmount());
        Person updatedClient = editFinanceDescriptor.getClient().orElse(financeToEdit.getClient());
        Description updatedDescription = editFinanceDescriptor.getDescription().orElse(financeToEdit.getDescription());
        TimeDue updatedTimeDue = editFinanceDescriptor.getTimeDue().orElse(financeToEdit.getTimeDue());

        if (financeToEdit instanceof Expense) {
            return new Expense(updatedAmount, updatedClient, updatedDescription, updatedTimeDue);
        } else {
            return new Commission(updatedAmount, updatedClient, updatedDescription, updatedTimeDue);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFinanceCommand)) {
            return false;
        }

        EditFinanceCommand otherEditFinanceCommand = (EditFinanceCommand) other;
        return index.equals(otherEditFinanceCommand.index)
                && editFinanceDescriptor.equals(otherEditFinanceCommand.editFinanceDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editFinanceDescriptor", editFinanceDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the finance with. Each non-empty field value will replace the
     * corresponding field value of the finance.
     */
    public static class EditFinanceDescriptor {
        private Amount amount;
        private Person client;
        private Description description;
        private TimeDue timeDue;

        public EditFinanceDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditFinanceDescriptor(EditFinanceDescriptor toCopy) {
            setAmount(toCopy.amount);
            setClient(toCopy.client);
            setDescription(toCopy.description);
            setTimeDue(toCopy.timeDue);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, client, description, timeDue);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setClient(Person client) {
            this.client = client;
        }
        public Optional<Person> getClient() {
            return Optional.ofNullable(client);
        }

        public void setDescription(Description description) {
            this.description = description;
        }
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setTimeDue(TimeDue timeDue) {
            this.timeDue = timeDue;
        }
        public Optional<TimeDue> getTimeDue() {
            return Optional.ofNullable(timeDue);
        }




        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFinanceDescriptor)) {
                return false;
            }

            EditFinanceDescriptor otherEditFinanceDescriptor = (EditFinanceDescriptor) other;
            return Objects.equals(amount, otherEditFinanceDescriptor.amount)
                    && Objects.equals(client, otherEditFinanceDescriptor.client)
                    && Objects.equals(description, otherEditFinanceDescriptor.description)
                    && Objects.equals(timeDue, otherEditFinanceDescriptor.timeDue);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("amount", amount)
                    .add("client", client)
                    .add("description", description)
                    .add("timeDue", timeDue)
                    .toString();
        }
    }
}
