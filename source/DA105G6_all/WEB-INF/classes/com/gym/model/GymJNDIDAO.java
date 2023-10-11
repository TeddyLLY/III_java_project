package com.gym.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GymJNDIDAO implements GymDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO gym (gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_content) VALUES ('G'||LPAD(TO_CHAR(GYM_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_content FROM gym order by gym_no";
	private static final String GET_ONE_STMT = "SELECT gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_content FROM gym where gym_no = ?";
	private static final String DELETE = "DELETE FROM gym where gym_no = ?";
	private static final String UPDATE = "UPDATE gym set gym_name=?, gym_latitude=?, gym_longitude=?, gym_address=?, gym_content=? where gym_no = ?";

	@Override
	public void insert(GymVO gymVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gymVO.getGym_name());
			pstmt.setString(2, gymVO.getGym_latitude());
			pstmt.setString(3, gymVO.getGym_longitude());
			pstmt.setString(4, gymVO.getGym_address());
			pstmt.setString(5, gymVO.getGym_content());

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
	public void update(GymVO gymVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gymVO.getGym_name());
			pstmt.setString(2, gymVO.getGym_latitude());
			pstmt.setString(3, gymVO.getGym_longitude());
			pstmt.setString(4, gymVO.getGym_address());
			pstmt.setString(5, gymVO.getGym_content());
			pstmt.setString(6, gymVO.getGym_no());

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
	public void delete(String gym_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gym_no);

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
	public GymVO findByPrimaryKey(String gym_no) {
		GymVO gymVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gym_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				gymVO = new GymVO();
				gymVO.setGym_no(rs.getString("gym_no"));
				gymVO.setGym_name(rs.getString("gym_name"));
				gymVO.setGym_latitude(rs.getString("gym_latitude"));
				gymVO.setGym_longitude(rs.getString("gym_longitude"));
				gymVO.setGym_address(rs.getString("gym_address"));
				gymVO.setGym_content(rs.getString("gym_content"));

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
		return gymVO;

	}

	@Override
	public List<GymVO> getAll() {
		List<GymVO> list = new ArrayList<GymVO>();
		GymVO gymVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				gymVO = new GymVO();
				gymVO.setGym_no(rs.getString("gym_no"));
				gymVO.setGym_name(rs.getString("gym_name"));
				gymVO.setGym_latitude(rs.getString("gym_latitude"));
				gymVO.setGym_longitude(rs.getString("gym_longitude"));
				gymVO.setGym_address(rs.getString("gym_address"));
				gymVO.setGym_content(rs.getString("gym_content"));
				list.add(gymVO); // Store the row in the list
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

}
