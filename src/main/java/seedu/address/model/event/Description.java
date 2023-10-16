package seedu.address.model.event;

import java.util.Objects;

/**
 * Represents a Person's description in the address book.
 */
public class Description {

    public final String value;

    public static final String MESSAGE_CONSTRAINTS = "Location can take any values up to 256 characters";

    public Description(String descriptionText) {
        this.value = descriptionText;
    }

    public String getDescription() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Description description = (Description) obj;
        return Objects.equals(value, description.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
