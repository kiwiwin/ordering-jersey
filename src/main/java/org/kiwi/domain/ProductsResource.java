package org.kiwi.domain;

import org.kiwi.json.ProductRefJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductsRepository productsRepository;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRefJson getProductById(@PathParam("id") int productId) {
        final Product product = productsRepository.getProductById(productId);
        return new ProductRefJson(product);
    }
}