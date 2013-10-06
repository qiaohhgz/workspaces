import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 3/14/13
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestNumberFormat {
    private NumberFormat format;

    @Before
    public void setUp(){
        format = NumberFormat.getNumberInstance(Locale.US);
    }

    @Test
    public void test1(){
        format.setMinimumFractionDigits(1);
        format.setMaximumFractionDigits(1);
        String val = format.format(50).toString();
        System.out.println(val);
    }
}
