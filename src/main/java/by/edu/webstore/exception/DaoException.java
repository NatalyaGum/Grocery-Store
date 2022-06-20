package by.edu.webstore.exception;

/**
 * {@code DaoException} class represent a checked exception from {@link by.edu.webstore.dao}
 *
 * @see Exception
 */
public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
