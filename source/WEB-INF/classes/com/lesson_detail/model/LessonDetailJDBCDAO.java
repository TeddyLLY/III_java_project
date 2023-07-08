package com.lesson_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDetailJDBCDAO implements LessonDetailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO lesson_detail (lesson_detail_no,lesson_no,lesson_date) "
			+ "VALUES ('LCD'||LPAD(TO_CHAR(LESSON_DETAIL_NO_SEQ.NEXTVAL),3,'0') ,?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail order by lesson_no";
	private static final String GET_ONE_STMT = 
			"SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail where lesson_no = ?";
	private static final String GET_ONE_DETAIL = 
			"SELECT lesson_detail_no,lesson_no,lesson_date FROM lesson_detail where lesson_detail_no = ?";
	private static final String DELETE = 
			"DELETE FROM lesson_detail where lesson_no = ?";	
	private static final String DELETE2 = 
			"DELETE FROM lesson_detail where lesson_detail_no = ?";
	private static final String UPDATE = 
			"UPDATE lesson_detail set lesson_no = ?,lesson_date = ? where lesson_detail_no = ?";	

	
	
		@Override
		public void insert(LessonDetailVO lessonDetailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, lessonDetailVO.getLessonNo());
				pstmt.setDate(2, lessonDetailVO.getLessonDate());
				
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
		public void update(LessonDetailVO lessonDetailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, lessonDetailVO.getLessonNo());
				pstmt.setDate(2, lessonDetailVO.getLessonDate());
				pstmt.setString(3, lessonDetailVO.getLessonDetailNo());
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
		public void delete(String lessonNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, lessonNo);

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
		public List<LessonDetailVO> findByLesson(String lessonNo) {	
			List<LessonDetailVO> alist = new ArrayList<LessonDetailVO>();
			
			LessonDetailVO lessonDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, lessonNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					lessonDetailVO = new LessonDetailVO();
					lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
					lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
					lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
					alist.add(lessonDetailVO);
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
		public List<LessonDetailVO> getAll() {
			List<LessonDetailVO> list = new ArrayList<LessonDetailVO>();
			LessonDetailVO lessonDetailVO = null;

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
					lessonDetailVO = new LessonDetailVO();
					lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
					lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
					lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
					list.add(lessonDetailVO); // Store the row in the list
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
		public void deleteByLessonDetail(String lessonDetailNo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE2);

				pstmt.setString(1, lessonDetailNo);

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
		public LessonDetailVO findByPrimaryKey(String lessonDetailNo) {
		
			LessonDetailVO lessonDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_DETAIL);

				pstmt.setString(1, lessonDetailNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					lessonDetailVO = new LessonDetailVO();
					lessonDetailVO.setLessonDetailNo(rs.getString("lesson_detail_no"));
					lessonDetailVO.setLessonNo(rs.getString("lesson_no"));
					lessonDetailVO.setLessonDate(rs.getDate("lesson_date"));
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
		
		public static void main(String[] args) {

			LessonDetailJDBCDAO dao = new LessonDetailJDBCDAO();

			// 新增
			LessonDetailVO lessonDetailVO1 = new LessonDetailVO();
			lessonDetailVO1.setLessonNo("LE001");
			lessonDetailVO1.setLessonDate(java.sql.Date.valueOf("2020-03-05"));
			dao.insert(lessonDetailVO1);
		
			//修改
			
//			LessonDetailVO lessonDetailVOu = new LessonDetailVO();
//			lessonDetailVOu.setLessonNo("LE010");
//			lessonDetailVOu.setLessonDate(java.sql.Date.valueOf("2020-03-01"));
//			lessonDetailVOu.setLessonDetailNo("LCD009");
//			dao.update(lessonDetailVOu);			
//			
			//刪除單一課程明細
//			dao.deleteByLesson("LCD022");
			// 刪除一個課程底下全部明細
//			dao.delete("LE010"); //依課程編號刪除
			
			//查詢單一課程明細
			System.out.println("單一課程明細");
			LessonDetailVO ld = dao.findByPrimaryKey("LCD001");
			System.out.print(ld.getLessonDetailNo() + ",");
			System.out.print(ld.getLessonNo() + ",");
			System.out.println(ld.getLessonDate());
			
			System.out.println("---------------------");			

			

//			 查詢一個課程下的所有日期明細
			System.out.println("單一課程所有明細");
			List<LessonDetailVO> alist = dao.findByLesson("LE001");
			for (LessonDetailVO lessonDetailVO2 : alist) {
			System.out.print(lessonDetailVO2.getLessonDetailNo() + ",");
			System.out.print(lessonDetailVO2.getLessonNo() + ",");
			System.out.println(lessonDetailVO2.getLessonDate());
			}		
			System.out.println("---------------------");

			// 查詢
			System.out.println("所有明細");
			List<LessonDetailVO> list = dao.getAll();
			for (LessonDetailVO lessonDetailVO3 : list) {
				System.out.print(lessonDetailVO3.getLessonDetailNo() + ",");
				System.out.print(lessonDetailVO3.getLessonNo() + ",");
				System.out.println(lessonDetailVO3.getLessonDate());
			}
		}






	
}
