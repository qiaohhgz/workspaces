package com.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/6/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadFileServlet extends HttpServlet {
    protected static final Logger log = Logger.getLogger(UploadFileServlet.class);
    public static final String SN_Upload_File = "UploadFileResult";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.info("----------File to uploader.---------");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            List list = upload.parseRequest(request);
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object o = iterator.next();
                FileItem item = null;
                if (o instanceof FileItem) {
                    item = (FileItem) o;
                    if (!item.isFormField()) {
                        String name = item.getName();
                        log.info("name = " + name);
                        String fieldName = item.getFieldName();
                        log.info("fieldName = " + fieldName);
                        long size = item.getSize();
                        log.info("size = " + size);
                        File file = new File("D:/" + name);
                        item.write(file);
                        request.getSession().setAttribute(SN_Upload_File, Boolean.TRUE);
                        response.getWriter().write(name);
                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object o = request.getSession().getAttribute(SN_Upload_File);
        String result = Boolean.FALSE.toString();
        log.info("result = " + result);
        if (o != null) {
            result = Boolean.TRUE.toString();
        }
        response.getWriter().write(result);
    }
}
