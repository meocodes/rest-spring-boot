package codes.meo.common.api.error;

import codes.meo.common.error.CommonExceptionType;

import javax.ws.rs.core.Response.Status;

public interface ApiExceptionType extends CommonExceptionType {

    /**
     * HTTP status to use when an error of this type occurs.
     *
     * @return {@link Status}
     */
    Status getStatus();
}