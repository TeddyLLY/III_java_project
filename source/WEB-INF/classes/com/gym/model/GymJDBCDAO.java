//package com.gym.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class GymJDBCDAO implements GymDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA105G6";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO gym (gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_status,gym_now_people,gym_total_people,gym_content) VALUES ('G'||LPAD(TO_CHAR(GYM_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_status,gym_now_people,gym_total_people,gym_content FROM gym order by gym_no";
//	private static final String GET_ONE_STMT = "SELECT gym_no,gym_name,gym_latitude,gym_longitude,gym_address,gym_status,gym_now_people,gym_total_people,gym_content FROM gym where gym_no = ?";
//	private static final String DELETE = "DELETE FROM gym where gym_no = ?";
//	private static final String UPDATE = "UPDATE gym set gym_name=?, gym_latitude=?, gym_longitude=?, gym_address=?, gym_status=?, gym_now_people=?, gym_total_people=?, gym_content=? where gym_no = ?";
//
//	// 新增
//	@Override
//	public void insert(GymVO gymVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, gymVO.getGym_name());
//			pstmt.setString(2, gymVO.getGym_latitude());
//			pstmt.setString(3, gymVO.getGym_longitude());
//			pstmt.setString(4, gymVO.getGym_address());
//			pstmt.setInt(5, gymVO.getGym_status());
//			pstmt.setInt(6, gymVO.getGym_now_people());
//			pstmt.setInt(7, gymVO.getGym_total_people());
//			pstmt.setString(8, gymVO.getGym_content());
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
//	public void update(GymVO gymVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, gymVO.getGym_name());
//			pstmt.setString(2, gymVO.getGym_latitude());
//			pstmt.setString(3, gymVO.getGym_longitude());
//			pstmt.setString(4, gymVO.getGym_address());
//			pstmt.setInt(5, gymVO.getGym_status());
//			pstmt.setInt(6, gymVO.getGym_now_people());
//			pstmt.setInt(7, gymVO.getGym_total_people());
//			pstmt.setString(8, gymVO.getGym_content());
//			pstmt.setString(9, gymVO.getGym_no());
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
//	public void delete(String gym_no) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, gym_no);
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
//	public GymVO findByPrimaryKey(String gym_no) {
//		GymVO gymVO = null;
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
//			pstmt.setString(1, gym_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				gymVO = new GymVO();
//				gymVO.setGym_no(rs.getString("gym_no"));
//				gymVO.setGym_name(rs.getString("gym_name"));
//				gymVO.setGym_latitude(rs.getString("gym_latitude"));
//				gymVO.setGym_longitude(rs.getString("gym_longitude"));
//				gymVO.setGym_address(rs.getString("gym_address"));
//				gymVO.setGym_status(rs.getInt("gym_status"));
//				gymVO.setGym_now_people(rs.getInt("gym_now_people"));
//				gymVO.setGym_total_people(rs.getInt("gym_total_people"));
//				gymVO.setGym_content(rs.getString("gym_content"));
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
//		return gymVO;
//
//	}
//
//	@Override
//	public List<GymVO> getAll() {
//		List<GymVO> list = new ArrayList<GymVO>();
//		GymVO gymVO = null;
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
//				gymVO = new GymVO();
//				gymVO.setGym_no(rs.getString("gym_no"));
//				gymVO.setGym_name(rs.getString("gym_name"));
//				gymVO.setGym_latitude(rs.getString("gym_latitude"));
//				gymVO.setGym_longitude(rs.getString("gym_longitude"));
//				gymVO.setGym_address(rs.getString("gym_address"));
//				gymVO.setGym_status(rs.getInt("gym_status"));
//				gymVO.setGym_now_people(rs.getInt("gym_now_people"));
//				gymVO.setGym_total_people(rs.getInt("gym_total_people"));
//				gymVO.setGym_content(rs.getString("gym_content"));
//				list.add(gymVO); // Store the row in the list
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
//		GymJDBCDAO dao = new GymJDBCDAO();
//
//		// 新增
////		GymVO gymVO1 = new GymVO();
////		
////		gymVO1.setGym_name("臺北市松山運動中心");
////		gymVO1.setGym_latitude("25.048496");
////		gymVO1.setGym_longitude("121.550444");
////		gymVO1.setGym_address("台北市松山區敦化北路1號");
////		gymVO1.setGym_status(0);
////		gymVO1.setGym_now_people(20);
////		gymVO1.setGym_total_people(120);
////		gymVO1.setGym_content("現代人生活模式的改變，生理機能退化，並不是只會發生在老年人身上，反而現今更多的年輕人，因為運動量的不足，導致肌肉量的不足，引起許多的慢性疾病，適當的運動除了可以增加心肺功能，增加肌力，還可讓關節液潤滑關節，提供軟骨所需的營養，保護關節，因此在團體課程中，有各式各樣的課程，讓身體藉由規律的訓練，達到身體心肺、肌力、體能的改善。");
////		dao.insert(gymVO1);
//
//		// 修改
////		GymVO gymVO1 = new GymVO();
////		gymVO1.setGym_no("G011");
////		gymVO1.setGym_name("臺北市松山運動中心");
////		gymVO1.setGym_latitude("25.048496");
////		gymVO1.setGym_longitude("121.550444");
////		gymVO1.setGym_address("台北市松山區敦化北路1號");
////		gymVO1.setGym_status(1);
////		gymVO1.setGym_now_people(0);
////		gymVO1.setGym_total_people(120);
////		gymVO1.setGym_content("現代人生活模式的改變，生理機能退化，並不是只會發生在老年人身上，反而現今更多的年輕人，因為運動量的不足，導致肌肉量的不足，引起許多的慢性疾病，適當的運動除了可以增加心肺功能，增加肌力，還可讓關節液潤滑關節，提供軟骨所需的營養，保護關節，因此在團體課程中，有各式各樣的課程，讓身體藉由規律的訓練，達到身體心肺、肌力、體能的改善。");
////		dao.update(gymVO1);
//
//		// 刪除
////		dao.delete("G011");
//		// 單筆查詢
//		GymVO gymVO1 = dao.findByPrimaryKey("G010");
//		System.out.println(gymVO1.getGym_no() + ",");
//		System.out.println(gymVO1.getGym_name() + ",");
//		System.out.println(gymVO1.getGym_latitude() + ",");
//		System.out.println(gymVO1.getGym_longitude() + ",");
//		System.out.println(gymVO1.getGym_address() + ",");
//		System.out.println(gymVO1.getGym_status() + ",");
//		System.out.println(gymVO1.getGym_now_people() + ",");
//		System.out.println(gymVO1.getGym_total_people() + ",");
//		System.out.println(gymVO1.getGym_content());
//
//		// 多筆查詢
//		List<GymVO> list = dao.getAll();
//		for (GymVO gym : list) {
//			System.out.print(gym.getGym_no() + ",");
//			System.out.print(gym.getGym_name() + ",");
//			System.out.print(gym.getGym_latitude() + ",");
//			System.out.print(gym.getGym_longitude() + ",");
//			System.out.print(gym.getGym_address() + ",");
//			System.out.print(gym.getGym_status() + ",");
//			System.out.print(gym.getGym_now_people() + ",");
//			System.out.print(gym.getGym_total_people() + ",");
//			System.out.print(gym.getGym_content());
//			System.out.println();
//		}
//
//	}
//}
