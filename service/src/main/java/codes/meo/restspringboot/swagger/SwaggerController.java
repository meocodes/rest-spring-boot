package codes.meo.restspringboot.swagger;

import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link SwaggerApi} implementation.
 */
@Component
public class SwaggerController implements SwaggerApi {

    @Override
    public String getSwaggerJson(String fileName) {
        try {
            Path path = Paths.get(getClass().getResource(String.format("/%s.json", fileName)).toURI());
            return new String(Files.readAllBytes(path));
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}