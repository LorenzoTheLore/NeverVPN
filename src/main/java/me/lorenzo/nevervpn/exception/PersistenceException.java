package me.lorenzo.nevervpn.exception;

/**
 * Specific exception for persistence related exceptions
 */
public class PersistenceException extends RuntimeException {
    /**
     * Throw an exception with specified message
     *
     * @param message exception's message
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Throw an exception with child throwable
     *
     * @param cause throwable child
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
