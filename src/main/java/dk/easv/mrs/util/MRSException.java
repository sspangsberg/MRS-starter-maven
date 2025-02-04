package dk.easv.mrs.util;

public class MRSException extends Exception {

    public MRSException() {
    }

    public MRSException(String message) {
        super(message);
    }

    public MRSException(String message, Throwable cause) {
        super(message, cause);
    }

    public MRSException(Throwable cause) {
        super(cause);
    }

    public MRSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
