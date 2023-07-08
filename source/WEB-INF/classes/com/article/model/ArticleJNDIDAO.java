package com.article.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.reply_article.model.ReplyArticleVO;

public class ArticleJNDIDAO implements ArticleDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO article (article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no,article_picture) VALUES ('A'||LPAD(TO_CHAR(ARTICLE_NO_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no,article_picture FROM Article order by article_no";
	private static final String GET_ONE_STMT = "SELECT article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no,article_picture FROM Article where article_no = ?";
	private static final String DELETE = "DELETE FROM Article where article_no = ?";
	private static final String UPDATE = "UPDATE Article set  article_title=?, article_content=?, article_glass=?, article_visitors=?, article_replies=? ,article_status=?,article_post_time=?,article_editart_lasttime=?,member_no=?,coach_no=?,article_picture=? where article_no = ?";
	private static final String QUERY = "select * from article where article_no like ? and article_title like ? and article_content like ? and article_glass like ?";
	private static final String FIND_REPLY_BY_ARTICLE_NO = "SELECT reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no FROM reply_article where article_no=?";
	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleVO.getArticle_title());
			pstmt.setString(2, articleVO.getArticle_content());
			pstmt.setString(3, articleVO.getArticle_glass());
			pstmt.setInt(4, articleVO.getArticle_visitors());
			pstmt.setInt(5, articleVO.getArticle_replies());
			pstmt.setInt(6, articleVO.getArticle_status());
			pstmt.setTimestamp(7, articleVO.getArticle_post_time());
			pstmt.setTimestamp(8, articleVO.getArticle_editart_lasttime());
			pstmt.setString(9, articleVO.getMember_no());
			pstmt.setString(10, articleVO.getCoach_no());
			pstmt.setBytes(11, articleVO.getArticle_picture());
			pstmt.executeUpdate();

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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, articleVO.getArticle_title());
			pstmt.setString(2, articleVO.getArticle_content());
			pstmt.setString(3, articleVO.getArticle_glass());
			pstmt.setInt(4, articleVO.getArticle_visitors());
			pstmt.setInt(5, articleVO.getArticle_replies());
			pstmt.setInt(6, articleVO.getArticle_status());
			pstmt.setTimestamp(7, articleVO.getArticle_post_time());
			pstmt.setTimestamp(8, articleVO.getArticle_editart_lasttime());
			pstmt.setString(9, articleVO.getMember_no());
			pstmt.setString(10, articleVO.getCoach_no());
			pstmt.setBytes(11, articleVO.getArticle_picture());
			pstmt.setString(12, articleVO.getArticle_no());
			
			pstmt.executeUpdate();

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
	public void delete(String article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

			pstmt.executeUpdate();

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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_glass(rs.getString("article_glass"));
				articleVO.setArticle_visitors(rs.getInt("article_visitors"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_post_time(rs.getTimestamp("article_post_time"));
				articleVO.setArticle_editart_lasttime(rs.getTimestamp("article_editart_lasttime"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setCoach_no(rs.getString("coach_no"));
				articleVO.setArticle_picture(rs.getBytes("article_picture"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_glass(rs.getString("article_glass"));
				articleVO.setArticle_visitors(rs.getInt("article_visitors"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_post_time(rs.getTimestamp("article_post_time"));
				articleVO.setArticle_editart_lasttime(rs.getTimestamp("article_editart_lasttime"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setCoach_no(rs.getString("coach_no"));
				articleVO.setArticle_picture(rs.getBytes("article_picture"));
				list.add(articleVO); // Store the row in the list
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

	public List<ArticleVO> query(String article_no, String article_title, String article_content,
			String article_glass) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (article_no == null) {
			article_no = "%%";
		} else {
			article_no = "%" + article_no + "%";
		}
		if (article_title == null) {
			article_title = "%%";

		} else {
			article_title = "%" + article_title + "%";
		}
		if (article_content == null) {
			article_content = "%%";
		} else {
			article_content = "%" + article_content + "%";
		}
		if (article_glass == null) {
			article_glass = "%%";
		} else {
			article_glass = "%" + article_glass + "%";
		}

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(QUERY);

			pstmt.setString(1, article_no);
			pstmt.setString(2, article_title);
			pstmt.setString(3, article_content);
			pstmt.setString(4, article_glass);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getString("article_no"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_glass(rs.getString("article_glass"));
				articleVO.setArticle_visitors(rs.getInt("article_visitors"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_post_time(rs.getTimestamp("article_post_time"));
				articleVO.setArticle_editart_lasttime(rs.getTimestamp("article_editart_lasttime"));
				articleVO.setMember_no(rs.getString("member_no"));
				articleVO.setCoach_no(rs.getString("coach_no"));
				list.add(articleVO); // Store the row in the list
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
