package codes.meo.restspringboot.swagger;

import codes.meo.restspringboot.BaseSystemTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * System tests for {@link SwaggerApi}.
 */
public class SwaggerApiSystemTest extends BaseSystemTest {

    @Test
    public void getSwaggerJson() {
        given()
                .when().get("swagger/{fileName}.json", "swagger")
                .then().statusCode(200)
                .body("swagger", equalTo("2.0"))
                .body("info.title", equalTo("rest-spring-boot-api"))
                .body("info.version", equalTo("1.0.0"))
                .body("basePath", equalTo("/api"))
        ;
    }

    @Test
    public void getSwaggerJsonNotFound() {
        given()
                .when().get("swagger/{fileName}.json", "NOT_FOUND")
                .then().statusCode(404);
    }
}