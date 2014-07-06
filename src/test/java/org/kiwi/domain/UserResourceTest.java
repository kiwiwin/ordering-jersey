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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.kiwi.domain.UserIWithId.userWithId;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest extends JerseyTest {
    @Mock
    private UsersRepository usersRepository;

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi")
                .register(ResourceNotFoundExceptionHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(usersRepository).to(UsersRepository.class);
                    }
                });
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(usersRepository.getUserById(1)).thenReturn(userWithId(1, new User("kiwi")));
        when(usersRepository.getUserById(100)).thenThrow(new ResourceNotFoundException());
    }

    @Test
    public void should_user_order_by_order_id() {
        final Response response = target("users/1/orders/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_get_404_when_user_not_exist() {
        final Response response = target("users/100/orders/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }
}
