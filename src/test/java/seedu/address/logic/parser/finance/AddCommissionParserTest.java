package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CLIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.DEFAULT_TIME_DUE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.finance.AddCommissionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.Commission;
import seedu.address.testutil.CommissionBuilder;


class AddCommissionParserTest {
    private final AddCommissionParser parser = new AddCommissionParser();

    @Test
    public void parse_success() throws ParseException {
        Commission expectedCommission = new CommissionBuilder().build();

        //whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DEFAULT_AMOUNT + CLIENT_DESC_AMY
                        + DEFAULT_DESCRIPTION + DEFAULT_TIME_DUE,
                new AddCommissionCommand(expectedCommission));
    }
    @Test
    public void parse_failure() throws ParseException {
        Commission expectedCommission = new CommissionBuilder().build();

        String validExcpectedCommissionString = DEFAULT_AMOUNT + CLIENT_DESC_AMY + DEFAULT_DESCRIPTION;
        // multiple clients
        assertParseFailure(parser, CLIENT_DESC_BOB + validExcpectedCommissionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CLIENT));
        // multiple amounts
        assertParseFailure(parser, DEFAULT_AMOUNT + validExcpectedCommissionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));
        // multiple descriptions
        assertParseFailure(parser, DEFAULT_DESCRIPTION + validExcpectedCommissionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }
    @Test
    public void parse_optionalFieldsMissing_success() {
        // no description
        Commission expectedCommission = new CommissionBuilder().withDescription("").build();
        assertParseSuccess(parser, DEFAULT_AMOUNT + CLIENT_DESC_AMY + DEFAULT_TIME_DUE,
                new AddCommissionCommand(expectedCommission));
    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommissionCommand.MESSAGE_USAGE);

        // missing amount prefix
        assertParseFailure(parser, VALID_AMOUNT + CLIENT_DESC_AMY + DEFAULT_DESCRIPTION,
                expectedMessage);
    }
}
