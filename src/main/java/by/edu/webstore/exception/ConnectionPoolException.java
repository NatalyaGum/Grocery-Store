package by.edu.webstore.exception;

/**
 * {@code ConnectionPoolException} class represent a checked exception from {@link by.edu.webstore.connection}
 *
 * @see Exception
 */
public class ConnectionPoolException extends Throwable {
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}

