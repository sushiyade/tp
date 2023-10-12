package seedu.address.model.event;

import java.util.Objects;

public class Location {

    private final String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
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
        return Objects.equals(locationName, location.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationName);
    }

    @Override
    public String toString() {
        return locationName;
    }
}
