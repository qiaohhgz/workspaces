package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.AppointmentManager;
import com.db.dao.BaseDao;
import com.util.OutputExcel;

public class AppointmentServlet extends HttpServlet {

	private static final long serialVersionUID = 6421879152995468972L;
	private AppointmentManager manager = new AppointmentManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test");
		String type = req.getParameter("t");
		if (type != null) {
			if (type.equalsIgnoreCase("excel")) {
				new OutputExcel(resp.getOutputStream(), BaseDao.getDB().getAppointments());
			} else if (type.equalsIgnoreCase("image")) {
				OutputStream os = resp.getOutputStream();
				InputStream is = new FileInputStream(new File("C:/Users/JimQiao/Desktop/lbjn.jpg"));
				ImageIO.write(ImageIO.read(is), "jpg", os);
			}
		} else {
			System.out.println("no result");
			resp.getWriter().write("no result");
		}
	}
}
