package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/24/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoBookReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(AbstractDisplayFileServlet.KEY_OUTPUT, "D:\\Downloads\\");
        req.getSession().setAttribute(AbstractDisplayFileServlet.KEY_FILENAME, "Hibernate In Action.pdf");
    }
}
