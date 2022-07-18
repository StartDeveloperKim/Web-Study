package com.WebStudy.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.WebStudy.web.entity.Notice;
import com.WebStudy.web.service.NoticeService;

@WebServlet("/webstudy")
public class NoticeListController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !query_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		try {
			List<Notice> list = service.getNoticeList(field, query, page);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/View/notice.jsp").forward(request, response);
		} catch( Error e){
			e.printStackTrace();
		}
		//int count = service.getNoticeCount(field, query);
		
		
		
	}
}
