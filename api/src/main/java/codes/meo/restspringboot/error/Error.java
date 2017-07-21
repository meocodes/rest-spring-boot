package codes.meo.restspringboot.error;

public class Error {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Error code(String code) {
        setCode(code);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error message(String message) {
        setMessage(message);
        return this;
    }
}