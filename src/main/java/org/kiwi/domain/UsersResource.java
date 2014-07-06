package org.kiwi.domain;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("users")
public class UsersResource {
    @Path("{userId}/orders")
    public OrdersResource getOrderResource(@PathParam("userId") int userId) {
        return new OrdersResource(userId);
    }
}
