package com.member_lesson_detail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lesson_detail.model.LessonDetailVO;

import java.sql.*;

public class MemberLessonDetailJNDIDAO implements MemberLessonDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO member_lesson_detail (member_lesson_detail_no,lesson_order_no,student_status,lesson_date)"
			+ " VALUES ('LMD'||LPAD(TO_CHAR(MEMBER_LESSON_DETAIL_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date "
			+ "FROM member_lesson_detail order by member_lesson_detail_no";
	private static final String GET_ONE_ALL_STMT = "SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date"
			+ " FROM member_lesson_detail where lesson_order_no = ? ORDER BY lesson_date";

	private static final String GET_ONE_STMT = "SELECT member_lesson_detail_no,lesson_order_no,student_status,lesson_date"
			+ " FROM member_lesson_detail where member_lesson_detail_no = ? ";

	private static final String DELETE = "DELETE FROM member_lesson_detail where member_lesson_detail_no = ?";
	private static final String DELETE2 = "DELETE FROM member_lesson_detail where lesson_order_no = ?";

	private static final String UPDATE = "UPDATE member_lesson_detail set lesson_order_no = ?,student_status = ?,lesson_date = ?"
			+ " where member_lesson_detail_no = ?";
	
	private static final String UPDATESTATUSTODAY = "UPDATE member_lesson_detail set student_status = ? where "
			+ " TO_CHAR(lesson_date, 'YYYY-MM-DD')=TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND lesson_order_no =? "
			+ " AND student_status =?";
	private static final String UPDATESTATUS = "UPDATE member_lesson_detail set student_status = ? "
			+ " where member_lesson_detail_no =? ";

	@Override
	public void insert(MemberLessonDetailVO memberLessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberLessonDetailVO.getLessonOrderNo());
			pstmt.setInt(2, memberLessonDetailVO.getStudentStatus());
			pstmt.setDate(3, memberLessonDetailVO.getLessonDate());

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
	public void update(MemberLessonDetailVO memberLessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberLessonDetailVO.getLessonOrderNo());
			pstmt.setInt(2, memberLessonDetailVO.getStudentStatus());
			pstmt.setDate(3, memberLessonDetailVO.getLessonDate());
			pstmt.setString(4, memberLessonDetailVO.getMemberLessonDetailNo());
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
	public void updateSTATUSTODAY(String lessonOrderNo,Integer studentStatus,Integer orgstatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUSTODAY);


			pstmt.setInt(1, studentStatus);
			pstmt.setString(2, lessonOrderNo);
			pstmt.setInt(3, orgstatus);
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
	public void delete(String memberLessonDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, memberLessonDetailNo);
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
	public void delete2(String lessonOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);
			pstmt.setString(1, lessonOrderNo);
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
	public MemberLessonDetailVO findByPrimaryKey(String memberLessonDetailNo) {
		MemberLessonDetailVO memberLessonDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberLessonDetailVO = new MemberLessonDetailVO();
				memberLessonDetailVO.setMemberLessonDetailNo(rs.getString("member_lesson_detail_no"));
				memberLessonDetailVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				memberLessonDetailVO.setStudentStatus(rs.getInt("student_status"));
				memberLessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				list.add(memberLessonDetailVO);
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
	public List<MemberLessonDetailVO> getOneAll(String lessonOrderNo) {
		List<MemberLessonDetailVO> lmdlist = new ArrayList<MemberLessonDetailVO>();
		MemberLessonDetailVO memberLessonDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, lessonOrderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberLessonDetailVO = new MemberLessonDetailVO();
				memberLessonDetailVO.setMemberLessonDetailNo(rs.getString("member_lesson_detail_no"));
				memberLessonDetailVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				memberLessonDetailVO.setStudentStatus(rs.getInt("student_status"));
				memberLessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				lmdlist.add(memberLessonDetailVO);
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

		return lmdlist;
	}

	public void insert2(LessonDetailVO aLessonDetail, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, aLessonDetail.getLessonNo());
			pstmt.setInt(2, 0);//上課狀態:預設"0":未報到
			pstmt.setDate(3, aLessonDetail.getLessonDate());
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
	public void updateMYSTATUS(String memberLessonDetailNo, Integer studentStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);


			pstmt.setInt(1, studentStatus);
			pstmt.setString(2, memberLessonDetailNo);

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

}
