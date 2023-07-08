package com.lesson_detail.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LessonDetailJNDIDAO implements LessonDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO lesson_detail (lesson_detail_no,lesson_no,lesson_date) "
			+ "VALUES ('LCD'||LPAD(TO_CHAR(LESSON_DETAIL_NO_SEQ.NEXTVAL),3,'0') ,?, ?)";
	private static final String GET_ALL_STMT = "SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail order by lesson_no";
	private static final String GET_ONE_STMT = "SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail where lesson_no = ? order by lesson_date";
	private static final String GET_ONE_DETAIL = "SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail where lesson_detail_no = ? order by lesson_date";
	private static final String DELETE = "DELETE FROM lesson_detail where lesson_no = ?";
	private static final String DELETE2 = "DELETE FROM lesson_detail where lesson_detail_no = ?";
	private static final String UPDATE = "UPDATE lesson_detail set lesson_no = ?,lesson_date = ? where lesson_detail_no = ?";

	@Override
	public void insert(LessonDetailVO lessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonDetailVO.getLessonNo());
			pstmt.setDate(2, lessonDetailVO.getLessonDate());

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
	public void update(LessonDetailVO lessonDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, lessonDetailVO.getLessonNo());
			pstmt.setDate(2, lessonDetailVO.getLessonDate());
			pstmt.setString(3, lessonDetailVO.getLessonDetailNo());
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
	public void delete(String lessonNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, lessonNo);

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
	public List<LessonDetailVO> findByLesson(String lessonNo) {
		List<LessonDetailVO> alist = new ArrayList<LessonDetailVO>();

		LessonDetailVO lessonDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, lessonNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonDetailVO = new LessonDetailVO();
				lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
				lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
				lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				alist.add(lessonDetailVO);
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
		return alist;
	}

	@Override
	public List<LessonDetailVO> getAll() {
		List<LessonDetailVO> list = new ArrayList<LessonDetailVO>();
		LessonDetailVO lessonDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonDetailVO = new LessonDetailVO();
				lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
				lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
				lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
				list.add(lessonDetailVO); // Store the row in the list
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
	public void deleteByLessonDetail(String lessonDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE2);

			pstmt.setString(1, lessonDetailNo);

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
	public LessonDetailVO findByPrimaryKey(String lessonDetailNo) {

		LessonDetailVO lessonDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DETAIL);

			pstmt.setString(1, lessonDetailNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonDetailVO = new LessonDetailVO();
				lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
				lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
				lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
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
		return lessonDetailVO;
	}

	@Override
	public void insert2(LessonDetailVO lessonDetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonDetailVO.getLessonNo());
			pstmt.setDate(2, lessonDetailVO.getLessonDate());
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
}
