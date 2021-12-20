package by.example.webstore.exception;

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

