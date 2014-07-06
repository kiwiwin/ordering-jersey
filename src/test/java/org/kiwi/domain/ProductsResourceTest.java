package org.kiwi.domain;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductWithId;
import org.kiwi.domain.ProductsRepository;
import org.kiwi.domain.handler.ResourceNotFoundException;
import org.kiwi.domain.handler.ResourceNotFoundExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
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
        when(productsRepository.getProductById(eq(1))).thenReturn(ProductWithId.productWithId(1, new Product("apple juice", "good")));
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

        final Map product = response.readEntity(Map.class);

        assertThat((Integer) product.get("id"), is(1));
        assertThat((String) product.get("name"), is("apple juice"));
        assertThat((String) product.get("description"), is("good"));
        assertThat((String) product.get("uri"), endsWith("/products/1"));
    }

    @Test
    public void should_get_404_when_product_not_exist() {
        final Response response = target("/products/100")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }
}