package codes.meo.common.error;

import java.text.MessageFormat;

public class CommonException extends RuntimeException {

    private String code;
    private String message;

    public CommonException(CommonExceptionType type, Object... messageProperties) {
        super();
        this.code = type.getCode();
        this.message = MessageFormat.format(type.getMessage(), messageProperties);
    }

    public CommonException(CommonExceptionType type, Throwable cause, Object... messageProperties) {
        super(cause);
        this.code = type.getCode();
        this.message = MessageFormat.format(type.getMessage(), messageProperties);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}