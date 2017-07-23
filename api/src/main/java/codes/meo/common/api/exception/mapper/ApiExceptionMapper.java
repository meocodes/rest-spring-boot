package codes.meo.common.api.exception.mapper;

import codes.meo.common.api.exception.ApiErrorResponse;
import codes.meo.common.api.exception.ApiException;
import codes.meo.common.api.exception.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        return Response.status(e.getStatus()).entity(toErrors(e)).build();
    }

    private ApiErrorResponse toErrors(ApiException e) {
        return new ApiErrorResponse().errors(new ApiError().code(e.getCode()).message(e.getMessage()));
    }
}