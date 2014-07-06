package org.persistent;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kiwi.domain.User;
import org.kiwi.domain.UsersRepository;
import org.kiwi.persistent.MybatisConnectionFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UsersRepositoryTest {
    private SqlSession sqlSession;
    private UsersRepository usersRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        usersRepository= sqlSession.getMapper(UsersRepository.class);

        user = new User("kiwi");
        usersRepository.createUser(user);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();
    }

    @Test
    public void should_get_user_by_id() {
        final User userById = usersRepository.getUserById(user.getId());

        assertThat(userById.getName(), is("kiwi"));
    }
}
