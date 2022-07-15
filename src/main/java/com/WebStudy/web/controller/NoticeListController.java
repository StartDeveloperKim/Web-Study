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

@WebServlet("/notice/webstudy")
public class NoticeListController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "C##TAEWOO";
		String password = "0000";
		String sql = "SELECT * FROM NOTICE";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String phone_num = rs.getString("PHONE_NUM");
				String email = rs.getString("EMAIL");
				
				Notice n = new Notice(id, title, content, regdate, writerId, hit, phone_num, email);
				
				
				list.add(n);
			}
			
			/*
			 * for(Notice n: list) { System.out.println(n.toString()); }
			 */
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("클래스 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/View/notice.jsp").forward(request, response);
		
	}
}
