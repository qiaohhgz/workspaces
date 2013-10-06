package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.biz.StudentBiz;
import com.entity.Student;

public class StudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String t = request.getParameter("t");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentBiz biz = ctx.getBean("studentBiz", StudentBiz.class);
		if(null == t){
			request.setAttribute("list", biz.getStudentList());
			request.getRequestDispatcher("index.jsp").forward(request, response);			
		}else if(t.equals("add")){
			String name = request.getParameter("name");
			Student student = new Student(name);
			biz.add(student);
			response.sendRedirect("stu");
		}
	}

}
