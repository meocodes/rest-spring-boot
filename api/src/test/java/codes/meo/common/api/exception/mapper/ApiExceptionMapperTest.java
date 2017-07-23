package codes.meo.common.api.exception.mapper;

import codes.meo.common.api.exception.ApiError;
import codes.meo.common.api.exception.ApiErrorResponse;
import codes.meo.common.api.exception.ApiException;
import codes.meo.common.api.exception.ApiRuntimeExceptionType;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ApiExceptionMapper}.
 */
public class ApiExceptionMapperTest {

    ApiExceptionMapper mapper;

    @Before
    public void beforeEach() {
        mapper = new ApiExceptionMapper();
    }

    @Test
    public void toResponse() {
        ApiException e = new ApiException(new ApiRuntimeExceptionType("1", "A"));
        Response response = mapper.toResponse(e);
        assertThat(response.getStatus()).isEqualTo(500);
        Object entity = response.getEntity();
        assertThat(entity).isInstanceOf(ApiErrorResponse.class);
        List<ApiError> errors = ((ApiErrorResponse) entity).getErrors();
        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getCode()).isEqualTo("1");
        assertThat(errors.get(0).getMessage()).isEqualTo("A");
    }
}