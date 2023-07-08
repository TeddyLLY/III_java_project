package com.comment_product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentProductJNDIDAO implements CommentProductDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, commentproductVO.getMember_no());
			pstmt.setString(2, commentproductVO.getProduct_no());
			pstmt.setInt(3, commentproductVO.getProduct_star());
			pstmt.setString(4, commentproductVO.getComment_product_content());
			pstmt.setTimestamp(5, commentproductVO.getComment_product_time());
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
	public void update(CommentProductVO commentproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, commentproductVO.getProduct_star());
			pstmt.setString(2, commentproductVO.getComment_product_content());
			pstmt.setString(3, commentproductVO.getComment_product_no());
			
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
	public void delete(String comment_product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, comment_product_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + comment_product_no + ":" + updateCount_record + "筆");
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
	public CommentProductVO findByPrimaryKey(String comment_product_no) {
		CommentProductVO commentproductvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
