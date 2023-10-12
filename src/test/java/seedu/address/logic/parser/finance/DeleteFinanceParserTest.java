package seedu.address.logic.parser.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;


class DeleteFinanceParserTest {
    private DeleteFinanceParser parser = new DeleteFinanceParser();

    @Test
    public void parse_success() throws ParseException {
        assertEquals(parser.parse("Dummy"), null);
    }

}
