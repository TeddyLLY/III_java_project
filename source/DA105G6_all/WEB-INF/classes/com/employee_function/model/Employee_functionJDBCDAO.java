//package com.employee_function.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class Employee_functionJDBCDAO implements Employee_functionDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA105G6";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO employee_function (function_no,function_name) VALUES ('F'||LPAD(TO_CHAR(employee_function_NO_SEQ.NEXTVAL), 3, '0'), ?)";
//	private static final String GET_ALL_STMT = "SELECT function_no,function_name FROM employee_function order by function_no";
//	private static final String GET_ONE_STMT = "SELECT function_no,function_name FROM employee_function where function_no = ?";
//	private static final String DELETE = "DELETE FROM employee_function where function_no = ?";
//	private static final String UPDATE = "UPDATE employee_function set function_name=? where function_no = ?";
//
//	@Override
//	public void insert(Employee_functionVO employee_functionVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, employee_functionVO.getFunction_name());
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
//	public void update(Employee_functionVO employee_functionVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, employee_functionVO.getFunction_name());
//			pstmt.setString(2, employee_functionVO.getFunction_no());
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
//	public void delete(String function_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, function_no);
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
//	public Employee_functionVO findByPrimaryKey(String function_no) {
//		Employee_functionVO employee_functionVO = null;
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
//			pstmt.setString(1, function_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				employee_functionVO = new Employee_functionVO();
//				employee_functionVO.setFunction_no(rs.getString("function_no"));
//				employee_functionVO.setFunction_name(rs.getString("function_name"));
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
//		return employee_functionVO;
//	}
//
//	@Override
//	public List<Employee_functionVO> getAll() {
//		List<Employee_functionVO> list = new ArrayList<Employee_functionVO>();
//		Employee_functionVO employee_functionVO = null;
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
//				employee_functionVO = new Employee_functionVO();
//				employee_functionVO.setFunction_no(rs.getString("function_no"));
//				employee_functionVO.setFunction_name(rs.getString("function_name"));
//				list.add(employee_functionVO); // Store the row in the list
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
//
//		Employee_functionJDBCDAO dao = new Employee_functionJDBCDAO();
//
//		// 新增
////		Employee_functionVO employee_functionVO1 = new Employee_functionVO();
////		
////		employee_functionVO1.setFunction_name("討論區檢舉管理");
////		dao.insert(employee_functionVO1);
////		System.out.println("新增成功");
//
//		// 修改
////		Employee_functionVO employee_functionVO1 = new Employee_functionVO();
////		employee_functionVO1.setFunction_no("F016");
////		employee_functionVO1.setFunction_name("管理討論區檢舉");
////		dao.update(employee_functionVO1);
////		System.out.println("修改成功");
//		
////		 刪除
////		dao.delete("F016");
////		System.out.println("刪除成功");
//		
//		// 單筆查詢
////		Employee_functionVO employee_functionVO1 = dao.findByPrimaryKey("F015");
////		System.out.print(employee_functionVO1.getFunction_no() + ",");
////		System.out.print(employee_functionVO1.getFunction_name());
////		System.out.println("---------------------");
//
//		// 多筆查詢
////		List<Employee_functionVO> list = dao.getAll();
////		
////		for (Employee_functionVO employee_function : list) {
////			System.out.print(employee_function.getFunction_no() + ",");
////			System.out.print(employee_function.getFunction_name());
////			System.out.println();
////		}	
//
//	}
//}
