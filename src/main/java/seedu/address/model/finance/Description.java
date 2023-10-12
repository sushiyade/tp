package seedu.address.model.finance;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Description of a Finance.
 */
public class Description {
    private String value;
    /**
     * Constructs a Description object with the specified description value.
     *
     * @param description The description value to be associated with this object.
     */
    public Description(String description) {
        requireNonNull(description);
        this.value = description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return value.equals(otherDescription.value);
    }



}
