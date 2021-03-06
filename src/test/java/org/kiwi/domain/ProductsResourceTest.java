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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.kiwi.domain.ProductWithId.productWithId;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductsResourceTest extends JerseyTest {
    @Mock
    private ProductsRepository productsRepository;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        when(productsRepository.getProductById(eq(1))).thenReturn(productWithId(1, new Product("apple juice", "good", 100)));
        when(productsRepository.getProductById(eq(100))).thenThrow(new ResourceNotFoundException());
        when(productsRepository.getProducts()).thenReturn(Arrays.asList(productWithId(1, new Product("apple juice", "good", 100)), productWithId(2, new Product("banana juice", "just so so", 100))));
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

        assertThat(product.get("id"), is(1));
        assertThat(product.get("name"), is("apple juice"));
        assertThat(product.get("description"), is("good"));
        assertThat(product.get("price"), is(100));
        assertThat((String) product.get("uri"), endsWith("/products/1"));
    }

    @Test
    public void should_get_404_when_product_not_exist() {
        final Response response = target("/products/100")
                .request()
                .get();

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_get_all_products() {
        final Response response = target("products")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        final List products = response.readEntity(List.class);

        assertThat(products.size(), is(2));

        final Map product = (Map) products.get(0);
        assertThat(product.get("id"), is(1));
        assertThat(product.get("name"), is("apple juice"));
        assertThat(product.get("description"), is("good"));
        assertThat((String) product.get("uri"), endsWith("/products/1"));
    }
}
