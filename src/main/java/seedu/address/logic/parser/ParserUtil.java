package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.ClientName;
import seedu.address.model.finance.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!EventName.isValidEventName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String company} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String telegramName} into an {@code TelegramName}.
     * @throws ParseException if the given {@code telegramName} is invalid.
     */
    public static TelegramName parseTelegramName(String telegramName) throws ParseException {
        String trimmedTelegramName = telegramName.trim();
        if (!TelegramName.isValidTelegramName(trimmedTelegramName)) {
            throw new ParseException(TelegramName.MESSAGE_CONSTRAINTS);
        }
        return new TelegramName(trimmedTelegramName);
    }

    /**
     * Parses a {@code String eventName} into an {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidEventName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }

    /**
     * Parses a {@code String timeStart} into a {@code TimeStart}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeStart} is invalid.
     */
    public static TimeStart parseTimeStart(String timeStart) throws ParseException {
        String trimmedTimeStart = timeStart.trim();
        if (!TimeStart.isValidTime(trimmedTimeStart)) {
            throw new ParseException(TimeStart.MESSAGE_CONSTRAINTS);
        }
        LocalDateTime dateTime = LocalDateTime.parse(trimmedTimeStart, TimeStart.DATE_TIME_FORMATTER);
        return new TimeStart(dateTime);
    }

    /**
     * Parses a {@code String timeEnd} into a {@code TimeEnd}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeEnd} is invalid.
     */
    public static TimeEnd parseTimeEnd(String timeEnd) throws ParseException {
        String trimmedTimeEnd = timeEnd.trim();
        if (!TimeEnd.isValidTime(trimmedTimeEnd)) {
            throw new ParseException(TimeEnd.MESSAGE_CONSTRAINTS);
        }
        LocalDateTime dateTime = LocalDateTime.parse(trimmedTimeEnd, TimeEnd.DATE_TIME_FORMATTER);
        return new TimeEnd(dateTime);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        String trimmedLocation = location.trim();
        if (!EventName.isValidEventName(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static EventDescription parseEventDescription(String description) throws ParseException {
        String trimmedDescription = description.trim();
        if (!EventName.isValidEventName(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new EventDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        String trimmedDescription = description.trim();
        if (!EventName.isValidEventName(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a collection of client names into a set of {@code Person} objects.
     *
     * @throws ParseException if a client name is invalid.
     */
    public static Set<Person> parseClients(Collection<String> clients) throws ParseException {
        requireNonNull(clients);
        final Set<Person> clientSet = new HashSet<>();
        for (String clientName : clients) {
            clientSet.add(parseClient(clientName));
        }
        return clientSet;
    }

    /**
     * Parses a {@code String clientName} into a {@code Person} object.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clientName} is invalid.
     */
    private static Person parseClient(String clientName) throws ParseException {
        requireNonNull(clientName);
        String trimmedClientName = clientName.trim();
        if (!Name.isValidName(clientName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
    }

    /**
     * Parses a {@code String amount} into an {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(trimmedAmount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount("$" + trimmedAmount);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ClientName parseClientName(String clientName) throws ParseException {
        requireNonNull(clientName);
        String trimmedName = clientName.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(ClientName.MESSAGE_CONSTRAINTS);
        }
        return new ClientName(trimmedName);
    }
}
