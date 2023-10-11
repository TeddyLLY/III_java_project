package com.reply_article.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReplyArticleJNDIDAO implements ReplyArticleDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, replyArticleVO.getReply_content());
			pstmt.setTimestamp(2, replyArticleVO.getReply_time());
			pstmt.setTimestamp(3, replyArticleVO.getLast_updated());
			pstmt.setString(4, replyArticleVO.getArticle_no());
			pstmt.setString(5, replyArticleVO.getMember_no());
			pstmt.setString(6, replyArticleVO.getCoach_no());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, reply_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

	@Override
	public List<ReplyArticleVO> getAllReplyByArticleNo(String article_no) {
		ReplyArticleVO replyArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyArticleVO> list = new ArrayList<ReplyArticleVO>();
		try {

			con = ds.getConnection();
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
				list.add(replyArticleVO);
			}

			// Handle any driver errors
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
}
