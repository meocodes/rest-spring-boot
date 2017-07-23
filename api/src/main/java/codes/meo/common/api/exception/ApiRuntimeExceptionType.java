package codes.meo.common.api.exception;

import codes.meo.common.exception.CommonRuntimeExceptionType;

import javax.ws.rs.core.Response.Status;

public class ApiRuntimeExceptionType extends CommonRuntimeExceptionType implements ApiExceptionType {

    private final Status status;

    public ApiRuntimeExceptionType(String code, String message) {
        super(code, message);
        this.status = Status.INTERNAL_SERVER_ERROR;
    }

    public ApiRuntimeExceptionType(String code, String message, Status status) {
        super(code, message);
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}