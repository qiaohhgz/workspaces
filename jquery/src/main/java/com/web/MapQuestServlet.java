package com.web;

import com.google.gson.Gson;
import com.jim.util.MapQuestCode;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/3/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapQuestServlet extends HttpServlet {
    protected static final Logger log = Logger.getLogger(MapQuestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("do get");
        String location = req.getParameter("l");
        if (location == null) {
            return;
        }
        Gson g = new Gson();
        MapQuestCode mapQuestCode = new MapQuestCode(location);
        String json = g.toJson(mapQuestCode);
        log.info(json);
        resp.getWriter().write(json);
    }
}
