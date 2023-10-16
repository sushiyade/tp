package seedu.address.model.event;

import java.util.Objects;

/**
 * Represents a Event's location in the address book.
 */
public class Location {

    public final String value;
    public static final String MESSAGE_CONSTRAINTS = "Location can take any values up to 256 characters";

    public Location(String locationName) {
        this.value = locationName;
    }

    public String getLocationName() {
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
        Location location = (Location) obj;
        return Objects.equals(value, location.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
