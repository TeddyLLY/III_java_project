package com.news.model;

import java.util.*;
import java.sql.*;

public class NewsJDBCDAO implements NewsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO news (news_no,news_title,news_content,news_time) VALUES ('N'||LPAD(TO_CHAR(NEWS_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT news_no,news_title,news_content,to_char(news_time,'yyyy-mm-dd') news_time FROM news order by news_no";
	private static final String GET_ONE_STMT = "SELECT news_no,news_title,news_content,to_char(news_time,'yyyy-mm-dd') news_time FROM news where news_no = ?";
	private static final String DELETE = "DELETE FROM news where news_no = ?";
	private static final String UPDATE = "UPDATE news set news_title=?, news_content=?, news_time=? where news_no = ?";

	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setDate(3, newsVO.getNews_time());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsVO.getNews_title());
			pstmt.setString(2, newsVO.getNews_content());
			pstmt.setDate(3, newsVO.getNews_time());
			pstmt.setString(4, newsVO.getNews_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, news_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public NewsVO findByPrimaryKey(String news_no) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("news_no"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getDate("news_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				newsVO = new NewsVO();
				newsVO.setNews_no(rs.getString("news_no"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsVO.setNews_time(rs.getDate("news_time"));
				list.add(newsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		NewsJDBCDAO dao = new NewsJDBCDAO();

		// 新增
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setNews_title("員工地區淨灘活動");
//		newsVO1.setNews_content("善盡企業的社會責任，不間斷地進行慈善公益與捐款活動");
////	newsVO1.setNews_time(java.sql.Date.valueOf("2020-01-30"));
//		newsVO1.setNews_time(new java.sql.Date(new GregorianCalendar(2020,01,21).getTime().getTime()));
//		dao.insert(newsVO1);

		// 修改
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setNews_no("N011");
//		newsVO1.setNews_title("員工淨灘活動");
//		newsVO1.setNews_content("員工淨灘活動，善盡企業的社會責任，不間斷地進行慈善公益與捐款活動");
//		newsVO1.setNews_time(java.sql.Date.valueOf("2020-01-30"));
//
//		dao.update(newsVO1);

		// 刪除
//		dao.delete("N011");

		// 單筆查詢
//		NewsVO newsVO3 = dao.findByPrimaryKey("N010");
//		System.out.println(newsVO3.getNews_no());
//		System.out.println(newsVO3.getNews_title());
//		System.out.println(newsVO3.getNews_content());
//		System.out.println(newsVO3.getNews_time());

		// 多筆查詢
//		List<NewsVO> list = dao.getAll();
//		for (NewsVO aEmp : list) {
//			System.out.print(aEmp.getNews_no() + ",");
//			System.out.print(aEmp.getNews_title() + ",");
//			System.out.print(aEmp.getNews_content() + ",");
//			System.out.print(aEmp.getNews_time());
//			System.out.println();
//		}
	}

}
