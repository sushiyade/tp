package seedu.address.model.event.exceptions;

/**
 * Custom exception class to handle events with a timeStart after timeEnd.
 */
public class TimeStartAfterTimeEndException extends Exception {

    public TimeStartAfterTimeEndException() {
        super("TimeStart cannot be after TimeEnd.");
    }

    public TimeStartAfterTimeEndException(String message) {
        super(message);
    }
}
