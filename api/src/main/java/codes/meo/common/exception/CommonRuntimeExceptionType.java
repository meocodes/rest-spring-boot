package codes.meo.common.exception;

public class CommonRuntimeExceptionType implements CommonExceptionType {

    private final String code;
    private final String message;

    public CommonRuntimeExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
