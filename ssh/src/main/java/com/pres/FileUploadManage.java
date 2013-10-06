package com.pres;

import com.web.UploadFileServlet;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/6/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadManage {
    protected static final Logger log = Logger.getLogger(FileUploadManage.class);

    public String uploadFile(String file) {
        System.out.println("uploadFile");
        System.out.println("file = " + file);
        return file;
    }

    public String getUploadFileResult() {
        String result = null;
        try {
            WebContext ctx = WebContextFactory.get();
            HttpSession session = ctx.getSession();
            Object fileStatus = session.getAttribute(UploadFileServlet.SN_Upload_File);
            if (fileStatus != null && fileStatus.toString().equals(Boolean.TRUE.toString())) {
                session.removeAttribute(UploadFileServlet.SN_Upload_File);
                return Boolean.TRUE.toString();
            }
        } catch (Exception e) {
            result = e.getMessage();
            log.error(result);
        }
        return result;
    }
}
