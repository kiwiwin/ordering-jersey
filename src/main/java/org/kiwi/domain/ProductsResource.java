package org.kiwi.domain;

import org.glassfish.jersey.server.Uri;
import org.kiwi.json.ProductRefJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductsResource {
    @Inject
    private ProductsRepository productsRepository;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRefJson getProductById(@PathParam("id") int productId, @Context UriInfo uriInfo) {
        final Product product = productsRepository.getProductById(productId);
        return new ProductRefJson(product, uriInfo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductRefJson> getProducts(@Context UriInfo uriInfo) {
        final List<Product> products = productsRepository.getProducts();
        return products.stream()
                .map(product -> new ProductRefJson(product, uriInfo))
                .collect(Collectors.toList());
    }
}
