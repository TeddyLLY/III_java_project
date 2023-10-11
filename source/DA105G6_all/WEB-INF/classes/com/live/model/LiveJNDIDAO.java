package com.live.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LiveJNDIDAO implements LiveDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO live (live_no,live_notice,live_time,live_title,live_imformation,coach_no) VALUES (('L'||LPAD(TO_CHAR(live_NO_SEQ.NEXTVAL),3,'0')), ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT live_no,live_notice,live_time,live_title,live_imformation,coach_no FROM live order by live_no";
	private static final String GET_ONE_STMT = "SELECT live_no,live_notice,live_time,live_title,live_imformation,coach_no FROM live where live_no = ?";
	private static final String DELETE = "DELETE FROM live where live_no = ?";
	private static final String UPDATE = "UPDATE live set live_notice=?, live_time=?, live_title=?, live_imformation=?,coach_no=? where live_no = ?";

	@Override
	public void insert(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, liveVO.getLive_notice());
			pstmt.setDate(2, liveVO.getLive_time());
			pstmt.setString(3, liveVO.getLive_title());
			pstmt.setString(4, liveVO.getLive_imformation());
			pstmt.setString(5, liveVO.getCoach_no());

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
	public void update(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, liveVO.getLive_notice());
			pstmt.setDate(2, liveVO.getLive_time());
			pstmt.setString(3, liveVO.getLive_title());
			pstmt.setString(4, liveVO.getLive_imformation());
			pstmt.setString(5, liveVO.getCoach_no());
			pstmt.setString(6, liveVO.getLive_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String live_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, live_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public LiveVO findByPrimaryKey(String live_no) {

		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, live_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("live_no"));
				liveVO.setLive_notice(rs.getDate("live_notice"));
				liveVO.setLive_time(rs.getDate("live_time"));
				liveVO.setLive_title(rs.getString("live_title"));
				liveVO.setLive_imformation(rs.getString("live_imformation"));
				liveVO.setCoach_no(rs.getString("coach_no"));

			}

			// Handle any driver errors
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
		return liveVO;
	}

	@Override
	public List<LiveVO> getAll() {
		List<LiveVO> list = new ArrayList<LiveVO>();
		LiveVO liveVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("live_no"));
				liveVO.setLive_notice(rs.getDate("live_notice"));
				liveVO.setLive_time(rs.getDate("live_time"));
				liveVO.setLive_title(rs.getString("live_title"));
				liveVO.setLive_imformation(rs.getString("live_imformation"));
				liveVO.setCoach_no(rs.getString("coach_no"));
				list.add(liveVO); // Store the row in the list
			}

			// Handle any driver errors
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
