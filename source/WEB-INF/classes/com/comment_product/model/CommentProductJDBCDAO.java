package com.comment_product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class CommentProductJDBCDAO implements CommentProductDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "  INSERT INTO COMMENT_PRODUCT  (COMMENT_PRODUCT_NO,MEMBER_NO,PRODUCT_NO,PRODUCT_STAR,COMMENT_PRODUCT_CONTENT,COMMENT_PRODUCT_TIME) VALUES('CP'||LPAD(TO_CHAR(COMMENT_PRODUCT_NO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)"; 
	private static final String DELETE = "DELETE FROM COMMENT_PRODUCT WHERE COMMENT_PRODUCT_NO=?";
	private static final String UPDATE = "UPDATE COMMENT_PRODUCT SET PRODUCT_STAR=?,COMMENT_PRODUCT_CONTENT=? WHERE COMMENT_PRODUCT_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM COMMENT_PRODUCT";
	private static final String GET_ONE_STMT = "SELECT * FROM COMMENT_PRODUCT WHERE COMMENT_PRODUCT_NO=?";
	
	@Override
	public void insert(CommentProductVO commentproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, commentproductVO.getMember_no());
			pstmt.setString(2, commentproductVO.getProduct_no());
			pstmt.setInt(3, commentproductVO.getProduct_star());
			pstmt.setString(4, commentproductVO.getComment_product_content());
			pstmt.setTimestamp(5, commentproductVO.getComment_product_time());
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
	public void update(CommentProductVO commentproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, commentproductVO.getProduct_star());
			pstmt.setString(2, commentproductVO.getComment_product_content());
			pstmt.setString(3, commentproductVO.getComment_product_no());
			
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
	public void delete(String comment_product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, comment_product_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + comment_product_no + ":" + updateCount_record + "筆");
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
	public CommentProductVO findByPrimaryKey(String comment_product_no) {
		CommentProductVO commentproductvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, comment_product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				commentproductvo = new CommentProductVO();
				commentproductvo.setMember_no(rs.getString("member_no"));
				commentproductvo.setProduct_no(rs.getString("product_no"));
				commentproductvo.setProduct_star(rs.getInt("product_star"));
				commentproductvo.setComment_product_content(rs.getString("comment_product_content"));
				commentproductvo.setComment_product_time(rs.getTimestamp("comment_product_time"));
				


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
		return commentproductvo;
	}
	@Override
	public List<CommentProductVO> getAll() {
		List<CommentProductVO> list = new ArrayList<CommentProductVO>();
		CommentProductVO commentproductgetall = null;

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
				commentproductgetall = new CommentProductVO();
				commentproductgetall.setComment_product_no(rs.getString("comment_product_no"));
				commentproductgetall.setMember_no(rs.getString("member_no"));
				commentproductgetall.setProduct_no(rs.getString("product_no"));				
				commentproductgetall.setProduct_star(rs.getInt("product_star"));
				commentproductgetall.setComment_product_content(rs.getString("comment_product_content"));
				commentproductgetall.setComment_product_time(rs.getTimestamp("comment_product_time"));
				list.add(commentproductgetall); // Store the row in the list
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
		CommentProductJDBCDAO commentproductjdbcdao = new CommentProductJDBCDAO();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		// insert
//		CommentProductVO commentproductvoinsert = new CommentProductVO();
//		commentproductvoinsert.setMember_no("M001");
//		commentproductvoinsert.setProduct_no("P001");
//		commentproductvoinsert.setProduct_star(5);
//		commentproductvoinsert.setComment_product_content("太好用了吧");
//		commentproductvoinsert.setComment_product_time(time);
//		
//		
//		commentproductjdbcdao.insert(commentproductvoinsert);

		// update

//		CommentProductVO commentproductvoupdate = new CommentProductVO();
//		commentproductvoupdate.setProduct_star(5);
//		commentproductvoupdate.setComment_product_content("我要推薦我朋友太好用了");
//		commentproductvoupdate.setComment_product_no("CP001");
//		commentproductjdbcdao.update(commentproductvoupdate);

		// delete
//		commentproductjdbcdao.delete("CP003");

		// getOne
		CommentProductVO commentproductvogetone = commentproductjdbcdao.findByPrimaryKey("CP008");
		System.out.print(commentproductvogetone.getMember_no() + ",");	
		System.out.print(commentproductvogetone.getProduct_no() + ",");			
		System.out.print(commentproductvogetone.getProduct_star()+ ",");
		System.out.print(commentproductvogetone.getComment_product_content() + ",");
		System.out.print(commentproductvogetone.getComment_product_time());
		System.out.println("---------------------");

		// getAll
		List<CommentProductVO> list = commentproductjdbcdao.getAll();
		for (CommentProductVO acommentproductvo : list) {
			System.out.print(acommentproductvo.getComment_product_no() + ",");
			System.out.print(acommentproductvo.getMember_no() + ",");
			System.out.print(acommentproductvo.getProduct_no() + ",");
			System.out.print(acommentproductvo.getProduct_star()+ ",");
			System.out.print(acommentproductvo.getComment_product_content() + ",");
			System.out.print(acommentproductvo.getComment_product_time());
			System.out.println();

		}
	}
	
}
