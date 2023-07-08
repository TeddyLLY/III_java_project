package com.favorite_lesson.model;
import java.util.*;
import java.sql.*;

public class FavoriteLessonJDBCDAO implements FavoriteLessonDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
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
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, favoriteLessonVO.getLessonNo());
				pstmt.setString(2, favoriteLessonVO.getMemberNo());
				
				pstmt.executeUpdate();
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
		public void delete(FavoriteLessonVO favoriteLessonVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, favoriteLessonVO.getLessonNo());
				pstmt.setString(2, favoriteLessonVO.getMemberNo());

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
		public List<FavoriteLessonVO> findByPrimaryKey(String memberNo) {	
			List<FavoriteLessonVO> alist = new ArrayList<FavoriteLessonVO>();
			
			FavoriteLessonVO favoriteLessonVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, memberNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					favoriteLessonVO = new FavoriteLessonVO();
					favoriteLessonVO.setLessonNo(rs.getString("lesson_no"));
					favoriteLessonVO.setMemberNo(rs.getString("member_no"));
					alist.add(favoriteLessonVO);
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					favoriteLessonVO = new FavoriteLessonVO();
					favoriteLessonVO.setLessonNo(rs.getString("lesson_no"));
					favoriteLessonVO.setMemberNo(rs.getString("member_no"));
					list.add(favoriteLessonVO); // Store the row in the list
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

			FavoriteLessonJDBCDAO dao = new FavoriteLessonJDBCDAO();

//			// 新增
//			FavoriteLessonVO favoriteLessonVO1 = new FavoriteLessonVO();
//			favoriteLessonVO1.setLessonNo("LE006");
//			favoriteLessonVO1.setMemberNo("M010");
//			dao.insert(favoriteLessonVO1);

//			// 刪除
//			FavoriteLessonVO favoriteLessonVO2 = new FavoriteLessonVO();
//			favoriteLessonVO2.setLessonNo("LE006");
//			favoriteLessonVO2.setMemberNo("M010");
//			dao.delete(favoriteLessonVO2);

//			// 查詢
			List<FavoriteLessonVO> alist = dao.findByPrimaryKey("M010");
			for (FavoriteLessonVO favoriteLessonVO3 : alist) {
			System.out.print(favoriteLessonVO3.getMemberNo() + ",");
			System.out.println(favoriteLessonVO3.getLessonNo());
			}
		
			System.out.println("---------------------");

			// 查詢
			List<FavoriteLessonVO> list = dao.getAll();
			for (FavoriteLessonVO aEmp : list) {
				System.out.print(aEmp.getMemberNo() + ",");
				System.out.print(aEmp.getLessonNo());
				System.out.println();
			}
		}


}
