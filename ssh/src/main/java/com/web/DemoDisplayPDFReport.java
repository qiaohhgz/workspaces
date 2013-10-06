package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/24/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoDisplayPDFReport extends AbstractDisplayFileServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateReport(request, response, "application/pdf");
    }
}
