package org.kiwi.domain;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("users")
public class UsersResource {
    @Inject
    private UsersRepository usersRepository;

    @Inject
    private OrdersMapper ordersMapper;

    @Path("{userId}/orders")
    public OrdersResource getOrderResource(@PathParam("userId") int userId) {
        final User user = usersRepository.getUserById(userId);
        return new OrdersResource(user, ordersMapper);
    }
}
