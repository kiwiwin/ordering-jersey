package org.kiwi.domain;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class OrdersResource {
    private final User user;

    public OrdersResource(User user) {
        this.user = user;
    }

    @GET
    @Path("{orderId}")
    public String getOrder(@PathParam("orderId") int orderId) {
        return "";
    }
}
