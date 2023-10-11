package com.appointment_order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson_order.model.LessonOrderJDBCDAO;
import com.lesson_order.model.LessonOrderVO;

public class AppointmentOrderDetailJDBCDAO implements AppointmentOrderDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO appointment_order_detail (appointment_order_detail_no,appointment_order_no,student_status,APPOINTMENT_DATE)"
		+ " VALUES ('APD'||LPAD(TO_CHAR(AP_ORDER_DETAIL_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT appointment_order_detail_no,appointment_order_no,student_status,APPOINTMENT_DATE "
		+ "FROM appointment_order_detail order by appointment_order_detail_no";
	private static final String GET_ONE_ALL_STMT = 
		"SELECT appointment_order_detail_no,appointment_order_no,student_status,APPOINTMENT_DATE"
		+ " FROM appointment_order_detail where appointment_order_no = ? order by appointment_order_no";
	
	private static final String GET_ONE_STMT = 
		"SELECT appointment_order_detail_no,appointment_order_no,student_status,APPOINTMENT_DATE"
		+ " FROM appointment_order_detail where appointment_order_detail_no = ? ";
	
	private static final String DELETE = 
		"DELETE FROM appointment_order_detail where appointment_order_detail_no = ?"; 
	private static final String DELETE2 = 
			"DELETE FROM appointment_order_detail where appointment_order_no = ?"; 
	
	private static final String UPDATE = 
		"UPDATE appointment_order_detail set appointment_order_no = ?,student_status = ?,APPOINTMENT_DATE = ?"
		+ " where appointment_order_detail_no = ?";
	
	@Override
	public void insert(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointmentOrderDetailVO.getAppointmentOrderNo());
			pstmt.setInt(2, appointmentOrderDetailVO.getStudentStatus());
			pstmt.setDate(3, appointmentOrderDetailVO.getAppointmentDate());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void update(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, appointmentOrderDetailVO.getAppointmentOrderNo());
			pstmt.setInt(2, appointmentOrderDetailVO.getStudentStatus());
			pstmt.setDate(3, appointmentOrderDetailVO.getAppointmentDate());
			pstmt.setString(4, appointmentOrderDetailVO.getAppointmentOrderDetailNo());			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public void delete(String appointmentOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, appointmentOrderNo);
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
	public void delete2(String appointmentOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE2);
			pstmt.setString(1, appointmentOrderNo);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

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
	public AppointmentOrderDetailVO findByPrimaryKey(String appointmentOrderNo) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, appointmentOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("APPOINTMENT_DATE"));



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
		return appointmentOrderDetailVO;
	}

	@Override
	public List<AppointmentOrderDetailVO> getAll() {
		List<AppointmentOrderDetailVO> list = new ArrayList<AppointmentOrderDetailVO>();
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Domain objects
				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("APPOINTMENT_DATE"));
				list.add(appointmentOrderDetailVO);
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

	@Override
	public List<AppointmentOrderDetailVO> getOneAll(String appointmentOrderNo) {
		List<AppointmentOrderDetailVO> lmdlist = new ArrayList<AppointmentOrderDetailVO>();
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, appointmentOrderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Domain objects
				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("APPOINTMENT_DATE"));
				lmdlist.add(appointmentOrderDetailVO);
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

		return lmdlist;
	}

	@Override
	public void updateStatus(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete3(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AppointmentOrderDetailVO findById(String appointmentOrderDetailNo) {
		// TODO Auto-generated method stub
		return null;
	}

		

}
