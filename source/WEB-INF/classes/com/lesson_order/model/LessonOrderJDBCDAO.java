package com.lesson_order.model;
import java.sql.*;
import java.util.*;

import com.lesson_detail.model.LessonDetailVO;

public class LessonOrderJDBCDAO implements LessonOrderDAO_interface {	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO lesson_order (lesson_order_no,member_no,lesson_no,lesson_price,date_acquisition,"
		+ "lesson_status) VALUES ('LO'||LPAD(TO_CHAR(LESSON_ORDER_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT lesson_order_no,member_no,lesson_no,lesson_price,date_acquisition,lesson_status "
		+ "FROM lesson_order order by lesson_order_no";
	private static final String GET_ONE_ALL_STMT = 
		"SELECT lesson_order_no,member_no,lesson_no,lesson_price,date_acquisition,lesson_status"
		+ " FROM lesson_order where member_no = ? order by member_no";
	
	private static final String GET_ONE_STMT = 
		"SELECT lesson_order_no,member_no,lesson_no,lesson_price,date_acquisition,lesson_status"
		+ " FROM lesson_order where lesson_order_no = ? ";
	
	private static final String DELETE = 
		"DELETE FROM lesson_order where lesson_order_no = ?";
	private static final String DELETE2_LO = 
			"DELETE FROM lesson_order where lesson_order_no = ?";
	private static final String DELETE2_LMD = 
			"DELETE FROM member_lesson_detail where lesson_order_no = ?";
	
	private static final String UPDATE = 
		"UPDATE lesson_order set member_no = ?,lesson_no = ?,lesson_price = ?,date_acquisition = ?,lesson_status = ?"
		+ " where lesson_order_no = ?";
	

	@Override
	public void insert(LessonOrderVO lessonOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonOrderVO.getMemberNo());
			pstmt.setString(2, lessonOrderVO.getLessonNo());
			pstmt.setInt(3, lessonOrderVO.getLessonPrice());
			pstmt.setTimestamp(4, lessonOrderVO.getDateAcquisition());
			pstmt.setInt(5, lessonOrderVO.getLessonStatus());

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
	public void update(LessonOrderVO lessonOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, lessonOrderVO.getMemberNo()); 
			pstmt.setString(2, lessonOrderVO.getLessonNo());
			pstmt.setInt(3, lessonOrderVO.getLessonPrice());
			pstmt.setTimestamp(4, lessonOrderVO.getDateAcquisition());
			pstmt.setInt(5, lessonOrderVO.getLessonStatus());
			pstmt.setString(6, lessonOrderVO.getLessonOrderNo());
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
	public void delete(String lessonOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, lessonOrderNo);
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
	public void delete2(String lessonOrderNo) {
		int updateCount_LMDs = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除明細
			pstmt = con.prepareStatement(DELETE2_LMD);
			pstmt.setString(1, lessonOrderNo);
			updateCount_LMDs = pstmt.executeUpdate();
			// 再刪除訂單
			pstmt = con.prepareStatement(DELETE2_LO);
			pstmt.setString(1, lessonOrderNo);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除課程訂單:" + lessonOrderNo + "時,共有訂單明細" + updateCount_LMDs
					+ "筆同時被刪除");


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
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
	public LessonOrderVO findByPrimaryKey(String lessonOrderNo) {
		LessonOrderVO lessonOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, lessonOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));


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
		return lessonOrderVO;
	}

	@Override
	public List<LessonOrderVO> getOneAll(String memberNo) {
		List<LessonOrderVO> olist = new ArrayList<LessonOrderVO>();
		LessonOrderVO lessonOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Domain objects
				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));
				olist.add(lessonOrderVO);
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
		return olist;
	}

	@Override
	public List<LessonOrderVO> getAll() {
		List<LessonOrderVO> list = new ArrayList<LessonOrderVO>();
		LessonOrderVO lessonOrderVO = null;

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
				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonOrderVO);
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

		LessonOrderJDBCDAO dao = new LessonOrderJDBCDAO();

		// 新增
		LessonOrderVO lessonOrderVO1 = new LessonOrderVO();
		lessonOrderVO1.setMemberNo("M010");
		lessonOrderVO1.setLessonNo("LE009");
		lessonOrderVO1.setLessonPrice(50);
		lessonOrderVO1.setDateAcquisition(java.sql.Timestamp.valueOf("2020-02-01 17:27:46"));
		lessonOrderVO1.setLessonStatus(1);
		dao.insert(lessonOrderVO1);

		// 修改
//		LessonOrderVO lessonOrderVO2 = new LessonOrderVO();
//		lessonOrderVO2.setMemberNo("M009");
//		lessonOrderVO2.setLessonNo("LE009");
//		lessonOrderVO2.setLessonPrice(1000);
//		lessonOrderVO2.setDateAcquisition(java.sql.Timestamp.valueOf("2020-02-01 22:27:46"));
//		lessonOrderVO2.setLessonStatus(0);
//		lessonOrderVO2.setLessonOrderNo("LO027");		
//		dao.update(lessonOrderVO2);

		// 只刪除訂單 沒明細時才可執行(FK問題)
//		dao.delete("LO010");

		//連帶刪除訂單明細
//		dao.delete2("LO008");
		// 單一訂單
		LessonOrderVO lessonOrderVO3 = dao.findByPrimaryKey("LO001"); //
		System.out.print(lessonOrderVO3.getLessonOrderNo() + ",");
		System.out.print(lessonOrderVO3.getMemberNo() + ",");
		System.out.print(lessonOrderVO3.getLessonNo() + ",");
		System.out.print(lessonOrderVO3.getLessonPrice() + ",");
		System.out.print(lessonOrderVO3.getDateAcquisition() + ",");
		System.out.println(lessonOrderVO3.getLessonStatus());
		System.out.println("---------------------");

		// 所有訂單
		List<LessonOrderVO> list = dao.getAll();
		for (LessonOrderVO lessonOrderVO4 : list) {
			System.out.print(lessonOrderVO4.getLessonOrderNo() + ",");
			System.out.print(lessonOrderVO4.getMemberNo() + ",");
			System.out.print(lessonOrderVO4.getLessonNo() + ",");
			System.out.print(lessonOrderVO4.getLessonPrice() + ",");
			System.out.print(lessonOrderVO4.getDateAcquisition() + ",");
			System.out.println(lessonOrderVO4.getLessonStatus());
		}
			System.out.println("---------------------");

			//單一會員所有訂單
		List<LessonOrderVO> olist = dao.getOneAll("M009");
		for (LessonOrderVO lessonOrderVO5 : olist) {
			System.out.print(lessonOrderVO5.getLessonOrderNo() + ",");
			System.out.print(lessonOrderVO5.getMemberNo() + ",");
			System.out.print(lessonOrderVO5.getLessonNo() + ",");
			System.out.print(lessonOrderVO5.getLessonPrice() + ",");
			System.out.print(lessonOrderVO5.getDateAcquisition() + ",");
			System.out.println(lessonOrderVO5.getLessonStatus());
		}
			System.out.println("---------------------");		
	}

	@Override
	public void insert2(LessonOrderVO lessonOrderVO, List<LessonDetailVO> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LessonOrderVO> getLessonJoinMember(String lessonNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete2(LessonOrderVO lessonOrderVO) {
		// TODO Auto-generated method stub
		
	}
	
	
}
