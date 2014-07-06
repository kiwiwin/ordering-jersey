package org.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.Product;
import org.kiwi.domain.ProductsRepository;
import org.kiwi.persistent.MybatisConnectionFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsRepositoryTest {
    private SqlSession sqlSession;
    private ProductsRepository productRepository;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        productRepository = sqlSession.getMapper(ProductsRepository.class);
    }

    @Test
    public void should_get_product() {
        final Product product = new Product("apple juice", "good", 100);
        productRepository.createProduct(product);

        final Product productById = productRepository.getProductById(product.getId());

        assertThat(productById.getName(), is("apple juice"));
        assertThat(productById.getDescription(), is("good"));
        assertThat(productById.getPrice(), is(100));
    }
}
