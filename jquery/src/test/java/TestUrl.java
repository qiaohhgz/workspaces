import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 5/31/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestUrl {
    @Test
    public void testUrl() throws Exception {
        System.getProperties().put("http.proxySet", "true");
        System.getProperties().put("http.proxyHost", "172.20.230.5");
        System.getProperties().put("http.proxyPort", "3128");
        System.getProperties().put("https.proxySet", "true");
        System.getProperties().put("https.proxyHost", "172.20.230.5");
        System.getProperties().put("https.proxyPort", "3128");

        final String href = "http://chart.apis.google.com/chart?chs=330x200&amp;cht=bvg&amp;chco=F6BD0F&amp;chxr=1,0,8&amp;chxl=0:|05.23|05.24|05.25|05.26|05.27|05.28|05.29|&amp;chds=0,8&amp;chm=N*,000000,0,-1,11&amp;chbh=35&amp;chd=t:4,7,6,3,7,8,8&amp;chxt=x,y&amp;";
        URLConnection conn = new URL(href).openConnection();
        InputStream is = conn.getInputStream();
        assert is != null;
    }



}
