package eu.alfred.api.sensors.exceptions;

/**
 * Created by gilbe on 21.09.2015.
 */
public class SAFException extends Exception {
    public SAFException() {
    }

    public SAFException(String message) {
        super(message);
    }

    public SAFException(Throwable cause) {
        super(cause);
    }

    public SAFException(String message, Throwable cause) {
        super(message, cause);
    }

}
