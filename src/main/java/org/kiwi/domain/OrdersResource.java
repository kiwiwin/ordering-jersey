package org.kiwi.domain;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class OrdersResource {
    private final int userId;

    public OrdersResource(int userId) {
        this.userId = userId;
    }

    @GET
    @Path("{orderId}")
    public String getOrder(@PathParam("orderId") int orderId) {
        return "";
    }
}
