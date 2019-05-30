import com.mybatis.dao.BookDao;
import com.mybatis.entities.Book;
import com.mybatis.service.BookService;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpringLoadTest {
    static ApplicationContext applicationContext;
    static BookService bookservice;
    @BeforeClass
    public static void before(){
        applicationContext = new FileSystemXmlApplicationContext(new String[]{"/resource/SpringMVC-servlet.xml"});
        //applicationContext= new ClassPathXmlApplicationContext(new String[]{"file:E:\Workspace\idea_workspace\spring\springtest\src\main\resources\applicationContext.xml"});
        bookservice = applicationContext.getBean(BookService.class);
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(SpringLoadTest.class);
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(new String[]{"/resource/SpringMVC-servlet.xml"});
        BookDao bookDao = (BookDao) applicationContext.getBean("bookDao");//依据bean的名字获取
        //bookDao=applicationContext.getBean(BookDao.class);//依据类型获取
        logger.info("在测试类中获取数据如下：");
        logger.info(bookDao);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books=bookservice.getAllBooks();
        assertNotNull(books);
    }

    @Test
    public void testAdd() {
        Book entity=new Book(0, "Hibernate 第七版", 78.1, new Date());
        try {
            assertEquals(1, bookservice.add(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteInt() {
        assertEquals(1, bookservice.delete(9));
    }

    @Test
    public void testDeleteStringArray() {
        String[] ids={"7","11","12"};
        assertEquals(3, bookservice.delete(ids));
    }

    @Test
    public void testUpdate() {
        Book entity=new Book(7, "Hibernate 第二版", 79.1, new Date());
        try {
            assertEquals(1, bookservice.update(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBookById()
    {
        assertNotNull(bookservice.getBookById(1));
    }

    @Test
    public void testAddDouble(){
        //因为书名相同，添加第二本会失败，用于测试事务
        Book entity1=new Book(0, "Hibernate 第八版", 78.1, new Date());
        Book entity2=new Book(0, "Hibernate 第八版", 78.1, new Date());
        assertEquals(2, bookservice.add(entity1, entity2));
    }
}
