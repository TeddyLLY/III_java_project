package com.live.model;


import java.sql.*;
import java.util.*;


public class LiveJDBCDAO implements LiveDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO live (room_no,live_notice,live_time,live_title,room_imformation,coach_no) VALUES (('L'||LPAD(TO_CHAR(ROOM_NO_SEQ.NEXTVAL),3,'0')), ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT room_no,live_notice,live_time,live_title,room_imformation,coach_no FROM live order by room_no";
	private static final String GET_ONE_STMT = 
		"SELECT room_no,live_notice,live_time,live_title,room_imformation,coach_no FROM live where room_no = ?";
	private static final String DELETE = 
		"DELETE FROM live where room_no = ?";
	private static final String UPDATE = 
		"UPDATE live set live_notice=?, live_time=?, live_title=?, room_imformation=?,coach_no=? where room_no = ?";

	@Override
	public void insert(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, liveVO.getLive_notice());
			pstmt.setDate(2, liveVO.getLive_time());
			pstmt.setString(3, liveVO.getLive_title());
			pstmt.setString(4, liveVO.getLive_imformation());
			pstmt.setString(5, liveVO.getCoach_no());

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
	public void update(LiveVO liveVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, liveVO.getLive_notice());
			pstmt.setDate(2, liveVO.getLive_time());
			pstmt.setString(3, liveVO.getLive_title());
			pstmt.setString(4, liveVO.getLive_imformation());
			pstmt.setString(5, liveVO.getCoach_no());
			pstmt.setString(6, liveVO.getLive_no());

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
	public void delete(String live_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, live_no);

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
	public LiveVO findByPrimaryKey(String live_no) {

		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, live_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("room_no"));
				liveVO.setLive_notice(rs.getDate("live_notice"));
				liveVO.setLive_time(rs.getDate("live_time"));
				liveVO.setLive_title(rs.getString("live_title"));
				liveVO.setLive_imformation(rs.getString("room_imformation"));
				liveVO.setCoach_no(rs.getString("coach_no"));
				
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("room_no"));
				liveVO.setLive_notice(rs.getDate("live_notice"));
				liveVO.setLive_time(rs.getDate("live_time"));
				liveVO.setLive_title(rs.getString("live_title"));
				liveVO.setLive_imformation(rs.getString("room_imformation"));
				liveVO.setCoach_no(rs.getString("coach_no"));
				list.add(liveVO); // Store the row in the list
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

	public static void main(String[] args) {

		LiveJDBCDAO dao = new LiveJDBCDAO();

		// 新增
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_notice(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_title("123");
//		liveVO.setLive_imformation("123");
//		liveVO.setCoach_no("C003");
//		
//		dao.insert(liveVO);
//
//		// 修改
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_no("L002");
//		liveVO.setLive_notice(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_time(new java.sql.Date(new GregorianCalendar(2020,01,30).getTime().getTime()));
//		liveVO.setLive_title("234");
//		liveVO.setRoom_imformation("234");
//		liveVO.setCoach_no("C001");
//		
//		dao.update(liveVO);
//
//		// 刪除
//		dao.delete("L002");
//
//		// 查詢
//		LiveVO liveVO = dao.findByPrimaryKey("L001");
//		System.out.print(liveVO.getLive_no() + ",");
//		System.out.print(liveVO.getLive_notice() + ",");
//		System.out.print(liveVO.getLive_time() + ",");
//		System.out.print(liveVO.getLive_title() + ",");
//		System.out.print(liveVO.getRoom_imformation() + ",");
//		System.out.print(liveVO.getCoach_no() + ",");
//		
//		System.out.println("---------------------");
//
		// 查詢
//		List<LiveVO> list = dao.getAll();
//		for (LiveVO aliveVO : list) {
//			System.out.print(aliveVO.getLive_no() + ",");
//			System.out.print(aliveVO.getLive_notice() + ",");
//			System.out.print(aliveVO.getLive_time() + ",");
//			System.out.print(aliveVO.getLive_title() + ",");
//			System.out.print(aliveVO.getRoom_imformation() + ",");
//			System.out.print(aliveVO.getCoach_no() + ",");
//			System.out.println();
//		}
	}
}
