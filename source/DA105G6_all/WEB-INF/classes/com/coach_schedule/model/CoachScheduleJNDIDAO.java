package com.coach_schedule.model;

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

public class CoachScheduleJNDIDAO implements CoachScheduleDAO_interface {
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
		"INSERT INTO COACH_SCHEDULE (COACH_SCHEDULE_NO, COACH_NO, COACH_DATE) VALUES ('CS'||LPAD(TO_CHAR(COACH_SCHEDULE_NO_SEQ.NEXTVAL),3,'0'), ?, ?)";
	private static final String GET_ALL_STMT = 			    
		"SELECT COACH_SCHEDULE_NO, COACH_NO, TO_CHAR(COACH_DATE ,'yyyy-mm-dd') COACH_DATE FROM COACH_SCHEDULE ORDER BY COACH_SCHEDULE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT COACH_SCHEDULE_NO, COACH_NO, TO_CHAR(COACH_DATE ,'yyyy-mm-dd') COACH_DATE FROM COACH_SCHEDULE WHERE COACH_SCHEDULE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM COACH_SCHEDULE WHERE COACH_SCHEDULE_NO = ?";
	private static final String UPDATE = 
		"UPDATE COACH_SCHEDULE SET COACH_NO=?, COACH_DATE=? WHERE COACH_SCHEDULE_NO = ?";

	private static final String GET_COACH_STMT = 
			"SELECT COACH_SCHEDULE_NO, COACH_NO, TO_CHAR(COACH_DATE ,'yyyy-mm-dd') COACH_DATE FROM COACH_SCHEDULE WHERE COACH_NO = ?";	
	private static final String DELETE2 = 
			"DELETE FROM COACH_SCHEDULE WHERE COACH_NO = ? and COACH_DATE = ?";	
	@Override
	public void insert(CoachScheduleVO coachscheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, coachscheduleVO.getCoachNo());
			pstmt.setDate(2, coachscheduleVO.getCoachDate());
			
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
	public void update(CoachScheduleVO coachscheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, coachscheduleVO.getCoachNo());
			pstmt.setDate(2, coachscheduleVO.getCoachDate());
			pstmt.setString(3, coachscheduleVO.getCoachScheduleNo());
											
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
	public void delete(String coachScheduleNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coachScheduleNo);

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
	public void delete2(CoachScheduleVO coachscheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);

			pstmt.setString(1, coachscheduleVO.getCoachNo());
			pstmt.setDate(2, coachscheduleVO.getCoachDate());
			
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
	public CoachScheduleVO findByPrimaryKey(String coachScheduleNo) {
		CoachScheduleVO coachscheduleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, coachScheduleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachscheduleVO = new CoachScheduleVO();
				coachscheduleVO.setCoachScheduleNo(rs.getString("coach_schedule_no"));
				coachscheduleVO.setCoachNo(rs.getString("coach_no"));
				coachscheduleVO.setCoachDate(rs.getDate("coach_date"));
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
		return coachscheduleVO;
	}
	
	@Override
	public List<CoachScheduleVO> getAll() {
		List<CoachScheduleVO> list = new ArrayList<CoachScheduleVO>();
		CoachScheduleVO coachscheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachscheduleVO = new CoachScheduleVO();
				coachscheduleVO.setCoachScheduleNo(rs.getString("coach_schedule_no"));			
				coachscheduleVO.setCoachNo(rs.getString("coach_no"));
				coachscheduleVO.setCoachDate(rs.getDate("coach_date"));
				list.add(coachscheduleVO); // Store the row in the list
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
	public List<CoachScheduleVO> getCoachSchedule(String coachNo) {
		List<CoachScheduleVO> list = new ArrayList<CoachScheduleVO>();
		CoachScheduleVO coachscheduleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_STMT);
			pstmt.setString(1, coachNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				coachscheduleVO = new CoachScheduleVO();
				coachscheduleVO.setCoachScheduleNo(rs.getString("coach_schedule_no"));			
				coachscheduleVO.setCoachNo(rs.getString("coach_no"));
				coachscheduleVO.setCoachDate(rs.getDate("coach_date"));
				list.add(coachscheduleVO); // Store the row in the list
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
