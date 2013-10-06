import com.jim.designpattern.singleton.MySingleton;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 8/19/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestMain {
    @Test
    public void testSpringBeanFactoryMethod() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        MySingleton mySingleton = (MySingleton) ctx.getBean("mySingleton");
        assertNotNull(mySingleton);
    }
}
