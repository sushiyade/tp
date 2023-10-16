package seedu.address.logic.parser.contacts;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.events.AddEventCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.*;
import seedu.address.model.event.exceptions.TimeStartAfterTimeEndException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIME_START, PREFIX_TIME_END, PREFIX_CLIENT,
                        PREFIX_LOCATION, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT_NAME, PREFIX_TIME_START, PREFIX_TIME_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TIME_START, PREFIX_TIME_END,
                PREFIX_LOCATION, PREFIX_DESCRIPTION);
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        TimeStart timeStart = ParserUtil.parseTimeStart(argMultimap.getValue(PREFIX_TIME_START).get());
        TimeEnd timeEnd = ParserUtil.parseTimeEnd(argMultimap.getValue(PREFIX_TIME_END).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(""));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).orElse(""));
        Set<Person> clients = ParserUtil.parseClients(argMultimap.getAllValues(PREFIX_CLIENT));

        Event event;
        try {
            event = new Event(eventName, timeStart, timeEnd, clients, location, description);
        } catch (TimeStartAfterTimeEndException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE_TIME));
        }

        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
