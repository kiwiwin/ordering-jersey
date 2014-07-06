package org.kiwi.domain;

import org.kiwi.json.OrderRefJson;
import org.kiwi.json.PaymentRefJson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class OrdersResource {
    private final User user;
    private final OrdersMapper ordersMapper;
    private final ProductsRepository productsRepository;
    private final PaymentMapper paymentMapper;

    public OrdersResource(User user, OrdersMapper ordersMapper, ProductsRepository productsRepository, PaymentMapper paymentMapper) {
        this.user = user;
        this.ordersMapper = ordersMapper;
        this.productsRepository = productsRepository;
        this.paymentMapper = paymentMapper;
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
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentRefJson getOrderPayment(@PathParam("orderId") int orderId) {
        final Order order = ordersMapper.getOrder(user, orderId);
        return new PaymentRefJson(paymentMapper.getPayment(order));
    }

}
