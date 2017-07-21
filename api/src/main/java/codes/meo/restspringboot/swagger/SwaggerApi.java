package codes.meo.restspringboot.swagger;

import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/swagger")
@Api(value = "/swagger", tags = "swagger")
@Produces({"application/json"})
public interface SwaggerApi {

    @GET
    @Path("/{fileName}.json")
    @ApiOperation("Get the Swagger specification")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Swagger specification not found")
    })
    String getSwaggerJson(
            @ApiParam(value = "Name of the Swagger specification file", required = true)
            @PathParam("fileName") String fileName);
}