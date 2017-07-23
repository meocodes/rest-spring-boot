package codes.meo.restspringboot.store;

import codes.meo.common.api.exception.ApiExceptionType;

import javax.ws.rs.core.Response.Status;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public enum StoreExceptionType implements ApiExceptionType {

    INVALID_ORDER("113", "Please provide a valid order.", BAD_REQUEST),
    INVALID_ORDER_ID("121", "Please provide a valid order ID.", NOT_FOUND),
    ORDER_NOT_FOUND("132", "The order ''{0}'' could not be found.", NOT_FOUND);

    StoreExceptionType(String code, String message) {
        this(code, message, Status.INTERNAL_SERVER_ERROR);
    }

    StoreExceptionType(String code, String message, Status status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private final String code;
    private final String message;
    private final Status status;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}