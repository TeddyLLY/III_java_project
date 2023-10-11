package com.employee.model;

import java.util.*;

import com.authority.model.AuthorityVO;
import com.employee_function.model.Employee_functionVO;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO employee (employee_no,employee_name,employee_title,employee_account,employee_password,employee_mail,employee_cellphone,employee_photo) VALUES ('E'||LPAD(TO_CHAR(EMPLOYEE_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT employee_no,employee_name,employee_title,employee_account,employee_password,employee_mail,employee_cellphone FROM employee order by employee_no";
	private static final String GET_ONE_STMT = "SELECT employee_no,employee_name,employee_title,employee_account,employee_password,employee_mail,employee_cellphone FROM employee where employee_no = ?";
	private static final String DELETE = "DELETE FROM employee where employee_no = ?";
	private static final String UPDATE = "UPDATE employee set employee_name=?, employee_title=?, employee_account=?, employee_password=?, employee_mail=?, employee_cellphone=? where employee_no = ?";

	private static final String UPDATEPIC = "UPDATE employee SET employee_photo = ? WHERE employee_no=?";
	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getEmployee_name());
			pstmt.setString(2, employeeVO.getEmployee_title());
			pstmt.setString(3, employeeVO.getEmployee_account());
			pstmt.setString(4, employeeVO.getEmployee_password());
			pstmt.setString(5, employeeVO.getEmployee_mail());
			pstmt.setString(6, employeeVO.getEmployee_cellphone());
			pstmt.setBytes(7, employeeVO.getEmployee_photo());

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
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getEmployee_name());
			pstmt.setString(2, employeeVO.getEmployee_title());
			pstmt.setString(3, employeeVO.getEmployee_account());
			pstmt.setString(4, employeeVO.getEmployee_password());
			pstmt.setString(5, employeeVO.getEmployee_mail());
			pstmt.setString(6, employeeVO.getEmployee_cellphone());
			pstmt.setString(7, employeeVO.getEmployee_no());

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
	public void delete(String employee_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, employee_no);

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
	public EmployeeVO findByPrimaryKey(String employee_no) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, employee_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmployee_no(rs.getString("employee_no"));
				employeeVO.setEmployee_name(rs.getString("employee_name"));
				employeeVO.setEmployee_title(rs.getString("employee_title"));
				employeeVO.setEmployee_account(rs.getString("Employee_account"));
				employeeVO.setEmployee_password(rs.getString("Employee_password"));
				employeeVO.setEmployee_mail(rs.getString("employee_mail"));
				employeeVO.setEmployee_cellphone(rs.getString("Employee_cellphone"));

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
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

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
				employeeVO = new EmployeeVO();
				employeeVO.setEmployee_no(rs.getString("employee_no"));
				employeeVO.setEmployee_name(rs.getString("employee_name"));
				employeeVO.setEmployee_title(rs.getString("employee_title"));
				employeeVO.setEmployee_account(rs.getString("employee_account"));
				employeeVO.setEmployee_password(rs.getString("employee_password"));
				employeeVO.setEmployee_mail(rs.getString("employee_mail"));
				employeeVO.setEmployee_cellphone(rs.getString("employee_cellphone"));
				list.add(employeeVO); // Store the row in the list
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
	
	public void setPic(EmployeeVO employeeVO ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid ,passwd);
			con.setAutoCommit(false);  
			pstmt = con.prepareStatement(UPDATEPIC);
			pstmt.setBytes(1, employeeVO.getEmployee_photo());
			pstmt.setString(2, employeeVO.getEmployee_no());
			
			pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			throw new RuntimeException(" update pic  error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();
		
		//上傳會員照片
		try {
			for(int i = 1 ; i<5 ; i++ ) {
				byte[] photo = null;
				photo = getPictureByteArray("C:/DA105_G6/G6_work_place/DA105G6_all/WebContent/tool/images/good/employee" + i +".jpg");
				String employee_no;
				if(i<5) {
					employee_no = "E00"+i;
				}else {
					employee_no = "E0"+i;
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmployee_photo(photo);
				empVO.setEmployee_no(employee_no);
				dao.setPic(empVO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// 新增
//		EmployeeVO employeeVO1 = new EmployeeVO();
//byte[] pic = getPictureByteArray("D:\\pic/pic7.jpg");	
//		employeeVO1.setEmployee_name("chika");
//		employeeVO1.setEmployee_title("客服人員");
//		employeeVO1.setEmployee_account("chika5548");
//		employeeVO1.setEmployee_password("wertgb");
//		employeeVO1.setEmployee_mail("chika19921006@gmail.com");
//		employeeVO1.setEmployee_cellphone("0965045859");
//	
//employeeVO1.setEmployee_photo(pic);
//
//
//		dao.insert(employeeVO1);
//	
//		// 修改
//		EmployeeVO employeeVO2 = new EmployeeVO();
//		employeeVO2.setEmployee_no("E010");
//		employeeVO2.setEmployee_name("CHIKA");
//		employeeVO2.setEmployee_title("直播管理員");
//		employeeVO2.setEmployee_account("CHIKA20008154");
//		employeeVO2.setEmployee_password("ASDLKU");
//		employeeVO2.setEmployee_mail("CHIKA20000509@gmail.com");
//		employeeVO2.setEmployee_cellphone("098304692");
//		dao.update(employeeVO2);
	
		// 刪除
//		dao.delete("E011");
	
		// 單筆查詢
//		EmployeeVO employeeVO3 = dao.findByPrimaryKey("E010");
//		System.out.print(employeeVO3.getEmployee_no() + ",");
//		System.out.print(employeeVO3.getEmployee_name() + ",");
//		System.out.print(employeeVO3.getEmployee_title() + ",");
//		System.out.print(employeeVO3.getEmployee_account() + ",");
//		System.out.print(employeeVO3.getEmployee_password() + ",");
//		System.out.print(employeeVO3.getEmployee_mail() + ",");
//		System.out.println(employeeVO3.getEmployee_cellphone());
//		System.out.println("---------------------");
	
//		// 多筆查詢
//		List<EmployeeVO> list = dao.getAll();
//		
//		for (EmployeeVO Employee : list) {
//			System.out.print(Employee.getEmployee_no() + ",");
//			System.out.print(Employee.getEmployee_name() + ",");
//			System.out.print(Employee.getEmployee_title() + ",");
//			System.out.print(Employee.getEmployee_account() + ",");
//			System.out.print(Employee.getEmployee_password() + ",");
//			System.out.print(Employee.getEmployee_mail() + ",");
//			System.out.print(Employee.getEmployee_cellphone());
//			System.out.println();
//		}
		
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}


	@Override
	public List<String> getEmployeeByAuthority(String employee_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithAutjority(EmployeeVO employeeVO, List<AuthorityVO> insertAuthoritylist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findAuthority(String employee_no, String function_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EmployeeVO employeeLogin(String employee_account, String employee_password) {
		// TODO Auto-generated method stub
		return null;
	}


}
