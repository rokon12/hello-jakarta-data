package org.eclipse.jakarta.hello;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/coffees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeResource {
    @Inject
    private CoffeeRepository coffeeRepository;

    @POST
    public Response addCoffee(Coffee coffee) {
        coffeeRepository.add(coffee);
        return Response.status(Response.Status.CREATED).entity(coffee).build();
    }

    @GET
    public Response getAllCoffees() {
        List<Coffee> coffees = coffeeRepository.findAll();
        return Response.ok(coffees).build();
    }

    @GET
    @Path("/{id}")
    public Response getCoffeeById(@PathParam("id") Long id) {
        Coffee coffee = coffeeRepository.findById(id);
        if (coffee != null) {
            return Response.ok(coffee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/filter")
    public Response getCoffeesByTypeAndMinRating(@QueryParam("type") CoffeeType type, @QueryParam("minRating") double minRating) {
        List<Coffee> coffees = coffeeRepository.findByTypeAndMinRating(type, minRating);
        return Response.ok(coffees).build();
    }

    @GET
    @Path("/affordable")
    public Response getAffordableCoffees(@QueryParam("maxPrice") double maxPrice, @QueryParam("minRating") Double minRating) {
        List<Coffee> coffees = coffeeRepository.findAffordableCoffees(maxPrice, minRating);
        return Response.ok(coffees).build();
    }

    @PUT
    @Path("/bulk-update-price")
    public Response bulkUpdatePrice(@QueryParam("type") CoffeeType type, @QueryParam("newPrice") double newPrice) {
        coffeeRepository.updatePriceByType(type, newPrice);
        return Response.ok("Prices updated successfully.").build();
    }

    @DELETE
    @Path("/low-rated")
    public Response removeLowRatedCoffees(@QueryParam("minRating") double minRating) {
        coffeeRepository.deleteLowRatedCoffees(minRating);
        return Response.ok("Low-rated coffees removed successfully.").build();
    }
}
