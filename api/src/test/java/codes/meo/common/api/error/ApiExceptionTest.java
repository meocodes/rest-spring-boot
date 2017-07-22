package codes.meo.common.api.error;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link ApiException}.
 */
public class ApiExceptionTest {

    @Test
    public void simpleType() {
        ApiExceptionType type = new ApiRuntimeExceptionType("A", "MSG A");
        ApiException exception = new ApiException(type);
        assertThat(exception.getCode()).isEqualTo("A");
        assertThat(exception.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
        assertThat(exception).hasMessage("MSG A");
        assertThat(exception).hasNoCause();
    }

    @Test
    public void simpleTypeWithCause() {
        ApiExceptionType type = new ApiRuntimeExceptionType("A", "MSG A");
        Throwable cause = new IllegalArgumentException("X");
        ApiException exception = new ApiException(type, cause);
        assertThat(exception.getCode()).isEqualTo("A");
        assertThat(exception.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
        assertThat(exception).hasMessage("MSG A");
        assertThat(exception).hasCause(cause);
    }

    @Test
    public void typeWithMessageProperties() {
        ApiExceptionType type = new ApiRuntimeExceptionType("B", "{3}: MSG {0}, ''{1}'', {5}");
        ApiException exception = new ApiException(type, 0, "1", 2, 3L);
        assertThat(exception.getCode()).isEqualTo("B");
        assertThat(exception.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
        assertThat(exception).hasMessage("3: MSG 0, '1', {5}");
        assertThat(exception).hasNoCause();
    }

    @Test
    public void typeWithMessagePropertiesWithCause() {
        ApiExceptionType type = new ApiRuntimeExceptionType("B", "{3}: MSG {0}, ''{1}'', {5}");
        Throwable cause = new IllegalArgumentException("X");
        ApiException exception = new ApiException(type, cause, 0, "1", 2, 3L);
        assertThat(exception.getCode()).isEqualTo("B");
        assertThat(exception.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR);
        assertThat(exception).hasMessage("3: MSG 0, '1', {5}");
        assertThat(exception).hasCause(cause);
    }
}