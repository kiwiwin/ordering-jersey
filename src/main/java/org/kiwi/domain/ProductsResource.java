package org.kiwi.domain;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductsRepository productsRepository;

    @GET
    @Path("{id}")
    public String getProductById(@PathParam("id") int productId) {
        productsRepository.getProductById(productId);
        return "";
    }
}
