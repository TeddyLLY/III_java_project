package com.report_product.model;

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

public class ReportProductJNDIDAO implements ReportProductDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "  INSERT INTO REPORT_PRODUCT  (REPORT_PRODUCT_NO,MEMBER_NO,PRODUCT_NO,REPORT_PRODUCT_CONTENT,REPORT_PRODUCT_STATUS,REPORT_PRODUCT_TIME) VALUES('RP'||LPAD(TO_CHAR(REPORT_PRODUCT_NO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM REPORT_PRODUCT WHERE REPORT_PRODUCT_NO=?";
	private static final String UPDATE = "UPDATE REPORT_PRODUCT SET REPORT_PRODUCT_CONTENT=?,REPORT_PRODUCT_STATUS=? WHERE REPORT_PRODUCT_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM REPORT_PRODUCT";
	private static final String GET_ONE_STMT = "SELECT * FROM REPORT_PRODUCT WHERE REPORT_PRODUCT_NO=?";

	@Override
	public void insert(ReportProductVO reportproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reportproductVO.getMember_no());
			pstmt.setString(2, reportproductVO.getProduct_no());

			pstmt.setString(3, reportproductVO.getReport_product_content());
			pstmt.setInt(4, reportproductVO.getReport_product_status());
			pstmt.setTimestamp(5, reportproductVO.getReport_product_time());
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
	public void update(ReportProductVO reportproductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reportproductVO.getReport_product_content());
			pstmt.setInt(2, reportproductVO.getReport_product_status());
			pstmt.setString(3, reportproductVO.getReport_product_no());

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
	public void delete(String report_product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, report_product_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + report_product_no + ":" + updateCount_record + "筆");
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
	public ReportProductVO findByPrimaryKey(String report_product_no) {
		ReportProductVO reportproductvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, report_product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				reportproductvo = new ReportProductVO();
				reportproductvo.setMember_no(rs.getString("member_no"));
				reportproductvo.setProduct_no(rs.getString("product_no"));
				reportproductvo.setReport_product_content(rs.getString("report_product_content"));
				reportproductvo.setReport_product_status(rs.getInt("report_product_status"));
				reportproductvo.setReport_product_time(rs.getTimestamp("report_product_time"));

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
		return reportproductvo;
	}

	@Override
	public List<ReportProductVO> getAll() {
		List<ReportProductVO> list = new ArrayList<ReportProductVO>();
		ReportProductVO reportproductvogetall = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				reportproductvogetall = new ReportProductVO();
				reportproductvogetall.setReport_product_no(rs.getString("report_product_no"));
				reportproductvogetall.setMember_no(rs.getString("member_no"));
				reportproductvogetall.setProduct_no(rs.getString("product_no"));
				reportproductvogetall.setReport_product_content(rs.getString("report_product_content"));
				reportproductvogetall.setReport_product_status(rs.getInt("report_product_status"));
				reportproductvogetall.setReport_product_time(rs.getTimestamp("report_product_time"));
				list.add(reportproductvogetall); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
