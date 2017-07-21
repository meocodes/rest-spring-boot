package codes.meo.restspringboot.store;

import codes.meo.restspringboot.error.Error;
import io.swagger.annotations.*;

import javax.ws.rs.*;

@Path("/store")
@Api(value = "/store", tags = "store")
@Produces({"application/json"})
public interface StoreApi {

    @GET
    @Path("/orders/{id}")
    @ApiOperation("Find purchase order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 0, message = "One or more errors occurred", response = Error.class, responseContainer = "List")
    })
    Order getOrderById(
            @ApiParam(value = "ID of the order that needs to be fetched", allowableValues = "range[1,10]", required = true)
            @PathParam("id") Long id);

    @POST
    @Path("/orders")
    @ApiOperation("Place an order")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Order"),
            @ApiResponse(code = 0, message = "One or more errors occurred", response = Error.class, responseContainer = "List")
    })
    Order placeOrder(
            @ApiParam(value = "order placed for purchasing", required = true) Order order);

    @DELETE
    @Path("/orders/{id}")
    @ApiOperation("Delete purchase order by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 0, message = "One or more errors occurred", response = Error.class, responseContainer = "List")
    })
    void deleteOrder(
            @ApiParam(value = "ID of the order that needs to be deleted", allowableValues = "range[1,10]", required = true)
            @PathParam("id") Long id);
}