package org.kiwi.json;

import org.kiwi.domain.Product;

import javax.ws.rs.core.UriInfo;

public class ProductRefJson {
    private final Product product;
    private final UriInfo uriInfo;

    public ProductRefJson(Product product, UriInfo uriInfo) {
        this.product = product;
        this.uriInfo = uriInfo;
    }

    public int getId() {
        return product.getId();
    }

    public String getName() {
        return product.getName();
    }

    public String getDescription() {
        return product.getDescription();
    }

    public String getUri() {
        return uriInfo.getBaseUri() + "products/" + product.getId();
    }
}
