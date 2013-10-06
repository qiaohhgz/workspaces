package ibatis;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Unit test for simple BlogDao.
 */
public class BlogDaoTest {
//    CREATE TABLE [blog](
//            [id] [int] IDENTITY(1,1) NOT NULL,
//    [subject] [nvarchar](50) NULL,
//            [content] [nvarchar](50) NULL,
//            [createOn] [date] NULL
//    )
//    go
    public static final int MYSQL = 0;
    public static final int SQL_SERVER = 1;
    int useDs = MYSQL;

    SqlSessionFactory factory;
    SqlSession session;
    BlogDao blogDao;

    private void initMySqlDataSource(BasicDataSource ds) {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        ds.setUsername("root");
        ds.setPassword("root");
    }

    private void initSqlServerDataSource(BasicDataSource ds) {
        ds.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
        ds.setUrl("jdbc:jtds:sqlserver://127.0.0.1:1433;databaseName=test");
        ds.setUsername("sa");
        ds.setPassword("D7#hyron");
    }

    @Before
    public void setUp() {
        if (factory == null) {
            BasicDataSource ds = new BasicDataSource();
            switch (useDs) {
                case MYSQL:
                    initMySqlDataSource(ds);
                    break;
                case SQL_SERVER:
                    initSqlServerDataSource(ds);
                    break;
                default:
                    return;
            }
            ds.setMaxActive(20);
            ds.setMaxIdle(1);
            ds.setMaxWait(-1);
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, ds);
            Configuration cfg = new Configuration(environment);
            cfg.addMappers("ibatis");

            factory = new SqlSessionFactoryBuilder().build(cfg);
        }
        session = factory.openSession();
        System.out.println("session = " + session);
        blogDao = session.getMapper(BlogDao.class);
        System.out.println("open session");
    }

    @After
    public void after() {
        session.commit();
        System.out.println("commit session");
    }

    @Test
    public void testInsert() {
        blogDao.save("标题11", "内容11", new Date());
        blogDao.saveBlog(new Blog("标题A", "内容A", new Date()));
        blogDao.saveBlogBean(new BlogBean("标题C", "内容C"));
    }

    @Test
    public void testGetBlogById() {
        Blog blog = blogDao.getBlogById(5);
        System.out.println(blog);
    }

    @Test
    public void testList() {
        List<Blog> blogList = blogDao.getBlogList();
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
    }

    @Test
    public void testBeanList() {
        List<BlogBean> blogBeanList = blogDao.getBlogBeanList();
        for (BlogBean blogBean : blogBeanList) {
            System.out.println(blogBean);
        }
    }

    @Test
    public void testUpdate() {
        blogDao.update(new BlogBean("新标题", "新内容"), 5);
    }

    @Test
    public void testDelete() throws Exception {
        blogDao.delete(5);
    }
}
