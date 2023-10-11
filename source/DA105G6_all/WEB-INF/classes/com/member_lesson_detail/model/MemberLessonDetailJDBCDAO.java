package com.member_lesson_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson_order.model.LessonOrderJDBCDAO;
import com.lesson_order.model.LessonOrderVO;

public class MemberLessonDetailJDBCDAO implements MemberLessonDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO member_lesson_detail (member_lesson_detail_no,lesson_order_no,student_status,lesson_date)"
		+ " VALUES ('LMD'||LPAD(TO_CHAR(MEMBER_LESSON_DETAIL_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date "
		+ "FROM member_lesson_detail order by member_lesson_detail_no";
	private static final String GET_ONE_ALL_STMT = 
		"SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date"
		+ " FROM member_lesson_detail where lesson_order_no = ? order by lesson_date";
	
	private static final String GET_ONE_STMT = 
		"SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date"
		+ " FROM member_lesson_detail where member_lesson_detail_no = ? ";
	
	private static final String DELETE = 
		"DELETE FROM member_lesson_detail where member_lesson_detail_no = ?"; 
	private static final String DELETE2 = 
			"DELETE FROM member_lesson_detail where lesson_order_no = ?"; 
	
	private static final String UPDATE = 
		"UPDATE member_lesson_detail set lesson_order_no = ?,student_status = ?,lesson_date = ?"
		+ " where member_lesson_detail_no = ?";
	
	@Override
	public void insert(MemberLessonDetailVO memberLessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberLessonDetailVO.getLessonOrderNo());
			pstmt.setInt(2, memberLessonDetailVO.getStudentStatus());
			pstmt.setDate(3, memberLessonDetailVO.getLessonDate());

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
	public void update(MemberLessonDetailVO memberLessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberLessonDetailVO.getLessonOrderNo());
			pstmt.setInt(2, memberLessonDetailVO.getStudentStatus());
			pstmt.setDate(3, memberLessonDetailVO.getLessonDate());
			pstmt.setString(4, memberLessonDetailVO.getMemberLessonDetailNo());			
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
	public void delete(String memberLessonDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, memberLessonDetailNo);
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE2);
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
	public MemberLessonDetailVO findByPrimaryKey(String memberLessonDetailNo) {
		MemberLessonDetailVO memberLessonDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memberLessonDetailNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberLessonDetailVO = new MemberLessonDetailVO();
				memberLessonDetailVO.setMemberLessonDetailNo(rs.getString("member_lesson_detail_no"));
				memberLessonDetailVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				memberLessonDetailVO.setStudentStatus(rs.getInt("student_status"));
				memberLessonDetailVO.setLessonDate(rs.getDate("lesson_date"));



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
		return memberLessonDetailVO;
	}

	@Override
	public List<MemberLessonDetailVO> getAll() {
		List<MemberLessonDetailVO> list = new ArrayList<MemberLessonDetailVO>();
		MemberLessonDetailVO memberLessonDetailVO = null;

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
				memberLessonDetailVO = new MemberLessonDetailVO();
				memberLessonDetailVO.setMemberLessonDetailNo(rs.getString("member_lesson_detail_no"));
				memberLessonDetailVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				memberLessonDetailVO.setStudentStatus(rs.getInt("student_status"));
				memberLessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				list.add(memberLessonDetailVO);
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
	public List<MemberLessonDetailVO> getOneAll(String lessonOrderNo) {
		List<MemberLessonDetailVO> lmdlist = new ArrayList<MemberLessonDetailVO>();
		MemberLessonDetailVO memberLessonDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, lessonOrderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Domain objects
				memberLessonDetailVO = new MemberLessonDetailVO();
				memberLessonDetailVO.setMemberLessonDetailNo(rs.getString("member_lesson_detail_no"));
				memberLessonDetailVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				memberLessonDetailVO.setStudentStatus(rs.getInt("student_status"));
				memberLessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				lmdlist.add(memberLessonDetailVO);
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
	public static void main(String[] args) {

		MemberLessonDetailJDBCDAO dao = new MemberLessonDetailJDBCDAO();

		// 新增
//		MemberLessonDetailVO memberLessonDetailVO1 = new MemberLessonDetailVO();
//		memberLessonDetailVO1.setLessonOrderNo("LO010");
//		memberLessonDetailVO1.setStudentStatus(0);
//		memberLessonDetailVO1.setLessonDate(java.sql.Date.valueOf("2020-02-28"));
//
//		dao.insert(memberLessonDetailVO1);

		// 修改
//		MemberLessonDetailVO memberLessonDetailVO2 = new MemberLessonDetailVO();
//		memberLessonDetailVO2.setLessonOrderNo("LO009");
//		memberLessonDetailVO2.setStudentStatus(1);
//		memberLessonDetailVO2.setLessonDate(java.sql.Date.valueOf("2020-02-27"));
//		memberLessonDetailVO2.setMemberLessonDetailNo("LMD011");
//		
//		dao.update(memberLessonDetailVO2);

		//刪除某訂單詳情
//		dao.delete("LMD011");
		// 刪除某訂單下的所有訂單詳情
//		dao.delete2("LO009");


		// 單一訂單
		System.out.println("單一訂單");
		MemberLessonDetailVO memberLessonDetailVO3 = dao.findByPrimaryKey("LMD001"); //
		System.out.print(memberLessonDetailVO3.getMemberLessonDetailNo() + ",");
		System.out.print(memberLessonDetailVO3.getLessonOrderNo() + ",");
		System.out.print(memberLessonDetailVO3.getStudentStatus() + ",");
		System.out.println(memberLessonDetailVO3.getLessonDate() );

		System.out.println("---------------------");

		// 所有訂單
		System.out.println("所有訂單");		
		List<MemberLessonDetailVO> list = dao.getAll();
		for (MemberLessonDetailVO memberLessonDetailVO4 : list) {
			System.out.print(memberLessonDetailVO4.getMemberLessonDetailNo() + ",");
			System.out.print(memberLessonDetailVO4.getLessonOrderNo() + ",");
			System.out.print(memberLessonDetailVO4.getStudentStatus() + ",");
			System.out.println(memberLessonDetailVO4.getLessonDate() );
		}
			System.out.println("---------------------");

			//單一訂單所有訂單明細
			System.out.println("單一課程訂單所有訂單明細");
		List<MemberLessonDetailVO> lmdlist = dao.getOneAll("LO009");
		for (MemberLessonDetailVO memberLessonDetailVO5 : lmdlist) {
			System.out.print(memberLessonDetailVO5.getMemberLessonDetailNo() + ",");
			System.out.print(memberLessonDetailVO5.getLessonOrderNo() + ",");
			System.out.print(memberLessonDetailVO5.getStudentStatus() + ",");
			System.out.println(memberLessonDetailVO5.getLessonDate() );
		}
			System.out.println("---------------------");		
	}


	@Override
	public void updateSTATUSTODAY(String lessonOrderNo, Integer studentstatus, Integer orgstatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMYSTATUS(String memberLessonDetail, Integer studentStatus) {
		// TODO Auto-generated method stub
		
	}

		

}
