package com.favorite_lesson.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class FavoriteLessonJNDIDAO implements FavoriteLessonDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO favorite_lesson (lesson_no,member_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT member_no,lesson_no FROM favorite_lesson order by member_no";
	private static final String GET_ONE_STMT = 
			"SELECT member_no,lesson_no FROM favorite_lesson where member_no = ?";
	private static final String DELETE = 
			"DELETE FROM favorite_lesson where lesson_no = ? and member_no = ?";
	

	@Override
	public void insert(FavoriteLessonVO favoriteLessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, favoriteLessonVO.getLessonNo());
			pstmt.setString(2, favoriteLessonVO.getMemberNo());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
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
	public void delete(FavoriteLessonVO favoriteLessonVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, favoriteLessonVO.getLessonNo());
			pstmt.setString(2, favoriteLessonVO.getMemberNo());

			pstmt.executeUpdate();
		} catch (SQLException se) {
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
	public List<FavoriteLessonVO> findByPrimaryKey(String memberNo) {	
		List<FavoriteLessonVO> alist = new ArrayList<FavoriteLessonVO>();
		
		FavoriteLessonVO favoriteLessonVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteLessonVO = new FavoriteLessonVO();
				favoriteLessonVO.setLessonNo(rs.getString("lesson_no"));
				favoriteLessonVO.setMemberNo(rs.getString("member_no"));
				alist.add(favoriteLessonVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<FavoriteLessonVO> getAll() {
		List<FavoriteLessonVO> list = new ArrayList<FavoriteLessonVO>();
		FavoriteLessonVO favoriteLessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteLessonVO = new FavoriteLessonVO();
				favoriteLessonVO.setLessonNo(rs.getString("lesson_no"));
				favoriteLessonVO.setMemberNo(rs.getString("member_no"));
				list.add(favoriteLessonVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
