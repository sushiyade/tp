package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class DateTimeParserTest {

    @Test
    public void parseDateTimeInstance_validInput_returnsLocalDateTime() throws ParseException {
        // Test valid date-time numbered inputs
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("2023-1-1 12:00"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1-1-2023 12.00"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1/1/2023 1200"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1 Jan 23 12pm"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("Jan 1 2023 12:00pm"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("Jan 1 23 12:00 pm"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1)
                                .withYear(LocalDate.now().getYear()), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1 Jan 12 pm"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1)
                        .withYear(LocalDate.now().getYear()), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1/1 12.00pm"));
        assertEquals(LocalDateTime.of(LocalDate.of(2023, 1, 1)
                        .withYear(LocalDate.now().getYear()), LocalTime.of(12, 0)),
                DateTimeParser.parseDateTimeInstance("1-1 12.00 pm"));

        assertNotNull(DateTimeParser.parseDateTimeInstance("now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("noon"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("midnight"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("today"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("tdy"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("tomorrow"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("tmr"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("yesterday"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("ytd"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next day"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next week"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next month"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next year"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next mon"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next Tuesday"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next Wed"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next wednesday"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("next Sun"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 min"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 3 mins"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 minute"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 5 minutes"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 hr"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 hour"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 2 hrs"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 2 hours"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 day"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 5 days"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 week"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 5 weeks"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 month"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 5 months"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 1 year"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("in 3 years"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 min from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("3 mins from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 minute from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("5 minutes from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 hr from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 hour from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("2 hrs from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("2 hours from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 day from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("5 days from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 week from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("5 weeks from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 month from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("5 months from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("1 year from now"));
        assertNotNull(DateTimeParser.parseDateTimeInstance("3 years from now"));
    }


    @Test
    public void parseDateTimeInstance_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("invalid"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("2023-10-26 12:00 invalid"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("in -40 mins"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("-40 mins from now"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("next chicken"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("5 centuries from now"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("5 centuries for now"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeInstance("a b c d e f g"));
    }

    @Test
    public void parseDateTimeDuration_validInputDtToDt_returnsLocalDateTimeArray() throws ParseException {
        LocalDateTime[] result = DateTimeParser.parseDateTimeDuration("2023-10-26 12:00", "2023-10-26 14:00");
        assertEquals(LocalDateTime.of(2023, 10, 26, 12, 0), result[0]);
        assertEquals(LocalDateTime.of(2023, 10, 26, 14, 00), result[1]);
    }

    @Test
    public void parseDateTimeDuration_validInputDToD_returnsLocalDateTimeArray() throws ParseException {
        LocalDateTime[] result = DateTimeParser.parseDateTimeDuration("26 Oct 23", "27 Oct");
        assertEquals(LocalDateTime.of(2023, 10, 26, 0, 0), result[0]);
        assertEquals(LocalDateTime.of(2023, 10, 27, 23, 59), result[1]);
    }

    @Test
    public void parseDateTimeDuration_validInputTtoT_returnsLocalDateTimeArray() throws ParseException {
        LocalDateTime[] result = DateTimeParser.parseDateTimeDuration("5pm", "6 pm");
        assertEquals(17, result[0].getHour());
        assertEquals(0, result[0].getMinute());
        assertEquals(18, result[1].getHour());
        assertEquals(0, result[1].getMinute());
    }

    @Test
    public void parseDateTimeDuration_validInputDTtoT_returnsLocalDateTimeArray() throws ParseException {
        LocalDateTime[] result = DateTimeParser.parseDateTimeDuration("26 Oct 23 5pm", "6pm");
        assertEquals(LocalDateTime.of(2023, 10, 26, 17, 0), result[0]);
        assertEquals(LocalDateTime.of(2023, 10, 26, 18, 0), result[1]);
    }

    @Test
    public void parseDateTimeDuration_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeDuration("invalid", "2023-10-26 14:00"));
        assertThrows(ParseException.class, () -> DateTimeParser.parseDateTimeDuration("2023-10-26 12:00", "invalid"));
    }
}
