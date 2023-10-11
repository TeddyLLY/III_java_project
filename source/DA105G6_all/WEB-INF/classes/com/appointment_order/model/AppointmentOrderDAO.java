package com.appointment_order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.appointment_order_detail.model.AppointmentOrderDetailDAO;
import com.appointment_order_detail.model.AppointmentOrderDetailVO;


public class AppointmentOrderDAO implements AppointmentOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx= new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO APPOINTMENT_ORDER (APPOINTMENT_ORDER_NO, MEMBER_NO, COACH_NO, APPOINTMENT_PRICE, ORDER_TIME, APPOINTMENT_STATUS, APPOINTMENT_LOCATION, APPOINTMENT_DEMAND) VALUES ('AP'||LPAD(TO_CHAR(APPOINTMENT_ORDER_NO_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String MEM_INSERT_STMT = 
			"INSERT INTO APPOINTMENT_ORDER (APPOINTMENT_ORDER_NO, MEMBER_NO, COACH_NO, ORDER_TIME, APPOINTMENT_STATUS, APPOINTMENT_LOCATION, APPOINTMENT_DEMAND) VALUES ('AP'||LPAD(TO_CHAR(APPOINTMENT_ORDER_NO_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = 			    
		"SELECT APPOINTMENT_ORDER_NO,MEMBER_NO,COACH_NO,APPOINTMENT_PRICE,TO_char(ORDER_TIME,'YYYY-MM-DD HH24:MI:SS') ORDER_TIME,APPOINTMENT_STATUS,APPOINTMENT_LOCATION,APPOINTMENT_DEMAND FROM APPOINTMENT_ORDER ORDER BY APPOINTMENT_ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT APPOINTMENT_ORDER_NO,MEMBER_NO,COACH_NO,APPOINTMENT_PRICE,TO_char(ORDER_TIME,'YYYY-MM-DD HH24:MI:SS') ORDER_TIME,APPOINTMENT_STATUS,APPOINTMENT_LOCATION,APPOINTMENT_DEMAND FROM APPOINTMENT_ORDER WHERE APPOINTMENT_ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM APPOINTMENT_ORDER WHERE APPOINTMENT_ORDER_NO = ?";
	private static final String UPDATE = 
		"UPDATE APPOINTMENT_ORDER SET MEMBER_NO=?, COACH_NO=?, APPOINTMENT_PRICE=?, ORDER_TIME=?, APPOINTMENT_STATUS=?, APPOINTMENT_LOCATION=?, APPOINTMENT_DEMAND=? WHERE APPOINTMENT_ORDER_NO = ?";

	private static final String GET_MEM_ALL_STMT = 
			"SELECT * FROM APPOINTMENT_ORDER WHERE MEMBER_NO=? ORDER BY ORDER_TIME DESC";
	private static final String GET_COACH_ALL_STMT = 
			"SELECT * FROM APPOINTMENT_ORDER WHERE COACH_NO=? ORDER BY ORDER_TIME DESC";			

	private static final String UPDATEPRICE = 
			"UPDATE APPOINTMENT_ORDER SET APPOINTMENT_PRICE=?,APPOINTMENT_STATUS=? WHERE APPOINTMENT_ORDER_NO = ?";
	private static final String GET_COACH_CERTAIN_STMT = 
			"SELECT * FROM APPOINTMENT_ORDER WHERE COACH_NO=? AND APPOINTMENT_STATUS <= 1";	//狀態為已完成或進行中		
	private static final String GET_MEMBER_CERTAIN_STMT = 
			"SELECT * FROM APPOINTMENT_ORDER WHERE MEMBER_NO=? AND APPOINTMENT_STATUS <= 1";	//狀態為已完成或進行中		

	
	@Override
	public void insert2(AppointmentOrderVO appointmentOrderVO, List<AppointmentOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			String cols[] = {"appointment_order_no"}; //自增主鍵
			pstmt = con.prepareStatement(MEM_INSERT_STMT , cols);		

			pstmt.setString(1, appointmentOrderVO.getMemberNo());
			pstmt.setString(2, appointmentOrderVO.getCoachNo());
			pstmt.setTimestamp(3, appointmentOrderVO.getOrderTime());
			pstmt.setInt(4, appointmentOrderVO.getAppointmentStatus());
			pstmt.setString(5, appointmentOrderVO.getAppointmentLocation());
			pstmt.setString(6, appointmentOrderVO.getAppointmentDemand());
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			String next_appointmentOrderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_appointmentOrderNo = rs.getString(1);
			} else {
			}
			rs.close();
			// 再同時新增明細
			AppointmentOrderDetailDAO dao = new AppointmentOrderDetailDAO();
			for (AppointmentOrderDetailVO aDetail : list) {
				aDetail.setAppointmentOrderNo(new String(next_appointmentOrderNo)) ;
				dao.insert2(aDetail,con);
			}			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-appointmentorder"+se);
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
	public void insert(AppointmentOrderVO appointmentorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, appointmentorderVO.getMemberNo());
			pstmt.setString(2, appointmentorderVO.getCoachNo());
			pstmt.setInt(3, appointmentorderVO.getAppointmentPrice());
			pstmt.setTimestamp(4, appointmentorderVO.getOrderTime());
			pstmt.setInt(5, appointmentorderVO.getAppointmentStatus());
			pstmt.setString(6, appointmentorderVO.getAppointmentLocation());
			pstmt.setString(7, appointmentorderVO.getAppointmentDemand());
			
			pstmt.executeUpdate();

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
	public void update(AppointmentOrderVO appointmentorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, appointmentorderVO.getMemberNo());
			pstmt.setString(2, appointmentorderVO.getCoachNo());
			pstmt.setInt(3, appointmentorderVO.getAppointmentPrice());
			pstmt.setTimestamp(4, appointmentorderVO.getOrderTime());
			pstmt.setInt(5, appointmentorderVO.getAppointmentStatus());
			pstmt.setString(6, appointmentorderVO.getAppointmentLocation());
			pstmt.setString(7, appointmentorderVO.getAppointmentDemand());
			pstmt.setString(8, appointmentorderVO.getAppointmentOrderNo());
			
			pstmt.executeUpdate();

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
	public void updatePrice(AppointmentOrderVO appointmentOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPRICE);

			pstmt.setInt(1, appointmentOrderVO.getAppointmentPrice());
			pstmt.setInt(2,appointmentOrderVO.getAppointmentStatus());
			pstmt.setString(3,appointmentOrderVO.getAppointmentOrderNo());		
			pstmt.executeUpdate();

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
	public void delete(String appointmentOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, appointmentOrderNo);

			pstmt.executeUpdate();

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
	public AppointmentOrderVO findByPrimaryKey(String appointmentOrderNo) {
		AppointmentOrderVO appointmentorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, appointmentOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
			}

			// Handle any SQL errors
		}  catch (SQLException se) {
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
		return appointmentorderVO;
	}
	
	@Override
	public List<AppointmentOrderVO> getAll() {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	public List<AppointmentOrderVO> getMemAll(String memberNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ALL_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	public List<AppointmentOrderVO> getCoachAll(String coachNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_ALL_STMT);
			pstmt.setString(1, coachNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	public List<AppointmentOrderVO> getCoachCertainDate(String coachNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_CERTAIN_STMT);
			pstmt.setString(1, coachNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	public List<AppointmentOrderVO> getMemberCertainDate(String memberNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEMBER_CERTAIN_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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

	//Android-------------------------------------
	@Override
	public List<AppointmentOrderVO> getMemAllAndroid(String memberNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MEM_ALL_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	public List<AppointmentOrderVO> getCoaAllAndroid(String coachNo) {
		List<AppointmentOrderVO> list = new ArrayList<AppointmentOrderVO>();
		AppointmentOrderVO appointmentorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_ALL_STMT);
			pstmt.setString(1, coachNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appointmentorderVO = new AppointmentOrderVO();
				appointmentorderVO.setAppointmentOrderNo(rs.getString("appointment_order_no"));
				appointmentorderVO.setMemberNo(rs.getString("member_no"));
				appointmentorderVO.setCoachNo(rs.getString("coach_no"));
				appointmentorderVO.setAppointmentPrice(rs.getInt("appointment_price"));
				appointmentorderVO.setOrderTime(rs.getTimestamp("order_time"));
				appointmentorderVO.setAppointmentStatus(rs.getInt("appointment_status"));
				appointmentorderVO.setAppointmentLocation(rs.getString("appointment_location"));
				appointmentorderVO.setAppointmentDemand(rs.getString("appointment_demand"));
				list.add(appointmentorderVO); // Store the row in the list
			}

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
	
}
