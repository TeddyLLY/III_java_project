package com.reply_article.model;

import java.sql.*;
import java.util.*;

public class ReplyArticleJDBCDAO implements ReplyArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO reply_article (reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no) VALUES (('R'||LPAD(TO_CHAR(REPLY_NO_SEQ.NEXTVAL),3,'0')), ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no FROM reply_article order by reply_no";
	private static final String GET_ONE_STMT = "SELECT reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no FROM reply_article where reply_no = ?";
	private static final String DELETE = "DELETE FROM reply_article where reply_no = ?";
	private static final String UPDATE = "UPDATE reply_article set reply_content=?, reply_time=?, REPLY_EDITART_LASTTIME=?,article_no=?,member_no=?,coach_no=? where reply_no = ?";
	private static final String FIND_REPLY_BY_ARTICLE_NO = "SELECT reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no FROM reply_article where article_no=?";

	@Override
	public void insert(ReplyArticleVO replyArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, replyArticleVO.getReply_content());
			pstmt.setTimestamp(2, replyArticleVO.getReply_time());
			pstmt.setTimestamp(3, replyArticleVO.getLast_updated());
			pstmt.setString(4, replyArticleVO.getArticle_no());
			pstmt.setString(5, replyArticleVO.getMember_no());
			pstmt.setString(6, replyArticleVO.getCoach_no());

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
	public void update(ReplyArticleVO replyArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, replyArticleVO.getReply_content());
			pstmt.setTimestamp(2, replyArticleVO.getReply_time());
			pstmt.setTimestamp(3, replyArticleVO.getLast_updated());
			pstmt.setString(4, replyArticleVO.getArticle_no());
			pstmt.setString(5, replyArticleVO.getMember_no());
			pstmt.setString(6, replyArticleVO.getCoach_no());
			pstmt.setString(7, replyArticleVO.getReply_no());

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
	public void delete(String reply_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, reply_no);

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
	public ReplyArticleVO findByPrimaryKey(String reply_no) {

		ReplyArticleVO replyArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, reply_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyArticleVO = new ReplyArticleVO();
				replyArticleVO.setReply_no(rs.getString("reply_no"));
				replyArticleVO.setReply_content(rs.getString("reply_content"));
				replyArticleVO.setReply_time(rs.getTimestamp("reply_time"));
				replyArticleVO.setLast_updated(rs.getTimestamp("REPLY_EDITART_LASTTIME"));
				replyArticleVO.setArticle_no(rs.getString("article_no"));
				replyArticleVO.setMember_no(rs.getString("member_no"));
				replyArticleVO.setCoach_no(rs.getString("coach_no"));

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
		return replyArticleVO;
	}

	@Override
	public List<ReplyArticleVO> getAll() {
		List<ReplyArticleVO> list = new ArrayList<ReplyArticleVO>();
		ReplyArticleVO replyArticleVO = null;

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
				replyArticleVO = new ReplyArticleVO();
				replyArticleVO.setReply_no(rs.getString("reply_no"));
				replyArticleVO.setReply_content(rs.getString("reply_content"));
				replyArticleVO.setReply_time(rs.getTimestamp("reply_time"));
				replyArticleVO.setLast_updated(rs.getTimestamp("REPLY_EDITART_LASTTIME"));
				replyArticleVO.setArticle_no(rs.getString("article_no"));
				replyArticleVO.setMember_no(rs.getString("member_no"));
				replyArticleVO.setCoach_no(rs.getString("coach_no"));
				list.add(replyArticleVO); // Store the row in the list
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

	public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no) {
		List<ReplyArticleVO> list = new ArrayList<ReplyArticleVO>();
		ReplyArticleVO replyArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_REPLY_BY_ARTICLE_NO);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyArticleVO = new ReplyArticleVO();
				replyArticleVO.setReply_no(rs.getString("reply_no"));
				replyArticleVO.setReply_content(rs.getString("reply_content"));
				replyArticleVO.setReply_time(rs.getTimestamp("reply_time"));
				replyArticleVO.setLast_updated(rs.getTimestamp("REPLY_EDITART_LASTTIME"));
				replyArticleVO.setArticle_no(rs.getString("article_no"));
				replyArticleVO.setMember_no(rs.getString("member_no"));
				replyArticleVO.setCoach_no(rs.getString("coach_no"));
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

		ReplyArticleJDBCDAO dao = new ReplyArticleJDBCDAO();

//		// 新增
//		ReplyArticleVO replyArticleVO = new ReplyArticleVO();
//		replyArticleVO.setReply_content("222");
//		replyArticleVO.setReply_time(new java.sql.Timestamp(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setLast_updated(new java.sql.Timestamp(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setArticle_no("A003");
//		replyArticleVO.setMember_no(null);
//		replyArticleVO.setCoach_no("C001");
//		dao.insert(replyArticleVO);
//
		// 修改
//		ReplyArticleVO replyArticleVO = new ReplyArticleVO();
//		replyArticleVO.setReply_no("R003");
//		replyArticleVO.setReply_content("123");
//		replyArticleVO.setReply_time(new java.sql.Timestamp(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setLast_updated(new java.sql.Timestamp(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		replyArticleVO.setArticle_no("A002");
//		replyArticleVO.setMember_no("M002");
//		replyArticleVO.setCoach_no(null);
//		dao.update(replyArticleVO);
//
//		// 刪除
//		dao.delete("R007");
//
//		// 查詢
//		ReplyArticleVO replyArticleVO = dao.findByPrimaryKey("R003");
//		System.out.print(replyArticleVO.getReply_no() + ",");
//		System.out.print(replyArticleVO.getReply_content() + ",");
//		System.out.print(replyArticleVO.getReply_time() + ",");
//		System.out.print(replyArticleVO.getLast_updated() + ",");
//		System.out.print(replyArticleVO.getArticle_no() + ",");
//		System.out.print(replyArticleVO.getMember_no() + ",");
//		System.out.println(replyArticleVO.getCoach_no());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<ReplyArticleVO> list = dao.getAll();
//		for (ReplyArticleVO aEmp : list) {
//			System.out.print(aEmp.getReply_no() + ",");
//			System.out.print(aEmp.getReply_content() + ",");
//			System.out.print(aEmp.getReply_time() + ",");
//			System.out.print(aEmp.getLast_updated() + ",");
//			System.out.print(aEmp.getArticle_no() + ",");
//			System.out.print(aEmp.getMember_no() + ",");
//			System.out.print(aEmp.getCoach_no());
//			System.out.println();
//		}
	}
}
