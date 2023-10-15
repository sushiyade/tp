package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Telegram name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TelegramName {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram names should follow Telegram constraints, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*\\B@(?=\\w{5,32}\\b)[a-zA-Z0-9]+(?:_[a-zA-Z0-9]+)*.*";

    public final String telegramName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TelegramName(String name) {
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        telegramName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return telegramName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramName)) {
            return false;
        }

        TelegramName otherName = (TelegramName) other;
        return telegramName.equals(otherName.telegramName);
    }

    @Override
    public int hashCode() {
        return telegramName.hashCode();
    }

}
