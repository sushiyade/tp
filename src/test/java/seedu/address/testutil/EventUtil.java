package seedu.address.testutil;

import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.contacts.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Person.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Event event) {
        return AddEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + event.getName().value + " ");
        sb.append(PREFIX_TIME_START + event.getTimeStart().getValue() + " ");
        sb.append(PREFIX_TIME_END + event.getTimeEnd().getValue() + " ");
        event.getClients().stream().forEach(
                s -> sb.append(PREFIX_CLIENT + s.getName().fullName + " ")
        );
        sb.append(PREFIX_LOCATION + event.getLocation().value + " ");
        sb.append(PREFIX_DESCRIPTION + event.getDescription().value + " ");
        return sb.toString();
    }

    /**
     * TO IMPLEMENT!
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getCompany().ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.value).append(" "));
        descriptor.getTelegramName().ifPresent(telegramName -> sb.append(PREFIX_TELEGRAM_NAME)
                .append(telegramName.value).append(" "));
        return sb.toString();
    }
}
