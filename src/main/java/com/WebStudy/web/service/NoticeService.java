package com.WebStudy.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.WebStudy.web.entity.Notice;

public class NoticeService {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String user = "C##TAEWOO";
	private String password = "0000";

	public List<Notice> getNoticeList() {
		return getNoticeList("title", "", 1);
	}

	public List<Notice> getNoticeList(int page) {
		return getNoticeList("title", "", page);
	}

	// 검색할 때
	public List<Notice> getNoticeList(String field, String query, int page) {

		List<Notice> list = new ArrayList<>();

		String sql = "SELECT * " + "FROM ( SELECT ROWNUM NUM, N.* FROM " + "    (SELECT * FROM NOTICE WHERE " + field
				+ " LIKE ? ORDER BY REGDATE DESC) N " + "    ) WHERE NUM BETWEEN ? AND ?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			

			st.setString(1, "%" + query + "%");
			st.setInt(2, 1 + (page - 1) * 3);
			st.setInt(3, page * 3);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
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

		return list;
	}

	// Notice 테이블의 글의 개수 알아낼 때
	public int getNoticeCount() {

		return getNoticeCount("title", "");
	}

	// 검색창에 검색을 했을 때 Count
	public int getNoticeCount(String field, String query) {

		int count = 0;
		String sql = "SELECT COUNT(*) COUNT FROM( " + " SELECT ROWNUM NUM, N.* " + " FROM (SELECT * FROM NOTICE WHERE "
				+ field + " LIKE ? ORDER BY REGDATE DESC) N)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, "%" + query + "%");
			
			ResultSet rs = st.executeQuery();

			if (rs.next())
				count = rs.getInt("count");

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

		return count;
	}

	public Notice getNotice(int id) {

		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");
				int hit = rs.getInt("HIT");
				String phone_num = rs.getString("PHONE_NUM");
				String email = rs.getString("EMAIL");

				notice = new Notice(nid, title, content, regdate, writerId, hit, phone_num, email);
			}

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
		
		return notice;
	}

	public Notice getNextNotice(int id) {

		return null;
	}

	public Notice getPrevNotice(int id) {

		return null;
	}
}
