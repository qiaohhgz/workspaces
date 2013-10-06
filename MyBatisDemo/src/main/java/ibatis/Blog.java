package ibatis;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/22/13
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Blog {
    private int id;
    private String subject;
    private Date createOn;
    private String content;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", createOn=" + createOn +
                ", content='" + content + '\'' +
                '}';
    }

    public Blog() {
    }

    public Blog(int id) {
        this.id = id;
    }

    public Blog(String subject) {
        this.subject = subject;
    }

    public Blog(String subject, String content, Date createOn) {
        this.subject = subject;
        this.content = content;
        this.createOn = createOn;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
