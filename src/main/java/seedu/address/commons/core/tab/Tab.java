package seedu.address.commons.core.tab;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a zero-based or one-based tab index.
 */
public class Tab {
    public static final String INVALID_PARAMETER = "INVALID";
    public static final String CONTACT_PARAMETER = "contacts";
    public static final String FINANCE_PARAMETER = "finance";
    public static final String EVENTS_PARAMETER = "events";
    public static final int INVALID_INDEX = -1;
    public static final int CONTACTS_TAB_INDEX = 0;
    public static final int FINANCE_TAB_INDEX = 1;
    public static final int EVENTS_TAB_INDEX = 2;
    public static final int NUM_OF_TABS = 3;

    private final int zeroBasedTabIndex;

    /**
     * Tab can only be created by calling {@link Tab#fromZeroBased(int)} or
     * {@link Tab#fromOneBased(int)} or {@link Tab#fromParameter(String)}.
     */
    private Tab(int zeroBasedTabIndex) {
        if (zeroBasedTabIndex < 0 || zeroBasedTabIndex >= NUM_OF_TABS) {
            throw new IndexOutOfBoundsException();
        }

        this.zeroBasedTabIndex = zeroBasedTabIndex;
    }

    public String getTabCommand() {
        return getParameterFromIndex(zeroBasedTabIndex);
    }
    public int getZeroBasedTabIndex() {
        return zeroBasedTabIndex;
    }

    public int getOneBasedTabIndex() {
        return zeroBasedTabIndex + 1;
    }

    /**
     * Creates a new {@code Tab} using a zero-based tab index.
     */
    public static Tab fromZeroBased(int zeroBasedIndex) {
        return new Tab(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Tab} using a one-based tab index.
     */
    public static Tab fromOneBased(int oneBasedIndex) {
        return new Tab(oneBasedIndex - 1);
    }

    public static Tab fromParameter(String parameter) {
        return new Tab(getIndexFromParameter(parameter));
    }

    private static int getIndexFromParameter(String parameter) {
        int tabIndex = INVALID_INDEX;

        switch (parameter) {
        case CONTACT_PARAMETER:
            tabIndex = CONTACTS_TAB_INDEX;
            break;
        case FINANCE_PARAMETER:
            tabIndex = FINANCE_TAB_INDEX;
            break;
        case EVENTS_PARAMETER:
            tabIndex = EVENTS_TAB_INDEX;
            break;
        default:
            tabIndex = INVALID_INDEX;
        }

        return tabIndex;
    }

    private static String getParameterFromIndex(int zeroBasedTabIndex) {
        String command = INVALID_PARAMETER;

        switch (zeroBasedTabIndex) {
        case CONTACTS_TAB_INDEX:
            command = CONTACT_PARAMETER;
            break;
        case FINANCE_TAB_INDEX:
            command = FINANCE_PARAMETER;
            break;
        case EVENTS_TAB_INDEX:
            command = EVENTS_PARAMETER;
            break;
        default:
            command = INVALID_PARAMETER;
        }

        return command;
    }
    /**
     * Checks if parameter is valid
     */
    public static boolean isValidTabParameter(String parameter) {
        return Objects.equals(parameter, CONTACT_PARAMETER)
                || Objects.equals(parameter, EVENTS_PARAMETER)
                || Objects.equals(parameter, FINANCE_PARAMETER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tab)) {
            return false;
        }

        Tab otherTab = (Tab) other;
        return zeroBasedTabIndex == otherTab.zeroBasedTabIndex;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tab", getParameterFromIndex(zeroBasedTabIndex)).toString();
    }
}
