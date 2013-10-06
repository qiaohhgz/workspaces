package com.web;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/19/13
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class SourceServlet extends javax.servlet.http.HttpServlet {
    protected static final Logger log = Logger.getLogger(SourceServlet.class);

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String type = request.getParameter("type");
        type = type == null ? "" : type.toLowerCase();
        log.info("type = " + type);

        log.info("request.getParameter(\"r\") = " + request.getParameter("r"));
        String pathname = "C:\\Users\\user\\Desktop\\music\\mydeskmate.mp3";
        if (type.equals("json")) {
            Gson g = new Gson();
            log.info("images");
            String contextPath = request.getContextPath();
            log.info("contextPath = " + contextPath);
            File[] images = new File("images").listFiles();
            for (File image : images) {
                log.info("image.getPath() = " + image.getPath());
            }
        } else if (type.equals("media")) {
            write(response, pathname);
        } else if (type.equals("video")) {
            pathname = "E:\\Video\\ps\\test2.mp4";
            write(response, pathname);
        }
    }

    private void write(HttpServletResponse response, String pathname) throws IOException {
        log.info("open");
        log.info("pathname = " + pathname);
        RandomAccessFile is = new RandomAccessFile(pathname, "r");
        byte[] bytes = new byte[1024];
        ServletOutputStream out = response.getOutputStream();
        long fileSize = is.length();
        log.info("fileSize = " + fileSize);
        long total = 0;
        for (int len = is.read(bytes); len != -1; len = is.read(bytes)) {
            total += len;
            out.write(bytes, 0, len);
        }
        is.close();
        out.close();
        log.info("close");
    }
}
