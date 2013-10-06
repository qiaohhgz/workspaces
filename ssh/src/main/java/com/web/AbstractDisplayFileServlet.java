package com.web;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/24/13
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDisplayFileServlet extends HttpServlet {
    protected static final Logger log = Logger.getLogger(AbstractDisplayFileServlet.class);
    public static final String KEY_OUTPUT = "output";
    public static final String KEY_FILENAME = "fileName";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void generateReport(HttpServletRequest request, HttpServletResponse response, String mimeType) throws ServletException, IOException {
        log.info("----------File to download.---------");
        String path = (String) request.getSession().getAttribute(KEY_OUTPUT);
        String fileName = (String) request.getSession().getAttribute(KEY_FILENAME);
        log.info("path = " + path);
        log.info("fileName = " + fileName);
        log.info("mimeType = " + mimeType);
        if (path == null || fileName == null) {
            String error = "Unable to get information from the session. Please re-login in a new browser window.";
            log.error(error);
            throw new ServletException(error);
        }
        ServletOutputStream stream = null;
        BufferedInputStream buf = null;
        try {
            stream = response.getOutputStream();
            File file = new File(path);
            response.setContentType(mimeType);
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.indexOf("MSIE") > -1) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            //CHANGING TO OPEN PDF AS ATTACHMENT INSTEAD OF DISPLAYING IT ON SCREEN
            //response.addHeader("Content-Disposition", "inline; filename=" + filename);
            response.setContentLength((int) file.length());
            FileInputStream input = new FileInputStream(file);
            buf = new BufferedInputStream(input);
            int readBytes = 0;

            while ((readBytes = buf.read()) != -1)
                stream.write(readBytes);

            log.info("----------File download successful.---------");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ServletException(e.getMessage());
        } finally {
            closeQuietly(buf);
            closeQuietly(stream);
        }
    }

    private void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
                c = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}