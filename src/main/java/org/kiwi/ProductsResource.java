package org.kiwi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/products")
public class ProductsResource {
    @GET
    @Path("{id}")
    public String getProductById(@PathParam("id") int productId) {
        return "";
    }
}
