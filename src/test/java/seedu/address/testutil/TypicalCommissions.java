package seedu.address.testutil;


import seedu.address.model.finance.Commission;

/**
 * A utility class containing a list of {@code Commission} objects to be used in tests.
 */
public class TypicalCommissions {
    public static final Commission COMMISSION_FROM_ALICE = new CommissionBuilder().withPerson("ALICE")
            .withAmount("80")
            .withDescription("Ninjavan UI payment").build();
    public static final Commission COMMISSION_FROM_BOB = new CommissionBuilder().withPerson("BOB")
            .withAmount("1000")
            .withDescription("Wedding photoshoot").build();
}
