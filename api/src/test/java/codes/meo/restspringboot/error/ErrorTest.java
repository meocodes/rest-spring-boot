package codes.meo.restspringboot.error;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link Error}.
 */
public class ErrorTest {

    Error error;

    @Before
    public void beforeEach() {
        error = new Error();
    }

    @Test
    public void setGetCode() {
        String expected = "ABC";
        error.setCode(expected);
        assertThat(error.getCode()).isEqualTo(expected);
    }

    @Test
    public void setGetMessage() {
        String expected = "ABC";
        error.setMessage(expected);
        assertThat(error.getMessage()).isEqualTo(expected);
    }

    @Test
    public void builderMethods() {
        error.code("CODE").message("MSG");
        assertThat(error.getCode()).isEqualTo("CODE");
        assertThat(error.getMessage()).isEqualTo("MSG");
    }
}