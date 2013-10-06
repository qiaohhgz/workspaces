package ibatis;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/22/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class BlogBean {
    private String subject;
    private String content;

    @Override
    public String toString() {
        return "BlogBean{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public BlogBean() {
    }

    public BlogBean(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
