package fileuploader.exception;

public class FileSizeExceededException extends RuntimeException {
    public FileSizeExceededException() {
        super();
    }

    public FileSizeExceededException(String message) {
        super(message);
    }

    public FileSizeExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeExceededException(Throwable cause) {
        super(cause);
    }

    protected FileSizeExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
