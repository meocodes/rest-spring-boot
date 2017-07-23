package codes.meo.common.api.exception;

import org.junit.Test;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link ApiRuntimeExceptionType}.
 */
public class ApiRuntimeExceptionTypeTest {

    @Test
    public void withCodeAndMessage() {
        ApiRuntimeExceptionType type = new ApiRuntimeExceptionType("1", "A");
        assertThat(type.getCode()).isEqualTo("1");
        assertThat(type.getMessage()).isEqualTo("A");
        assertThat(type.getStatus()).isEqualTo(INTERNAL_SERVER_ERROR);
    }

    @Test
    public void withCodeAndMessageAndStatus() {
        ApiRuntimeExceptionType type = new ApiRuntimeExceptionType("1", "A", BAD_REQUEST);
        assertThat(type.getCode()).isEqualTo("1");
        assertThat(type.getMessage()).isEqualTo("A");
        assertThat(type.getStatus()).isEqualTo(BAD_REQUEST);
    }
}