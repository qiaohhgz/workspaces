import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.jim.account.email.AccountEmailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/20/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAccountEmailService {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.start();
    }

    public boolean isCanUsePort(){
        try {
            new ServerSocket(25);
            System.out.println("Port 25 can be used.");
            return true;
        } catch (IOException e) {
            System.out.println("Port 25 is bind.");
            return false;
        }
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");

        String subject = "Test Subject";
        String htmlText = "<h3>Hello Maven</h3>";
//        accountEmailService.sendMail("qiaoshiju@hyron.com", subject, htmlText);

//        greenMail.waitForIncomingEmail(1000, 1);
//        isCanUsePort();
//        MimeMessage[] messages = greenMail.getReceivedMessages();
//        System.out.println(messages.length);
//        assert 1 == messages.length;
//        assert subject.equals(messages[0].getSubject());
//        assert htmlText.equals(GreenMailUtil.getBody(messages[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();
    }
}
