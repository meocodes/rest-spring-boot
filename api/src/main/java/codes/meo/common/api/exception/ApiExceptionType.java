package codes.meo.common.api.exception;

import codes.meo.common.exception.CommonExceptionType;

import javax.ws.rs.core.Response.Status;

public interface ApiExceptionType extends CommonExceptionType {

    /**
     * HTTP status to use when an exception of this type occurs.
     *
     * @return {@link Status}
     */
    Status getStatus();
}