package org.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.*;
import org.kiwi.persistent.MybatisConnectionFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PaymentMapperTest {
    private SqlSession sqlSession;
    private UsersRepository usersRepository;
    private OrdersMapper ordersMapper;
    private ProductsRepository productRepository;
    private User user;
    private Product product;
    private Order order;
    private PaymentMapper paymentMapper;
    private Payment oayment;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        usersRepository = sqlSession.getMapper(UsersRepository.class);
        ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        productRepository = sqlSession.getMapper(ProductsRepository.class);
        paymentMapper = sqlSession.getMapper(PaymentMapper.class);

        product = new Product("apple juice", "good", 100);
        productRepository.createProduct(product);


        user = new User("kiwi");
        usersRepository.createUser(user);

        order = new Order(product);
        ordersMapper.createOrder(user, order);

        oayment = new Payment("cash", 100);
        paymentMapper.createPayment(order, oayment);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
    }

    @Test
    public void should_get_payment() {
        final Payment paymentById = paymentMapper.getPayment(order);

        assertThat(paymentById.getType(), is("cash"));
        assertThat(paymentById.getAmount(), is(100));
    }
}
