package codes.meo.common.api.error;

import codes.meo.common.error.CommonException;

import javax.ws.rs.core.Response.Status;

public class ApiException extends CommonException {

    private Status status;

    public ApiException(ApiExceptionType type, Object... messageProperties) {
        super(type, messageProperties);
        this.status = type.getStatus();
    }

    public ApiException(ApiExceptionType type, Throwable cause, Object... messageProperties) {
        super(type, cause, messageProperties);
        this.status = type.getStatus();
    }

    public Status getStatus() {
        return status;
    }
}