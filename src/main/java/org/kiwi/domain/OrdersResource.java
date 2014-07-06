package org.kiwi.domain;

import org.kiwi.json.OrderRefJson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class OrdersResource {
    private final User user;
    private final OrdersMapper ordersMapper;
    private final ProductsRepository productsRepository;

    public OrdersResource(User user, OrdersMapper ordersMapper, ProductsRepository productsRepository) {
        this.user = user;
        this.ordersMapper = ordersMapper;
        this.productsRepository = productsRepository;
    }

    @GET
    @Path("{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderRefJson getOrder(@PathParam("orderId") int orderId, @Context UriInfo uriInfo) {
        return new OrderRefJson(user, ordersMapper.getOrder(user, orderId), uriInfo);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@FormParam("productId") int productId, @Context UriInfo uriInfo) {
        final Order order = new Order(productsRepository.getProductById(productId));
        ordersMapper.createOrder(order);
        return Response.status(201).header("location", new OrderRefJson(user, order, uriInfo).getUri()).build();
    }

    @GET
    @Path("{orderId}/payment")
    public String getOrderPayment(@PathParam("orderId") int orderId) {
        return "";
    }

}
