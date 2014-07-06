package org.kiwi.json;

import org.kiwi.domain.Order;
import org.kiwi.domain.User;

import javax.ws.rs.core.UriInfo;

public class OrderRefJson {
    private final User user;
    private final Order order;
    private final UriInfo uriInfo;

    public OrderRefJson(User user, Order order, UriInfo uriInfo) {
        this.user = user;
        this.order = order;
        this.uriInfo = uriInfo;
    }

    public int getId() {
        return order.getId();
    }

    public int getPrice() {
        return order.getProduct().getPrice();
    }

    public String getUri() {
        return uriInfo.getBaseUri() + "users/" + user.getId() + "/orders/" + order.getId();
    }

    public ProductRefJson getProduct() {
        return new ProductRefJson(order.getProduct(), uriInfo);
    }
}
