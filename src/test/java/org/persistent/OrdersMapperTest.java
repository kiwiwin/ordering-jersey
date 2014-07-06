package org.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.*;
import org.kiwi.persistent.MybatisConnectionFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OrdersMapperTest {
    private SqlSession sqlSession;
    private UsersRepository usersRepository;
    private OrdersMapper ordersMapper;
    private ProductsRepository productRepository;
    private User user;
    private Product product;
    private Order order;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        usersRepository = sqlSession.getMapper(UsersRepository.class);
        ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        productRepository = sqlSession.getMapper(ProductsRepository.class);

        product = new Product("apple juice", "good", 100);
        productRepository.createProduct(product);


        user = new User("kiwi");
        usersRepository.createUser(user);

        order = new Order(product);
        ordersMapper.createOrder(user, order);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
    }

    @Test
    public void should_get_order() {
        final Order orderById = ordersMapper.getOrder(order.getId());

        assertThat(orderById.getProduct().getName(), is("apple juice"));
    }
}
