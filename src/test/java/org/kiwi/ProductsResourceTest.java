package org.kiwi;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductsResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi");
    }

    @Test
    public void should_get_product_by_id() {
        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }
}
