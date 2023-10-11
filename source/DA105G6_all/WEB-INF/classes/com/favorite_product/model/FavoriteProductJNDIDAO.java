package com.favorite_product.model;

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

public class FavoriteProductJNDIDAO implements FavoriteProductDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = " INSERT INTO FAVORITE_PRODUCT (PRODUCT_NO,MEMBER_NO) VALUES(?,?)";
	private static final String DELETE = "DELETE FROM FAVORITE_PRODUCT WHERE MEMBER_NO=? AND PRODUCT_NO=?";
	private static final String UPDATE = "UPDATE FAVORITE_PRODUCT SET PRODUCT_NO=? WHERE MEMBER_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM FAVORITE_PRODUCT";
	private static final String GET_ONE_STMT = "SELECT * FROM FAVORITE_PRODUCT WHERE MEMBER_NO=?";
	@Override
	public void insert(FavoriteProductVO favoriteproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setString(1, favoriteproductVO.getProduct_no());
			pstmt.setString(2, favoriteproductVO.getMember_no());

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
	public void update(FavoriteProductVO favoriteproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, favoriteproductVO.getMember_no());
			pstmt.setString(2, favoriteproductVO.getProduct_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String member_no,String product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_no);
			pstmt.setString(2, product_no);
			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + member_no + ":" + updateCount_record + "筆");
			System.out.println("刪除紀錄編號:" + product_no + ":" + updateCount_record + "筆");
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
	public List<FavoriteProductVO> findByPrimaryKey(String member_no) {
		List<FavoriteProductVO> favoriteproductvoList = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteproductvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				favoriteproductvo = new FavoriteProductVO();
				favoriteproductvo.setMember_no("member_no");
				favoriteproductvo.setProduct_no(rs.getString("product_no"));
				favoriteproductvoList.add(favoriteproductvo);
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
		return favoriteproductvoList;
	}
	@Override
	public List<FavoriteProductVO> getAll() {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteproductvogetall = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				favoriteproductvogetall = new FavoriteProductVO();
				favoriteproductvogetall.setMember_no(rs.getString("member_no"));
				favoriteproductvogetall.setProduct_no(rs.getString("product_no"));
				list.add(favoriteproductvogetall); // Store the row in the list
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
