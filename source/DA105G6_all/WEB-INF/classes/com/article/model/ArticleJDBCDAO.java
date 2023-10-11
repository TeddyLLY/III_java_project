package com.article.model;


import java.sql.*;
import java.util.*;

import com.reply_article.model.ReplyArticleVO;


public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO article (article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no) VALUES ('A'||LPAD(TO_CHAR(ARTICLE_NO_SEQ.NEXTVAL),3,'0'), ?, ?,  ?, ?, ?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no FROM Article order by article_no";
	private static final String GET_ONE_STMT = 
			"SELECT article_no,article_title,article_content,article_glass,article_visitors,article_replies,article_status,article_post_time,article_editart_lasttime,member_no,coach_no FROM Article where article_no = ?";
	private static final String DELETE = 
		"DELETE FROM Article where article_no = ?";
	private static final String UPDATE = 
		"UPDATE Article set  article_title=?, article_content=?, article_glass=?, article_visitors=?, article_replies=? ,article_status=?,article_post_time=?,article_editart_lasttime=?,member_no=?,coach_no=? where article_no = ?";
	private static final String QUERY = "select * from article where article_no like ? and article_title like ? and article_content like ? and article_glass like ?";
	private static final String FIND_REPLY_BY_ARTICLE_NO = "SELECT reply_no,reply_content,reply_time,REPLY_EDITART_LASTTIME,article_no,member_no,coach_no FROM reply_article where article_no=?";
	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			pstmt.setString(11, articleVO.getArticle_no());
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
	public void delete(String article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(articleVO); // Store the row in the list
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
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(QUERY);
			rs = pstmt.executeQuery();

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

		ArticleJDBCDAO dao = new ArticleJDBCDAO();

		// 新增
//		ArticleVO articleVO1 = new ArticleVO();
//		articleVO1.setArticle_title("測試");
//		articleVO1.setArticle_content("測試");
//		articleVO1.setArticle_glass("閒聊");
//		articleVO1.setArticle_visitors(50);
//		articleVO1.setArticle_replies(50);
//		articleVO1.setArticle_status(0);

//		articleVO1.setArticle_post_time(new java.sql.Timestamp(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		articleVO1.setArticle_editart_lasttime(new java.sql.Timestamp(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		dao.insert(articleVO1);

		// 修改
//		ArticleVO articleVO2 = new ArticleVO();
//		articleVO2.setArticle_no("A005");
//		articleVO2.setArticle_title("測試");
//		articleVO2.setArticle_content("測試");
//		articleVO2.setArticle_glass("閒聊");
//		articleVO2.setArticle_visitors(50);
//		articleVO2.setArticle_replies(50);
//		articleVO2.setArticle_status(0);

//		articleVO2.setArticle_post_time(new java.sql.Timestamp(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		articleVO2.setArticle_editart_lasttime(new java.sql.Timestamp(new GregorianCalendar(2020,01,26).getTime().getTime()));
//		dao.update(articleVO2);
//
//		// 刪除
//		dao.delete("A008");
//
//		// 查詢
//		ArticleVO articleVO3 = dao.findByPrimaryKey("A005");
//		System.out.print(articleVO3.getArticle_no() + ",");
//		System.out.print(articleVO3.getArticle_title() + ",");
//		System.out.print(articleVO3.getArticle_content() + ",");
//		System.out.print(articleVO3.getArticle_glass() + ",");
//		System.out.print(articleVO3.getArticle_visitors() + ",");
//		System.out.print(articleVO3.getArticle_replies() + ",");
//		System.out.println(articleVO3.getArticle_status()+ ",");

//		System.out.println(articleVO3.getArticle_post_time()+ ",");
//		System.out.println(articleVO3.getArticle_editart_lasttime());
//		System.out.println("---------------------");
////
//		// 查詢
		List<ArticleVO> list = dao.getAll();
		for (ArticleVO aArticle : list) {
			System.out.print(aArticle.getArticle_no() + ",");
			System.out.print(aArticle.getArticle_title() + ",");
			System.out.print(aArticle.getArticle_content() + ",");
			System.out.print(aArticle.getArticle_glass() + ",");
			System.out.print(aArticle.getArticle_visitors() + ",");
			System.out.print(aArticle.getArticle_replies() + ",");
			System.out.print(aArticle.getArticle_status()+ ",");

			System.out.print(aArticle.getArticle_post_time()+ ",");
			System.out.print(aArticle.getArticle_editart_lasttime());
			System.out.println();
		}
	}
}