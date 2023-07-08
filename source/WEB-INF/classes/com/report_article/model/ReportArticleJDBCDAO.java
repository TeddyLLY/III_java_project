package com.report_article.model;

import java.sql.*;
import java.util.*;


public class ReportArticleJDBCDAO implements ReportArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO report_article (report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no) VALUES (('RA'||LPAD(TO_CHAR( REPORT_ARTICLE_NO_SEQ.NEXTVAL),3,'0')), ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no FROM report_article order by report_article_no";
	private static final String GET_ONE_STMT = 
		"SELECT report_article_no,report_article_content,report_article_time,report_article_status,article_no,member_no FROM report_article where report_article_no = ?";
	private static final String DELETE = 
		"DELETE FROM report_article where report_article_no = ?";
	private static final String UPDATE = 
		"UPDATE report_article set report_article_content=?, report_article_time=?, report_article_status=?,article_no=?,member_no=? where report_article_no = ?";

	@Override
	public void insert(ReportArticleVO reportArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reportArticleVO.getReport_article_content());
			pstmt.setTimestamp(2, reportArticleVO.getReport_article_time());
			pstmt.setInt(3, reportArticleVO.getReport_article_status());
			pstmt.setString(4, reportArticleVO.getArticle_no());
			pstmt.setString(5, reportArticleVO.getMember_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ReportArticleVO reportArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reportArticleVO.getReport_article_content());
			pstmt.setTimestamp(2, reportArticleVO.getReport_article_time());
			pstmt.setInt(3, reportArticleVO.getReport_article_status());
			pstmt.setString(4, reportArticleVO.getArticle_no());
			pstmt.setString(5, reportArticleVO.getMember_no());
			pstmt.setString(6, reportArticleVO.getReport_article_no());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String report_article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, report_article_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ReportArticleVO findByPrimaryKey(String report_article_no) {

		ReportArticleVO reportArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, report_article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reportArticleVO = new ReportArticleVO();
				reportArticleVO.setReport_article_no(rs.getString("report_article_no"));
				reportArticleVO.setReport_article_content(rs.getString("report_article_content"));
				reportArticleVO.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticleVO.setReport_article_status(rs.getInt("report_article_status"));
				reportArticleVO.setArticle_no(rs.getString("article_no"));
				reportArticleVO.setMember_no(rs.getString("member_no"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return reportArticleVO;
	}

	@Override
	public List<ReportArticleVO> getAll() {
		List<ReportArticleVO> list = new ArrayList<ReportArticleVO>();
		ReportArticleVO reportArticleVO = null;

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
				reportArticleVO = new ReportArticleVO();
				reportArticleVO = new ReportArticleVO();
				reportArticleVO.setReport_article_no(rs.getString("report_article_no"));
				reportArticleVO.setReport_article_content(rs.getString("report_article_content"));
				reportArticleVO.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticleVO.setReport_article_status(rs.getInt("report_article_status"));
				reportArticleVO.setArticle_no(rs.getString("article_no"));
				reportArticleVO.setMember_no(rs.getString("member_no"));
				
				list.add(reportArticleVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		ReportArticleJDBCDAO dao = new ReportArticleJDBCDAO();

//		// 新增
//		ReportArticleVO reportArticleVO = new ReportArticleVO();
//		reportArticleVO.setReport_article_content("111");
//		reportArticleVO.setReport_article_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		reportArticleVO.setReport_article_status(0);
//		reportArticleVO.setArticle_no("A002");
//		reportArticleVO.setMember_no("M003");
//		
//		dao.insert(reportArticleVO);
//
//		// 修改
//		ReportArticleVO reportArticleVO = new ReportArticleVO();
//		reportArticleVO.setReport_article_no("RA002");
//		reportArticleVO.setReport_article_content("吳永志2");
//		reportArticleVO.setReport_article_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		reportArticleVO.setReport_article_status(0);
//		reportArticleVO.setArticle_no("A001");
//		reportArticleVO.setMember_no("M005");
//		
//		dao.update(reportArticleVO);
//
//		// 刪除
//		dao.delete("RA002");
//
		// 查詢
//		ReportArticleVO reportArticleVO = dao.findByPrimaryKey("RA001");
//		System.out.print(reportArticleVO.getReport_article_no() + ",");
//		System.out.print(reportArticleVO.getReport_article_content() + ",");
//		System.out.print(reportArticleVO.getReport_article_time() + ",");
//		System.out.print(reportArticleVO.getReport_article_status() + ",");
//		System.out.print(reportArticleVO.getArticle_no() + ",");
//		System.out.print(reportArticleVO.getMember_no() + ",");
//		
//		System.out.println("---------------------");
//
		// 查詢
		List<ReportArticleVO> list = dao.getAll();
		for (ReportArticleVO aEmp : list) {
			System.out.print(aEmp.getReport_article_no() + ",");
			System.out.print(aEmp.getReport_article_content() + ",");
			System.out.print(aEmp.getReport_article_time() + ",");
			System.out.print(aEmp.getReport_article_status() + ",");
			System.out.print(aEmp.getArticle_no() + ",");
			System.out.print(aEmp.getMember_no() + ",");
			
			System.out.println();
		}
	}
}
