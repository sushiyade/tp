package seedu.address.model.event;

import java.util.Objects;

public class Description {

    private final String descriptionText;

    public Description(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getDescriptionText() {
        return descriptionText;
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
        return Objects.equals(descriptionText, description.descriptionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionText);
    }

    @Override
    public String toString() {
        return descriptionText;
    }
}
