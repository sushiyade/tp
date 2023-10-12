package seedu.address.model.event;

import java.util.Objects;

public class Description {

    private final String description;

    public Description(String descriptionText) {
        this.description = descriptionText;
    }

    public String getDescription() {
        return description;
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
        return Objects.equals(description, description.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
