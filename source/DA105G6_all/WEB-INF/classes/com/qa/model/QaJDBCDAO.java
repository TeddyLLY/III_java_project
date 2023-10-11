//package com.qa.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class QaJDBCDAO implements QaDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA105G6";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO qa (qa_no,qa_content) VALUES ('Q'||LPAD(TO_CHAR(QA_NO_SEQ.NEXTVAL), 3, '0'), ?)";
//	private static final String GET_ALL_STMT = "SELECT qa_no,qa_content FROM qa order by qa_no";
//	private static final String GET_ONE_STMT = "SELECT qa_no,qa_content FROM qa where qa_no = ?";
//	private static final String DELETE = "DELETE FROM qa where qa_no = ?";
//	private static final String UPDATE = "UPDATE qa set qa_content=? where qa_no = ?";
//
//	@Override
//	public void insert(QaVO qaVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, qaVO.getQa_content());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(QaVO qaVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, qaVO.getQa_content());
//			pstmt.setString(2, qaVO.getQa_no());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void delete(String qa_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, qa_no);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public QaVO findByPrimaryKey(String qa_no) {
//		QaVO qaVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, qa_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				qaVO = new QaVO();
//				qaVO.setQa_no(rs.getString("qa_no"));
//				qaVO.setQa_content(rs.getString("qa_content"));
//
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return qaVO;
//	}
//
//	@Override
//	public List<QaVO> getAll() {
//		List<QaVO> list = new ArrayList<QaVO>();
//		QaVO qaVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				qaVO = new QaVO();
//				qaVO.setQa_no(rs.getString("qa_no"));
//				qaVO.setQa_content(rs.getString("qa_content"));
//				list.add(qaVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	public static void main(String[] args) {
//		QaJDBCDAO dao = new QaJDBCDAO();
//
//		// 新增
////		QaVO qaVO1 = new QaVO();
////		
////		qaVO1.setQa_content("問:有氧課還有其它注意事項嗎？");
////		dao.insert(qaVO1);
//
//		// 修改
////		QaVO qaVO1 = new QaVO();
////		qaVO1.setQa_no("Q011");
////		qaVO1.setQa_content("問:有氧課還有其它注意事項嗎？, 答:上課中，請勿使用手機及大聲交談，避免影響他人。");
////		dao.update(qaVO1);
//
////		 刪除
////		dao.delete("Q011");
//
//		// 單筆查詢
////		QaVO qaVO1 = dao.findByPrimaryKey("Q010");
////		System.out.print(qaVO1.getQa_no() + ",");
////		System.out.print(qaVO1.getQa_content());
////		System.out.println();
//
//		// 多筆查詢
////		List<QaVO> list = dao.getAll();
////		
////		for (QaVO aQa : list) {
////			System.out.print(aQa.getQa_no() + ",");
////			System.out.print(aQa.getQa_content());
////			System.out.println();
////		}
//
//	}
//
//}
