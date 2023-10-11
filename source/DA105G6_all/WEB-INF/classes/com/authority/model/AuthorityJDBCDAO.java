//package com.authority.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class AuthorityJDBCDAO implements AuthorityDAO_interface {
//
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "HUAN";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO authority (employee_no,function_no) VALUES (?, ?)";
//	private static final String GET_ALL_STMT = "SELECT employee_no,function_no FROM authority order by employee_no";
//	private static final String GET_ONE_STMT = "SELECT employee_no,function_no FROM authority where employee_no = ?";
//	private static final String DELETE = "DELETE FROM authority where employee_no = ? and function_no = ?";
//	
//private static final String UPDATE = "UPDATE authority set function_no = ? where employee_no = ? and function_no = ?";
//
//	@Override
//	public void insert(AuthorityVO authorityVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, authorityVO.getEmployee_no());
//			pstmt.setString(2, authorityVO.getFunction_no());
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
//	public void update(AuthorityVO authorityVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, authorityVO.getFunction_no()); // set
//	
//			pstmt.setString(2, authorityVO.getEmployee_no()); 
//			pstmt.setString(3, authorityVO.getFunction_no());// where
//			
//		
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
//	public void delete(AuthorityVO authorityVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, authorityVO.getEmployee_no());
//			pstmt.setString(2, authorityVO.getFunction_no());
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
//	public AuthorityVO findByPrimaryKey(String employee_no) {
//		AuthorityVO authorityVO = null;
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
//			pstmt.setString(1, employee_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				authorityVO = new AuthorityVO();
//				authorityVO.setEmployee_no(rs.getString("employee_no"));
//				authorityVO.setFunction_no(rs.getString("function_no"));
//				authorityVO.setFunction_no(rs.getString("function_no"));
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
//		return authorityVO;
//	}
//
//	@Override
//	public List<AuthorityVO> getAll() {
//		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
//		AuthorityVO authorityVO = null;
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
//				authorityVO = new AuthorityVO();
//				authorityVO.setEmployee_no(rs.getString("employee_no"));
//				authorityVO.setFunction_no(rs.getString("function_no"));
//				list.add(authorityVO); // Store the row in the list
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
//		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//
//		// 新增
////		AuthorityVO authorityO1 = new AuthorityVO();
////		authorityO1.setEmployee_no("E010");
////		authorityO1.setFunction_no("F013");
////		dao.insert(authorityO1);
//
//		// 修改  			//複合主鍵無法
////		AuthorityVO authorityVO2 = new AuthorityVO();
////
////		authorityVO2.setFunction_no("F002");
////
////		authorityVO2.setEmployee_no("E002");
////		authorityVO2.setFunction_no("F004");
////		dao.update(authorityVO2);
//
//		// 刪除
////		AuthorityVO authorityVO1 = new AuthorityVO();
////		authorityVO1.setFunction_no("F002");
////		authorityVO1.setEmployee_no("E001");
////		dao.delete(authorityVO1);
//
//		// 單筆查詢 
////		AuthorityVO authorityVO3 = dao.findByPrimaryKey("E009");
////		System.out.println(authorityVO3.getEmployee_no());
////		System.out.println(authorityVO3.getFunction_no());
////
////		// 多筆查詢
////		List<AuthorityVO> list = dao.getAll();
////		for (AuthorityVO Authority : list) {
////			System.out.print(Authority.getEmployee_no() + ",");
////			System.out.print(Authority.getFunction_no());
////			System.out.println();
////		}
//
//	}
//
//	@Override
//	public void insert2(AuthorityVO authorityVO, Connection con) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
