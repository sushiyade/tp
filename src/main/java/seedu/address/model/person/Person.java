package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address; // Company Address
    private final Company company; // Company Name
    private final TelegramName telegramName;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Person}.
     * @param name A valid name.
     * @param phone A valid phone number.
     * @param email A valid email address.
     * @param address An optional address.
     * @param company An optional company.
     * @param telegramName An optional telegram name.
     */
    public Person(Name name, Phone phone, Email email, Address address, Company company, TelegramName telegramName) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.company = company;
        this.telegramName = telegramName;
    }

    public Person(Name name, Phone phone, Email email, Address address, TelegramName telegramName) {
        this(name, phone, email, address, null, telegramName);
    }

    public Person(Name name, Phone phone, Email email, Company company, TelegramName telegramName) {
        this(name, phone, email, null, company, telegramName);
    }

    public Person(Name name, Phone phone, Email email, Address address, Company company) {
        this(name, phone, email, address, company, null);
    }

    public Person(Name name, Phone phone, Email email, TelegramName telegramName) {
        this(name, phone, email, null, null, telegramName);
    }

    public Person(Name name, Phone phone, Email email, Company company) {
        this(name, phone, email, null, company, null);
    }

    public Person(Name name, Phone phone, Email email, Address address) {
        this(name, phone, email, address, null, null);
    }

    public Person(Name name, Phone phone, Email email) {
        this(name, phone, email, null, null, null);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        if (address == null) {
            return new Address("");
        } else {
            return address;
        }
    }

    public Company getCompany() {
        if (company == null) {
            return new Company("");
        } else {
            return company;
        }
    }

    public TelegramName getTelegramName() {
        if (telegramName == null) {
            return new TelegramName("");
        } else {
            return telegramName;
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, company, telegramName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("company", company)
                .add("telegramName", telegramName)
                .toString();
    }

}
