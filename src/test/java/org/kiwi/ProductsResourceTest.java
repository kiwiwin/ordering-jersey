package org.kiwi;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsResourceTest extends JerseyTest {
    @Mock
    private ProductsRepository productsRepository;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(productsRepository.getProductById(eq(100))).thenThrow(new ResourceNotFoundException());
    }


    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages(true, "org.kiwi")
                .register(ResourceNotFoundExceptionHandler.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(productsRepository).to(ProductsRepository.class);
                    }
                });
    }

    @Test
    public void should_get_product_by_id() {
        final Response response = target("/products/1")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void should_get_404_when_product_not_exist() {
        final Response response = target("/products/100")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }
}
