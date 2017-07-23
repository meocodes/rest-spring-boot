package codes.meo.common.api.exception.mapper;

import codes.meo.common.api.exception.ApiError;
import codes.meo.common.api.exception.ApiErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {

    public static final String DEFAULT_MSG = "An internal error has occurred, please try again later. If problems persist contact customer support.";

    @Override
    public Response toResponse(Exception e) {
        if (e instanceof WebApplicationException) {
            return ((WebApplicationException) e).getResponse();
        } else {
            return Response.status(INTERNAL_SERVER_ERROR).entity(toErrors(e)).build();
        }
    }

    private ApiErrorResponse toErrors(Exception e) {
        return new ApiErrorResponse().errors(new ApiError().code(String.format("%s_999", abbreviate(e))).message(DEFAULT_MSG));
    }

    private <T> String abbreviate(T type) {
        return type.getClass().getSimpleName().replaceAll("[a-z]", "");
    }
}