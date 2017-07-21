package codes.meo.restspringboot.swagger;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

/**
 * Unit tests for {@link SwaggerController}.
 */
public class SwaggerControllerTest {

    SwaggerController controller;

    @Before
    public void beforeEach() {
        controller = new SwaggerController();
    }

    @Test
    public void getSwaggerJson() {
        String actual = controller.getSwaggerJson("swagger");
        assertThat(actual).isEqualTo(contentOf(new File("src/test/resources/swagger.json")));
    }

    @Test(expected = NotFoundException.class)
    public void getSwaggerJsonNotFound() {
        controller.getSwaggerJson("XXX");
    }
}