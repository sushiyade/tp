package seedu.address.model.util;


import java.time.LocalDateTime;

import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.FinancesBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventsBook;
import seedu.address.model.ReadOnlyFinancesBook;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new Company("Google"), new TelegramName("@AlexYeoh")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new Company("FAPro"),
                new TelegramName("@BerniceYu")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address(""),
                    new Company("NUS"),
                    new TelegramName("@charOlive")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 69 Lorong Jalan, #3-69"), new Company("Google"), new TelegramName("@DavidLi")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 69 Lorong Batok, #5-55"), new Company("Google"), new TelegramName("@IrfanIbrahim")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address(""), new Company("Google"), new TelegramName("@balakroiii"))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new EventBuilder().withName("Meeting with Alice")
                    .withTimeStart("23-09-2023 09:00")
                    .withTimeEnd("23-09-2023 10:00")
                    .build(),
            new EventBuilder().withName("Conference with Bob")
                    .withTimeStart("24-09-2023 14:00")
                    .withTimeEnd("24-09-2023 18:00")
                    .build(),
            new EventBuilder().withName("Team Lunch")
                    .withTimeStart("25-09-2023 12:00")
                    .withTimeEnd("25-09-2023 13:00")
                    .build(),
            new EventBuilder().withName("Project Presentation")
                    .withTimeStart("26-09-2023 15:00")
                    .withTimeEnd("26-09-2023 16:30")
                    .build()
        };
    }

    public static Finance[] getSampleFinances() {
        return new Finance[] {
            new Commission(
                    new Amount("300"),
                    new Person(
                            new Name("Bob"),
                            new Phone("00000"),
                            new Email("filler@email.com"),
                            new Address(""),
                            new Company(""),
                            new TelegramName("")
                    ),
                    new Description("Artwork"),
                    new TimeDue(LocalDateTime.now())
            ),
            new Commission(
                    new Amount("100"),
                    new Person(
                            new Name("Alice"),
                            new Phone("00000"),
                            new Email("filler@email.com"),
                            new Address(""),
                            new Company(""),
                            new TelegramName("")
                    ),
                    new Description(""),
                    new TimeDue(LocalDateTime.now())
            ),
            new Expense(
                    new Amount("1200"),
                    new Person(
                            new Name("Adobe"),
                            new Phone("00000"),
                            new Email("filler@email.com"),
                            new Address(""),
                            new Company(""),
                            new TelegramName("")
                    ),
                    new Description("License"),
                    new TimeDue(LocalDateTime.now())
            ),
            new Expense(
                    new Amount("500"),
                    null,
                    new Description("Party"),
                    new TimeDue(LocalDateTime.now())
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEventsBook getSampleEventsBook() {
        EventsBook sampleEb = new EventsBook();
        for (Event sampleEvent : getSampleEvents()) {
            sampleEb.addEvent(sampleEvent);
        }
        return sampleEb;
    }

    public static ReadOnlyFinancesBook getSampleFinancesBook() {
        FinancesBook sampleFb = new FinancesBook();
        for (Finance sampleFinance : getSampleFinances()) {
            sampleFb.addFinance(sampleFinance);
        }
        return sampleFb;
    }
}
