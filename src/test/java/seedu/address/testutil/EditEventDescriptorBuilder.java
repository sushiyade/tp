package seedu.address.testutil;

import seedu.address.logic.commands.events.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setEventName(event.getEventName());
        descriptor.setTimeStart(event.getTimeStart());
        descriptor.setTimeEnd(event.getTimeEnd());
        descriptor.setLocation(event.getLocation());
        descriptor.setClients(event.getClients());
        descriptor.setEventDescription(event.getDescription());
    }

    /**
     * Sets the {@code EventName} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventName(String eventName) {
        descriptor.setEventName(new EventName(eventName));
        return this;
    }

    /**
     * Sets the {@code TimeStart} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withTimeStart(String timeStart) throws ParseException {
        descriptor.setTimeStart(new TimeStart(timeStart));
        return this;
    }

    /**
     * Sets the {@code TimeEnd} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withTimeEnd(String timeEnd) throws ParseException {
        descriptor.setTimeEnd(new TimeEnd(timeEnd));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Parses the {@code clients} into a {@code Set<Person>} and set it to the {@code Event} that we are building.
     */
    public EditEventDescriptorBuilder withClients(String ... clients) {
        descriptor.setClients(SampleDataUtil.getClientSet(clients));
        return this;
    }

    /**
     * Sets the {@code EventDescription} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDescription(String description) {
        descriptor.setEventDescription(new EventDescription(description));
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
