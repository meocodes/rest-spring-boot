package codes.meo.common.exception;

public interface CommonExceptionType {

    /**
     * Returns the error code.
     *
     * @return Error code
     */
    String getCode();

    /**
     * Returns the error message.
     *
     * @return Error message
     */
    String getMessage();
}