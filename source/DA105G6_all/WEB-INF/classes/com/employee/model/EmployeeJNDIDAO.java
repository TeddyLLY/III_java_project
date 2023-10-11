package com.employee.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.authority.model.AuthorityJNDIDAO;
import com.authority.model.AuthorityVO;
import com.employee_function.model.Employee_functionVO;

public class EmployeeJNDIDAO implements EmployeeDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO employee (employee_no,employee_name,employee_title,employee_account,employee_mail,employee_cellphone,employee_photo, employee_password) VALUES ('E'||LPAD(TO_CHAR(EMPLOYEE_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT employee_no,employee_name,employee_title,employee_account,employee_password,employee_mail,employee_cellphone,employee_photo FROM employee order by employee_no";
	private static final String GET_ONE_STMT = "SELECT employee_no,employee_name,employee_title,employee_account,employee_password,employee_mail,employee_cellphone,employee_photo FROM employee where employee_no = ?";
	private static final String DELETE_EMP = "DELETE FROM employee where employee_no = ?";
	private static final String UPDATE = "UPDATE employee set employee_name=?, employee_title=?, employee_account=?, employee_mail=?, employee_cellphone=?, employee_photo=? where employee_no = ?";

private static final String DELETE_AUT = "DELETE FROM authority where employee_no = ?";
private static final String GET_Eemployee_ByAuthority = "SELECT * FROM authority where employee_no = ?";
	
private static final String GET_ACTPWD_STMT = "select * from employee where employee_account = ? and employee_password = ?";
private static final String GET_AUTH_STMT = "select function_no from authority where employee_no = ? and function_no = ?";


	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, employeeVO.getEmployee_name());
			pstmt.setString(2, employeeVO.getEmployee_title());		
			pstmt.setString(3, employeeVO.getEmployee_account());
			pstmt.setString(7, employeeVO.getEmployee_password());
			pstmt.setString(4, employeeVO.getEmployee_mail());
			pstmt.setString(5, employeeVO.getEmployee_cellphone());

			pstmt.setBytes(6, employeeVO.getEmployee_photo());
			pstmt.executeUpdate();

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
		int updateAuthority = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			//	先刪權限
			pstmt = con.prepareStatement(DELETE_AUT);
			pstmt.setString(1, employee_no);
			updateAuthority = pstmt.executeUpdate();
			//	再刪員工
			pstmt = con.prepareStatement(DELETE_EMP);			
			pstmt.setString(1, employee_no);
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除員工編號"+employee_no+"時,共有權限"+updateAuthority+"個權限同時");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getEmployee_name());
			pstmt.setString(2, employeeVO.getEmployee_title());
			pstmt.setString(3, employeeVO.getEmployee_account());
//			pstmt.setString(7, employeeVO.getEmployee_password());
			pstmt.setString(4, employeeVO.getEmployee_mail());
			pstmt.setString(5, employeeVO.getEmployee_cellphone());
			pstmt.setBytes(6, employeeVO.getEmployee_photo());
			pstmt.setString(7, employeeVO.getEmployee_no());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, employee_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmployee_no(rs.getString("employee_no"));
				employeeVO.setEmployee_name(rs.getString("employee_name"));
				employeeVO.setEmployee_title(rs.getString("employee_title"));
				employeeVO.setEmployee_account(rs.getString("employee_account"));
				employeeVO.setEmployee_password(rs.getString("employee_password"));
				employeeVO.setEmployee_mail(rs.getString("employee_mail"));
				employeeVO.setEmployee_cellphone(rs.getString("employee_cellphone"));
				employeeVO.setEmployee_photo(rs.getBytes("employee_photo"));
			}

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

			con = ds.getConnection();
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
				employeeVO.setEmployee_photo(rs.getBytes("employee_photo"));
				list.add(employeeVO); // Store the row in the list
			}

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



	@Override
	public EmployeeVO employeeLogin(String employee_account, String employee_password) {

		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ACTPWD_STMT);

			pstmt.setString(1, employee_account);
			pstmt.setString(2, employee_password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmployee_account(rs.getString("employee_account"));
				employeeVO.setEmployee_password(rs.getString("employee_password"));
				employeeVO.setEmployee_no(rs.getString("employee_no"));
				System.out.println();
			}

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
	public List<String> getEmployeeByAuthority(String employee_no) {
		List<String> list = new ArrayList<String>();
		AuthorityVO String = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Eemployee_ByAuthority);
			pstmt.setString(1, employee_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
//				authorityVO = new AuthorityVO();
				String fun_no;
				fun_no=rs.getString("function_no");
//				authorityVO.setFunction_no(rs.getString("function_no"));

				list.add(fun_no); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insertWithAutjority(EmployeeVO employeeVO, List<AuthorityVO> insertAuthoritylist) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);

			//先新增員工
			String cols[] = {"employee_no"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, employeeVO.getEmployee_name());
			pstmt.setString(2, employeeVO.getEmployee_title());		
			pstmt.setString(3, employeeVO.getEmployee_account());
			pstmt.setString(7, employeeVO.getEmployee_password());
			pstmt.setString(4, employeeVO.getEmployee_mail());
			pstmt.setString(5, employeeVO.getEmployee_cellphone());
			pstmt.setBytes(6, employeeVO.getEmployee_photo());	
			pstmt.executeUpdate();

			String next_employeeNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_employeeNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_employeeNo + "(剛新增成功的員工編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 在同時新增權限
			AuthorityJNDIDAO dao = new AuthorityJNDIDAO(); 
			System.out.println("list.size()-A="+insertAuthoritylist.size());
			for (AuthorityVO authority :insertAuthoritylist) {
				authority.setEmployee_no(new String(next_employeeNo));
				dao.insert2(authority, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + insertAuthoritylist.size());
			System.out.println("新增員工編號" + next_employeeNo + "時,共有權限" + insertAuthoritylist.size() + "個被同時新增");
			
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-employee");
					con.rollback();
				} catch (SQLException excep){
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public String findAuthority(String employee_no, String function_no) {
		
		String functionNo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTH_STMT);

			pstmt.setString(1, employee_no);
			pstmt.setString(2, function_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
			
				functionNo=rs.getString("function_no");
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
		return functionNo;
		
	}

}
