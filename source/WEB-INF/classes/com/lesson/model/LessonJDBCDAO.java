package com.lesson.model;
import java.util.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import com.lesson_detail.model.LessonDetailJNDIDAO;
import com.lesson_detail.model.LessonDetailVO;;

public class LessonJDBCDAO implements LessonDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String UPDATEPIC  = "UPDATE LESSON SET LESSON_PICTURE=? WHERE LESSON_NO =?";
	
	private static final String INSERT_STMT = 
		"INSERT INTO lesson (lesson_no,coach_no,lesson_name,lesson_point,lesson_price,"
		+ "lesson_content,lesson_start_date,lesson_end_date,lesson_registration,"
		+ "lesson_maximum_people,lesson_picture,lesson_location) "
		+ "VALUES ('LE'||LPAD(TO_CHAR(LESSON_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_mALL_STMT = 
		"SELECT lesson_no,coach_no,lesson_name,lesson_point,lesson_content,lesson_price,"
		+ "to_char(lesson_start_date,'yyyy-mm-dd') lesson_start_date,"
		+ "to_char(lesson_end_date,'yyyy-mm-dd') lesson_end_date,"
		+ "lesson_registration,lesson_maximum_people,lesson_picture,lesson_location "
		+ "FROM lesson order by lesson_no";
	private static final String GET_ALL_STMT = 
		"SELECT lesson_name,coach_no,lesson_price,"
		+ "to_char(lesson_end_date,'yyyy-mm-dd') lesson_end_date,"
		+ "lesson_registration,lesson_maximum_people,lesson_picture,lesson_location "
		+ "FROM lesson where lesson_end_date>=sysdate order by lesson_no";
	
	private static final String GET_ONE_STMT = 
		"SELECT lesson_no,coach_no,lesson_name,lesson_point,lesson_content,lesson_price,"
		+ "to_char(lesson_start_date,'yyyy-mm-dd') lesson_start_date,"
		+ "to_char(lesson_end_date,'yyyy-mm-dd') lesson_end_date,"
		+ "lesson_registration,lesson_maximum_people,lesson_picture,lesson_location "
		+ "FROM lesson where lesson_no = ? ";
	
	private static final String SEARCH_STMT =
		"SELECT * FROM LESSON WHERE CONCAT(LESSON_NAME,LESSON_CONTENT) LIKE ? "; //只能做單字查詢

	/*JOIN用法 	SELECT L.lesson_no,L.coach_no,C.COACH_NAMEFROM LESSON L  JOIN COACH C ON L.COACH_NO = C.COACH_NO where lesson_no = 'LE010';*/
	
	private static final String DELETE = 
		"DELETE FROM lesson where lesson_no = ?";
	private static final String DELETE2_LE = 
			"DELETE FROM lesson where lesson_no = ?";
	private static final String DELETE2_LD = 
			"DELETE FROM lesson_detail where lesson_no = ?";
	private static final String DELETE2_FL = 
			"DELETE FROM favorite_lesson where lesson_no = ?";

	private static final String UPDATE = 
		"UPDATE lesson set lesson_name = ?,lesson_point = ?,"
		+ "lesson_price = ?,lesson_content = ?,lesson_end_date = ?,"
		+ "lesson_maximum_people = ?,lesson_picture = ?,"
		+ "lesson_location = ? where lesson_no = ?";


	
	@Override
	public LessonVO findByNoForAndroid(String lessonNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LessonVO> getAllForAndroid() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public byte[] getImage(String lessonNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insert(LessonVO lessonVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonVO.getCoachNo()); //之後要自己抓教練編號
			pstmt.setString(2, lessonVO.getLessonName());
			pstmt.setString(3, lessonVO.getLessonPoint());
			pstmt.setInt(4, lessonVO.getLessonPrice());
			pstmt.setString(5, lessonVO.getLessonContent());
			pstmt.setDate(6, lessonVO.getLessonStartDate());
			pstmt.setDate(7, lessonVO.getLessonEndDate());
			pstmt.setInt(8, lessonVO.getLessonRegistration());
			pstmt.setInt(9, lessonVO.getLessonMaximumPeople());

			ByteArrayInputStream bais=new ByteArrayInputStream(lessonVO.getLessonPicture());  
			pstmt.setBinaryStream(10, bais);
			
			pstmt.setString(11, lessonVO.getLessonLocation());
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
		} catch (Exception se) {
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
	public void update(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(X, lessonVO.getCoachNo()); //教練更新資料教練編號不可改
//			pstmt.setDate(X, lessonVO.getLessonStartDate()); //課程上傳日期不因更新而變動

			pstmt.setString(1, lessonVO.getLessonName());
			pstmt.setString(2, lessonVO.getLessonPoint());
			pstmt.setInt(3, lessonVO.getLessonPrice());
			pstmt.setString(4, lessonVO.getLessonContent());
			pstmt.setDate(5, lessonVO.getLessonEndDate());
//			pstmt.setInt(X, lessonVO.getLessonRegistration()); //不可變更報名人數
			pstmt.setInt(6, lessonVO.getLessonMaximumPeople());
			
			
			pstmt.setBytes(7, lessonVO.getLessonPicture());
			
			
			pstmt.setString(8, lessonVO.getLessonLocation());
			pstmt.setString(9, lessonVO.getLessonNo()); //輸入課程編號抓table
			
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
	public void delete2(String lessonNo) {
		int updateCount_LDs = 0;
		int updateCount_FLs = 0;		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除明細
			pstmt = con.prepareStatement(DELETE2_LD);
			pstmt.setString(1, lessonNo);
			updateCount_LDs = pstmt.executeUpdate();
			//再刪除課程收藏TABLE
			pstmt = con.prepareStatement(DELETE2_FL);
			pstmt.setString(1, lessonNo);
			updateCount_FLs = pstmt.executeUpdate();
			// 再刪除課程
			pstmt = con.prepareStatement(DELETE2_LE);
			pstmt.setString(1, lessonNo);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除課程:" + lessonNo + "時,共有課程明細" + updateCount_LDs
					+ "筆同時被刪除，以及課程收藏共有"+(updateCount_FLs-updateCount_LDs)+"筆被刪除");

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
	public LessonVO findByPrimaryKey(String lessonNo) {
		LessonVO lessonVO = null;
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

				lessonVO = new LessonVO();
				lessonVO.setLessonNo(rs.getString("lesson_no"));
				lessonVO.setCoachNo(rs.getString("coach_no"));
				lessonVO.setLessonName(rs.getString("lesson_name"));
				lessonVO.setLessonPoint(rs.getString("lesson_point"));
				lessonVO.setLessonContent(rs.getString("lesson_content"));
				lessonVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonVO.setLessonStartDate(rs.getDate("lesson_start_date"));
				lessonVO.setLessonEndDate(rs.getDate("lesson_end_date"));
				lessonVO.setLessonRegistration(rs.getInt("lesson_registration"));
				lessonVO.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
				lessonVO.setLessonPicture(rs.getBytes("lesson_picture"));
				lessonVO.setLessonLocation(rs.getString("lesson_location"));

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
		return lessonVO;
	}
	@Override
	public List<LessonVO> getmAll() {
		// 所有課程
		List<LessonVO> mlist = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_mALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Domain objects
				lessonVO = new LessonVO();
				lessonVO.setLessonNo(rs.getString("lesson_no"));
				lessonVO.setCoachNo(rs.getString("coach_no"));
				lessonVO.setLessonName(rs.getString("lesson_name"));
				lessonVO.setLessonPoint(rs.getString("lesson_point"));
				lessonVO.setLessonContent(rs.getString("lesson_content"));
				lessonVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonVO.setLessonStartDate(rs.getDate("lesson_start_date"));
				lessonVO.setLessonEndDate(rs.getDate("lesson_end_date"));
				lessonVO.setLessonRegistration(rs.getInt("lesson_registration"));
				lessonVO.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
				lessonVO.setLessonPicture(rs.getBytes("lesson_picture"));
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				mlist.add(lessonVO); // Store the row in the list
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
		return mlist;
	}
	@Override
	public List<LessonVO> getAll() {
		// 課程總覽
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lessonVO = new LessonVO();
				lessonVO.setCoachNo(rs.getString("coach_no"));
				lessonVO.setLessonName(rs.getString("lesson_name"));
				lessonVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonVO.setLessonEndDate(rs.getDate("lesson_end_date"));
				lessonVO.setLessonRegistration(rs.getInt("lesson_registration"));
				lessonVO.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
				
				lessonVO.setLessonPicture(rs.getBytes("lesson_picture"));
				
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				list.add(lessonVO); // Store the row in the list
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
	
	
	public List<LessonVO> search(String string) {
		
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO search = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_STMT);
			pstmt.setString(1, string);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				search = new LessonVO();
				search.setCoachNo(rs.getString("coach_no"));
				search.setLessonName(rs.getString("lesson_name"));
				search.setLessonPrice(rs.getInt("lesson_price"));
				search.setLessonEndDate(rs.getDate("lesson_end_date"));
				search.setLessonRegistration(rs.getInt("lesson_registration"));
				search.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
				search.setLessonPicture(rs.getBytes("lesson_picture"));
				search.setLessonLocation(rs.getString("lesson_location"));
				list.add(search); // Store the row in the list
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

		LessonJDBCDAO dao = new LessonJDBCDAO();
		
//		// 新增
//		LessonVO lessonVO1 = new LessonVO();
//		lessonVO1.setCoachNo("C010");
//		lessonVO1.setLessonName("00魔鬼訓練");
//		lessonVO1.setLessonPoint("簡介:高階的魔鬼訓練");
//		lessonVO1.setLessonPrice(9999);
//		lessonVO1.setLessonContent("***我是課程內容****高階魔鬼訓練");
//		lessonVO1.setLessonStartDate(java.sql.Date.valueOf("2020-01-02"));
//		lessonVO1.setLessonEndDate(java.sql.Date.valueOf("2020-01-30"));
//		lessonVO1.setLessonRegistration(10); //報名人數之後不可讓教練輸入
//		lessonVO1.setLessonMaximumPeople(15);
//	
//		File file = new File("\test.jpg");
//		byte[] img = null;
//		try {
//			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//			img= new byte[in.available()];
//			in.read(img);
//			in.close();
//		} catch (IOException e ) {
//			e.printStackTrace();
//		}
//		lessonVO1.setLessonPicture(img);
//		
//		lessonVO1.setLessonLocation("大安森林公園");
//		dao.insert(lessonVO1);

		// 修改
//		LessonVO lessonVO2 = new LessonVO();
//		lessonVO2.setLessonName("肺活量訓練");
//		lessonVO2.setLessonPoint("***我是課程簡介2***");
//		lessonVO2.setLessonPrice(500);
//		lessonVO2.setLessonContent("***我是課程內容2***");
//		lessonVO2.setLessonEndDate(java.sql.Date.valueOf("2020-02-01"));
//		lessonVO2.setLessonRegistration(15); //報名人數之後不可讓教練輸入
//		lessonVO2.setLessonMaximumPeople(15);
//		lessonVO2.setLessonPicture(new byte[15]);
//		lessonVO2.setLessonLocation("河濱公園");
//		lessonVO2.setLessonNo("LE034");
//		dao.update(lessonVO2);

		// 只刪除課程
//		dao.delete("LE010");

		// 刪除課程+詳情+收藏(注意:已有會員購買並產生課程訂單，則課程無法刪除)
		
//		dao.delete2("LE009");		
		
		// 查詢課程詳情
//		LessonVO lessonVO3 = dao.findByPrimaryKey("LE001"); //
//		System.out.print(lessonVO3.getLessonNo() + ",");
//		System.out.print(lessonVO3.getCoachNo() + ",");
//		System.out.print(lessonVO3.getLessonName() + ",");
//		System.out.print(lessonVO3.getLessonPoint() + ",");
//		System.out.print(lessonVO3.getLessonContent() + ",");
//		System.out.print(lessonVO3.getLessonPrice() + ",");
//		System.out.print(lessonVO3.getLessonStartDate() + ",");
//		System.out.print(lessonVO3.getLessonEndDate() + ",");
//		System.out.print(lessonVO3.getLessonRegistration() + ",");
//		System.out.print(lessonVO3.getLessonMaximumPeople() + ",");
//		System.out.print(lessonVO3.getLessonPicture() + ",");
//		System.out.println(lessonVO3.getLessonLocation());
//		System.out.println("---------------------");
//
//		// 所有課程詳情
//		List<LessonVO> mlist = dao.getmAll();
//		for (LessonVO LE : mlist) {
//			System.out.print(LE.getLessonNo() + ",");
//			System.out.print(LE.getCoachNo() + ",");
//			System.out.print(LE.getLessonName() + ",");
//			System.out.print(LE.getLessonPoint() + ",");
//			System.out.print(LE.getLessonContent() + ",");
//			System.out.print(LE.getLessonPrice() + ",");
//			System.out.print(LE.getLessonStartDate() + ",");
//			System.out.print(LE.getLessonEndDate() + ",");
//			System.out.print(LE.getLessonRegistration() + ",");
//			System.out.print(LE.getLessonMaximumPeople() + ",");
//			System.out.print(LE.getLessonPicture() + ",");
//			System.out.println(LE.getLessonLocation());
//
//		}
//			System.out.println("---------------------");
//			
//			//課程總覽
//		List<LessonVO> list = dao.getAll();
//		for (LessonVO LE : list) {
//			System.out.print(LE.getCoachNo() + ",");
//			System.out.print(LE.getLessonName() + ",");
//			System.out.print(LE.getLessonPrice() + ",");
//			System.out.print(LE.getLessonEndDate() + ",");
//			System.out.print(LE.getLessonRegistration() + ",");
//			System.out.print(LE.getLessonMaximumPeople() + ",");
//			System.out.print(LE.getLessonPicture() + ",");
//			System.out.println(LE.getLessonLocation());
//		}
//			System.out.println("---------------------");		
//	
//	
//		List<LessonVO> slist = dao.search("%瑜珈%");
//		for (LessonVO LE : slist) {
//			System.out.print(LE.getCoachNo() + ",");
//			System.out.print(LE.getLessonName() + ",");
//			System.out.print(LE.getLessonPrice() + ",");
//			System.out.print(LE.getLessonEndDate() + ",");
//			System.out.print(LE.getLessonRegistration() + ",");
//			System.out.print(LE.getLessonMaximumPeople() + ",");
//			System.out.print(LE.getLessonPicture() + ",");
//			System.out.println(LE.getLessonLocation());
//	}
//		System.out.println("---------------------");		
}
	
	
	
	@Override
	public void insertWithDetails(LessonVO lessonVO, List<LessonDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"lesson_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			
			pstmt.setString(1, lessonVO.getCoachNo());
			pstmt.setString(2, lessonVO.getLessonName());
			pstmt.setString(3, lessonVO.getLessonPoint());
			pstmt.setInt(4, lessonVO.getLessonPrice());
			pstmt.setString(5, lessonVO.getLessonContent());
			pstmt.setDate(6, lessonVO.getLessonStartDate());
			pstmt.setDate(7, lessonVO.getLessonEndDate());
			pstmt.setInt(8, lessonVO.getLessonRegistration());
			pstmt.setInt(9, lessonVO.getLessonMaximumPeople());

			ByteArrayInputStream bais=new ByteArrayInputStream(lessonVO.getLessonPicture());  
			pstmt.setBinaryStream(10, bais);
			
			pstmt.setString(11, lessonVO.getLessonLocation());
			pstmt.executeUpdate();
			
			
			//掘取對應的自增主鍵值
			String next_lessonNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_lessonNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_lessonNo +"(剛新增成功的課程編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			LessonDetailJNDIDAO dao = new LessonDetailJNDIDAO();
			System.out.println("list.size()-A="+list.size());
			for (LessonDetailVO aLessonDetail : list) {
				aLessonDetail.setLessonNo(new String(next_lessonNo)) ;
				dao.insert2(aLessonDetail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_lessonNo + "時,共有員工" + list.size()
					+ "人同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public void update2(LessonVO lessonVO, List<LessonDetailVO> list) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<LessonVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LessonVO> getLessonByCoach(String coachNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LessonVO> getLessonDateByCoach(String coachNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateLessonStatus(LessonVO lessonVO) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<LessonVO> gethistoryAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
