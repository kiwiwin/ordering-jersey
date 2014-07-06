package org.kiwi.domain;

import org.kiwi.json.OrderRefJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

public class OrdersResource {
    private final User user;
    private final OrdersMapper ordersMapper;

    public OrdersResource(User user, OrdersMapper ordersMapper) {
        this.user = user;
        this.ordersMapper = ordersMapper;
    }

    @GET
    @Path("{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderRefJson getOrder(@PathParam("orderId") int orderId, @Context UriInfo uriInfo) {
        return new OrderRefJson(user, ordersMapper.getOrder(user, orderId), uriInfo);
    }
}
