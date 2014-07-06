package org.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductsRepository;
import org.kiwi.persistent.MybatisConnectionFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsRepositoryTest {
    private SqlSession sqlSession;
    private ProductsRepository productRepository;
    private Product product;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        productRepository = sqlSession.getMapper(ProductsRepository.class);

        product = new Product("apple juice", "good", 100);
        productRepository.createProduct(product);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
    }

    @Test
    public void should_get_product() {
        final Product productById = productRepository.getProductById(product.getId());

        assertThat(productById.getName(), is("apple juice"));
        assertThat(productById.getDescription(), is("good"));
        assertThat(productById.getPrice(), is(100));
    }

    @Test
    public void should_get_products() {
        final List<Product> products = productRepository.getProducts();

        assertThat(products.size(), is(1));
        assertThat(products.get(0).getName(), is("apple juice"));
        assertThat(products.get(0).getDescription(), is("good"));
        assertThat(products.get(0).getPrice(), is(100));
    }
}
