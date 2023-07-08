package com.appointment_order_detail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class AppointmentOrderDetailJNDIDAO implements AppointmentOrderDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO appointment_order_detail (appointment_order_detail_no,appointment_order_no,student_status,appointment_date)"
			+ " VALUES('APD'||LPAD(TO_CHAR(AP_ORDER_DETAIL_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT appointment_order_detail_no,appointment_order_no,student_status,appointment_date "
			+ "FROM appointment_order_detail order by appointment_order_detail_no";
	private static final String GET_ONE_ALL_STMT = "SELECT appointment_order_detail_no,appointment_order_no,student_status,appointment_date"
			+ " FROM appointment_order_detail where appointment_order_no = ? order by appointment_order_no";

	private static final String GET_ONE_STMT = "SELECT appointment_order_detail_no,appointment_order_no,student_status,appointment_date"
			+ " FROM appointment_order_detail where appointment_order_detail_no = ? ";

	private static final String DELETE = "DELETE FROM appointment_order_detail where appointment_order_detail_no = ?";
	private static final String DELETE2 = "DELETE FROM appointment_order_detail where appointment_order_no = ?";
	private static final String DELETE3 = 
			"DELETE FROM appointment_order_detail WHERE appointment_order_no = ? and appointment_date = ?";	
	
	private static final String UPDATE = "UPDATE appointment_order_detail set appointment_order_no = ?,student_status = ?,appointment_date = ?"
			+ " where appointment_order_detail_no = ?";
	private static final String UPDATE2 = "UPDATE appointment_order_detail set student_status = ? where appointment_order_detail_no = ?";
	@Override
	public void insert(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointmentOrderDetailVO.getAppointmentOrderNo());
			pstmt.setInt(2, appointmentOrderDetailVO.getStudentStatus());
			pstmt.setDate(3, appointmentOrderDetailVO.getAppointmentDate());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, appointmentOrderDetailVO.getAppointmentOrderNo());
			pstmt.setInt(2, appointmentOrderDetailVO.getStudentStatus());
			pstmt.setDate(3, appointmentOrderDetailVO.getAppointmentDate());
			pstmt.setString(4, appointmentOrderDetailVO.getAppointmentOrderDetailNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String appointmentOrderDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, appointmentOrderDetailNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);
			pstmt.setString(1, appointmentOrderNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AppointmentOrderDetailVO findByPrimaryKey(String appointmentOrderDetailNo) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, appointmentOrderDetailNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("appointment_date"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("appointment_date"));
				list.add(appointmentOrderDetailVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		List<AppointmentOrderDetailVO> apodlist = new ArrayList<AppointmentOrderDetailVO>();
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, appointmentOrderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("appointment_date"));
				apodlist.add(appointmentOrderDetailVO);
			}

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

		return apodlist;
	}

	public void insert2(AppointmentOrderDetailVO aptmtDetail, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, aptmtDetail.getAppointmentOrderNo());
			pstmt.setInt(2, 0);//上課狀態:預設"0":未報到
			pstmt.setDate(3, aptmtDetail.getAppointmentDate());
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
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
		}
		
	}

	@Override
	public void updateStatus(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setInt(1, appointmentOrderDetailVO.getStudentStatus());
			pstmt.setString(2, appointmentOrderDetailVO.getAppointmentOrderDetailNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete3(AppointmentOrderDetailVO appointmentOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE3);
			pstmt.setString(1, appointmentOrderDetailVO.getAppointmentOrderDetailNo());
			pstmt.setDate(2, appointmentOrderDetailVO.getAppointmentDate());			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AppointmentOrderDetailVO findById(String appointmentOrderDetailNo) {
		AppointmentOrderDetailVO appointmentOrderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, appointmentOrderDetailNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				appointmentOrderDetailVO = new AppointmentOrderDetailVO();
				appointmentOrderDetailVO.setAppointmentOrderDetailNo(rs.getString("appointment_order_detail_no"));
				appointmentOrderDetailVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentOrderDetailVO.setStudentStatus(rs.getInt("student_status"));
				appointmentOrderDetailVO.setAppointmentDate(rs.getDate("appointment_date"));

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	

}
