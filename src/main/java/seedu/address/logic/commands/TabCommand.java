package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;

/**
 * Changes tabs in the program.
 */
public class TabCommand extends Command {

    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": changes the tab to the specified tab "
            + "Example: " + COMMAND_WORD + " finance";

    public static final String MESSAGE_TAB_ACKNOWLEDGEMENT = "Changing tabs too... %1$s";

    private final String tabName;

    public TabCommand(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public CommandResult execute(Model model) {
        Integer tabIndex;
        switch(tabName) {
        case "contacts":
            tabIndex = 0;
            break;
        case "finance":
            tabIndex = 1;
            break;
        case "events":
            tabIndex = 2;
            break;
        default: // should not reach here
            tabIndex = null;
        }
        return new CommandResult("", false, tabIndex, false);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabCommand)) {
            return false;
        }

        TabCommand otherTabCommand = (TabCommand) other;
        return tabName.equals(otherTabCommand.tabName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tabName", tabName)
                .toString();
    }
}
