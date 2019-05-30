import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import com.mybatis.entities.User;
import com.mybatis.dao.UserDao;


public class TestMapper {   
    static SqlSessionFactory sqlSessionFactory = null;   
    static {   
       sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();   
    }   
   
    @Test   
    public void testAdd() {   
       SqlSession sqlSession = sqlSessionFactory.openSession();   
       try {   
           UserDao userMapper = sqlSession.getMapper(UserDao.class);
           User user = new User("tom",new Integer(5));
           userMapper.addUser(user);
           sqlSession.commit();
       } 
       finally {   
           sqlSession.close();   
       }   
    }
    @Test
    public void getUser() {   
       SqlSession sqlSession = sqlSessionFactory.openSession();   
       try {   
    	   UserDao userMapper = sqlSession.getMapper(UserDao.class);
           User user = userMapper.getUser("tom");   
           System.out.println("name: "+user.getName()+"|age: "+user.getAge());   
//           System.out.println(userMapper.getUser("tom"));
       } finally {
           sqlSession.close();   
       }   
    }   
   
}  