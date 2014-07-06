package org.kiwi.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.handler.ResourceNotFoundException;
import org.kiwi.domain.handler.ResourceNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.kiwi.domain.ProductWithId.productWithId;
import static org.kiwi.domain.UserIWithId.userWithId;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest extends JerseyTest {
    @Mock
    private UsersRepository usersRepository;

    @Mock
    private OrdersMapper ordersMapper;

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi")
                .register(ResourceNotFoundExceptionHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(usersRepository).to(UsersRepository.class);
                        bind(ordersMapper).to(OrdersMapper.class);
                    }
                });
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(usersRepository.getUserById(1)).thenReturn(userWithId(1, new User("kiwi")));
        when(usersRepository.getUserById(100)).thenThrow(new ResourceNotFoundException());

        when(ordersMapper.getOrder(any(User.class), eq(1))).thenReturn(OrderWithId.orderWithId(1, new Order(productWithId(1
                , new Product("apple juice", "good", 100)))));
    }

    @Test
    public void should_user_order_by_order_id() {
        final Response response = target("users/1/orders/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final Map order = response.readEntity(Map.class);
        assertThat(order.get("id"), is(1));
        assertThat(order.get("price"), is(100));
        assertThat((String) order.get("uri"), endsWith("/users/1/orders/1"));

        final Map product = (Map) order.get("product");
        assertThat(product.get("name"), is("apple juice"));
    }

    @Test
    public void should_get_404_when_user_not_exist() {
        final Response response = target("users/100/orders/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }
}
