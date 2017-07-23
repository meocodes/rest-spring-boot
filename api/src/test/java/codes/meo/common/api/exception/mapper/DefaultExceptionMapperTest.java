package codes.meo.common.api.exception.mapper;

import codes.meo.common.api.exception.ApiError;
import codes.meo.common.api.exception.ApiErrorResponse;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link DefaultExceptionMapper}.
 */
public class DefaultExceptionMapperTest {

    DefaultExceptionMapper mapper;

    @Before
    public void beforeEach() {
        mapper = new DefaultExceptionMapper();
    }

    @Test
    public void toResponseNotFoundException() {
        Exception e = new NotFoundException();
        Response response = mapper.toResponse(e);
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void toResponseIllegalArgumentException() {
        Exception e = new IllegalArgumentException("X");
        Response response = mapper.toResponse(e);
        assertThat(response.getStatus()).isEqualTo(500);
        Object entity = response.getEntity();
        assertThat(entity).isInstanceOf(ApiErrorResponse.class);
        List<ApiError> errors = ((ApiErrorResponse) entity).getErrors();
        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getCode()).isEqualTo("IAE_999");
        assertThat(errors.get(0).getMessage()).isEqualTo(DefaultExceptionMapper.DEFAULT_MSG);
    }
}