package codes.meo.common.api.exception;

public class ApiError {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApiError code(String code) {
        setCode(code);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiError message(String message) {
        setMessage(message);
        return this;
    }
}