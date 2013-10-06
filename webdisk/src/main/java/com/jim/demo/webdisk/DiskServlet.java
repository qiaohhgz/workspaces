package com.jim.demo.webdisk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		File[] roots = File.listRoots();
		String dirTemplate = "<p><a href='dir.shtml?path={0}'>DIR:({0})</a></p>";
		for (File f : roots) {
			String html = MessageFormat.format(dirTemplate, f.getAbsolutePath());
			out.println(html);
		}
		out.flush();
		out.close();
	}
}
