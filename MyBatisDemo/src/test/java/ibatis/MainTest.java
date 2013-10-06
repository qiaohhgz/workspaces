package ibatis;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-23
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class MainTest {
    @Test
    public void testName() throws Exception {
        Blog blog = (Blog) Blog.class.getConstructors()[0].newInstance();
        System.out.println("blog.getClass().getName() = " + blog.getClass().getName());
        blog = (Blog) Class.forName("ibatis.Blog").getConstructors()[0].newInstance();
        System.out.println("blog.getClass().getName() = " + blog.getClass().getName());
        Assert.assertNotNull(blog);
    }
}
