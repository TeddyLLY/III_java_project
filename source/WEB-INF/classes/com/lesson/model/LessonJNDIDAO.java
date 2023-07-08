package com.lesson.model;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lesson_detail.model.LessonDetailJNDIDAO;
import com.lesson_detail.model.LessonDetailVO;

public class LessonJNDIDAO implements LessonDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String UPDATEPIC  = "UPDATE LESSON SET LESSON_PICTURE=? WHERE LESSON_NO =?";
	
	
	private static final String INSERT_STMT = "INSERT INTO lesson (lesson_no,coach_no,lesson_name,lesson_point,lesson_price,"
			+ "lesson_content,lesson_start_date,lesson_end_date,lesson_registration,"
			+ "lesson_maximum_people,lesson_picture,lesson_location,lesson_class,lesson_status) "
			+ "VALUES ('LE'||LPAD(TO_CHAR(LESSON_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_mALL_STMT = "SELECT * FROM lesson order by lesson_no";
	private static final String GET_ALL_STMT = "SELECT * FROM lesson where lesson_end_date>=sysdate order by lesson_end_date";
	private static final String GET_history_ALL_STMT = "SELECT * FROM lesson where lesson_end_date<sysdate+30 order by lesson_end_date";
	private static final String GET_ONE_STMT = "SELECT * FROM lesson where lesson_no = ?";
	private static final String SEARCH_STMT =
			"SELECT * FROM LESSON WHERE CONCAT(LESSON_NAME,LESSON_CONTENT) LIKE ? "; //只能做單字查詢	
	private static final String DELETE = "DELETE FROM lesson where lesson_no = ?";
	private static final String DELETE2_LE = "DELETE FROM lesson where lesson_no = ?";
	private static final String DELETE2_LD = "DELETE FROM lesson_detail where lesson_no = ?";
	private static final String DELETE2_FL = "DELETE FROM favorite_lesson where lesson_no = ?";
	private static final String UPDATE = "UPDATE lesson set lesson_name = ?,lesson_point = ?,"
			+ "lesson_price = ?,lesson_content = ?,lesson_start_date = ?,lesson_end_date = ?,"
			+ "lesson_maximum_people = ?,lesson_picture = ?,"
			+ "lesson_location = ?,lesson_class = ?,lesson_status = ? where lesson_no = ?";
	private static final String UPDATE_REG = "UPDATE lesson set LESSON_REGISTRATION = ? where lesson_no = ?";
	private static final String UPDATE_STATUS = "UPDATE lesson set LESSON_STATUS = ? where lesson_no = ?";	
	private static final String GET_COACH_STMT = "SELECT * FROM lesson where COACH_NO = ?";

	
	// Android
	private static final String FIND_BY_LESSON_NO = "SELECT * FROM LESSON WHERE LESSON_NO = ?";
	private static final String GET_ALL_ANDROID = "SELECT * FROM LESSON";
	private static final String FIND_IMG_BY_LESSON_NO = "SELECT LESSON_PICTURE FROM LESSON WHERE LESSON_NO = ?";
	
	public void update3(String lessonNO,Integer lessonRegistration,Connection con) {

	PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REG);

			pstmt.setInt(1, ++lessonRegistration); // 之後要自己抓教練編號
			pstmt.setString(2, lessonNO);//增加人數

			pstmt.executeUpdate();
			
	} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-lesson");
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
		}

	}	

	public void update4(String lessonNO,Integer lessonRegistration,Connection con) {

	PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REG);

			pstmt.setInt(1, --lessonRegistration);
			pstmt.setString(2, lessonNO);//增加人數

			pstmt.executeUpdate();
			
	} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-lesson");
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
		}

	}		
	
	@Override
	public void insert(LessonVO lessonVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonVO.getCoachNo()); // 之後要自己抓教練編號
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
			pstmt.setInt(12, lessonVO.getLessonClass());			
			pstmt.setInt(13, lessonVO.getLessonStatus());
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
	public void update(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(X, lessonVO.getCoachNo()); //教練更新資料教練編號不可改
			pstmt.setDate(5, lessonVO.getLessonStartDate()); //開放報名日期
//			pstmt.setInt(x, lessonVO.getLessonRegistration()); //已報名人數不可改
			pstmt.setString(1, lessonVO.getLessonName());
			pstmt.setString(2, lessonVO.getLessonPoint());
			pstmt.setInt(3, lessonVO.getLessonPrice());
			pstmt.setString(4, lessonVO.getLessonContent());
			pstmt.setDate(6, lessonVO.getLessonEndDate());
			pstmt.setInt(7, lessonVO.getLessonMaximumPeople());
			
			ByteArrayInputStream bais=new ByteArrayInputStream(lessonVO.getLessonPicture());  
			pstmt.setBinaryStream(8, bais);
			
			pstmt.setString(9, lessonVO.getLessonLocation());
			pstmt.setInt(10, lessonVO.getLessonClass());
			pstmt.setInt(11, lessonVO.getLessonStatus());
			pstmt.setString(12, lessonVO.getLessonNo()); // 輸入課程編號抓table

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
	public void delete2(String lessonNo) {
		int updateCount_LDs = 0;
		int updateCount_FLs = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除明細
			pstmt = con.prepareStatement(DELETE2_LD);
			pstmt.setString(1, lessonNo);
			updateCount_LDs = pstmt.executeUpdate();
			// 再刪除課程收藏
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
			System.out.println("刪除課程:" + lessonNo + "時,共有課程明細" + updateCount_LDs + "筆同時被刪除，以及課程收藏共有"
					+ (updateCount_FLs) + "筆被刪除");
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public LessonVO findByPrimaryKey(String lessonNo) {
		LessonVO lessonVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

				
			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);

				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_mALL_STMT);
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
				
			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);

				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				mlist.add(lessonVO);
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);
				
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonVO);
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
	public List<LessonVO> gethistoryAll() {
		// 課程總覽
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_history_ALL_STMT);
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

			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);
				
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonVO);
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
	
	public List<LessonVO> search(String string) {
		
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO search = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_STMT);
			pstmt.setString(1, string);			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				search = new LessonVO();
				search.setLessonNo(rs.getString("lesson_no"));
				search.setCoachNo(rs.getString("coach_no"));
				search.setLessonName(rs.getString("lesson_name"));
				search.setLessonPrice(rs.getInt("lesson_price"));
				search.setLessonEndDate(rs.getDate("lesson_end_date"));
				search.setLessonRegistration(rs.getInt("lesson_registration"));
				search.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));

			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
			    search.setLessonPicture(bytes);
				
				search.setLessonLocation(rs.getString("lesson_location"));
				search.setLessonClass(rs.getInt("lesson_class"));
				search.setLessonStatus(rs.getInt("lesson_status"));
				list.add(search); // Store the row in the list
			}

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
	public void insertWithDetails(LessonVO lessonVO, List<LessonDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先課程
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
			pstmt.setInt(12, lessonVO.getLessonClass());
			pstmt.setInt(13, lessonVO.getLessonStatus());
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
			// 再同時新增明細
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
			System.out.println("新增課程編號" + next_lessonNo + "時,共有lessonDate" + list.size()
					+ "日同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-lessonDetail");
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
	public List<LessonVO> getAll(Map<String, String[]> map) {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from lesson where "
		          + CompositeQuery_for_Lesson.get_WhereCondition(map)
		          + "AND lesson_end_date>=sysdate order by lesson_end_date";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				lessonVO = new LessonVO();
				lessonVO.setLessonNo(rs.getString("lesson_no"));
				lessonVO.setCoachNo(rs.getString("coach_no"));
				lessonVO.setLessonName(rs.getString("lesson_name"));
				lessonVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonVO.setLessonEndDate(rs.getDate("lesson_end_date"));
				lessonVO.setLessonRegistration(rs.getInt("lesson_registration"));
				lessonVO.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));

			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);
				
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonVO); // Store the row in the List
			}
	
			// Handle any SQL errors
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

	@Override
	public List<LessonVO> getLessonByCoach(String coachNo) {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_STMT);
			pstmt.setString(1, coachNo);
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

			    Blob blob = rs.getBlob("lesson_picture");
			    byte [] bytes = blob.getBytes(1l, (int)blob.length());			       
				lessonVO.setLessonPicture(bytes);
				
				lessonVO.setLessonLocation(rs.getString("lesson_location"));
				lessonVO.setLessonClass(rs.getInt("lesson_class"));
				lessonVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonVO);
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
	public List<LessonVO> getLessonDateByCoach(String coachNo) {
		List<LessonVO> list = new ArrayList<LessonVO>();
		LessonVO lessonVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COACH_STMT);
			pstmt.setString(1, coachNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lessonVO = new LessonVO();
				lessonVO.setLessonNo(rs.getString("lesson_no"));
				lessonVO.setLessonName(rs.getString("lesson_name"));

				list.add(lessonVO);
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
	
//	@Override  //****未完成**** (同時更新課程明細)
	public void update2(LessonVO lessonVO, List<LessonDetailVO> list) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//    		con.setAutoCommit(false);
//			
//    		// 先課程
//			String cols[] = {"lesson_no"};
//			pstmt = con.prepareStatement(UPDATE , cols);	
//			pstmt = con.prepareStatement(UPDATE);
//
////			pstmt.setString(X, lessonVO.getCoachNo()); //教練更新資料教練編號不可改
////			pstmt.setDate(X, lessonVO.getLessonStartDate()); //課程上傳日期不因更新而變動
////			pstmt.setInt(x, lessonVO.getLessonRegistration()); //已報名人數不可改
//			pstmt.setString(1, lessonVO.getLessonName());
//			pstmt.setString(2, lessonVO.getLessonPoint());
//			pstmt.setInt(3, lessonVO.getLessonPrice());
//			pstmt.setString(4, lessonVO.getLessonContent());
//			pstmt.setDate(5, lessonVO.getLessonEndDate());
//			pstmt.setInt(6, lessonVO.getLessonMaximumPeople());
//			
//			ByteArrayInputStream bais=new ByteArrayInputStream(lessonVO.getLessonPicture());  
//			pstmt.setBinaryStream(7, bais);
//			
//			pstmt.setString(8, lessonVO.getLessonLocation());
//			pstmt.setInt(9, lessonVO.getLessonClass());
//			pstmt.setInt(10, lessonVO.getLessonStatus());
//			pstmt.setString(11, lessonVO.getLessonNo()); // 輸入課程編號抓table
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
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
	}

	@Override
	public void updateLessonStatus(LessonVO lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPIC);
		
			pstmt.setInt(1, lessonVO.getLessonStatus());
			pstmt.setString(2, lessonVO.getLessonNo());
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
	
	public void updateLessonPic(List<LessonVO> lessonVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPIC);
		for(LessonVO lessonpic:lessonVO) {
			pstmt.setBytes(1, lessonpic.getLessonPicture());
			pstmt.setString(2, lessonpic.getLessonNo());
			pstmt.executeUpdate();
		}

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
	
	// Android---------------------------------------
		@Override
		public LessonVO findByNoForAndroid(String lessonNo) {

			LessonVO lesson = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BY_LESSON_NO);

				pstmt.setString(1, lessonNo);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					lesson = new LessonVO();

					lesson.setLessonNo(rs.getString("lesson_no"));
					lesson.setCoachNo(rs.getString("coach_no"));
					lesson.setLessonName(rs.getString("lesson_name"));
					lesson.setLessonPrice(rs.getInt("lesson_price"));
					lesson.setLessonEndDate(rs.getDate("lesson_end_date"));
					lesson.setLessonRegistration(rs.getInt("lesson_registration"));
					lesson.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
					lesson.setLessonLocation(rs.getString("lesson_location"));
					lesson.setLessonClass(rs.getInt("lesson_class"));
					lesson.setLessonStatus(rs.getInt("lesson_status"));

				}

				// Handle any driver errors
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
			return lesson;
		}

		@Override
		public List<LessonVO> getAllForAndroid() {
			List<LessonVO> list = new ArrayList<LessonVO>();
			LessonVO lesson = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_ANDROID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					lesson = new LessonVO();

					lesson.setLessonNo(rs.getString("lesson_no"));
					lesson.setCoachNo(rs.getString("coach_no"));
					lesson.setLessonName(rs.getString("lesson_name"));
					lesson.setLessonPrice(rs.getInt("lesson_price"));
					lesson.setLessonEndDate(rs.getDate("lesson_end_date"));
					lesson.setLessonRegistration(rs.getInt("lesson_registration"));
					lesson.setLessonMaximumPeople(rs.getInt("lesson_maximum_people"));
					lesson.setLessonLocation(rs.getString("lesson_location"));
					lesson.setLessonClass(rs.getInt("lesson_class"));
					lesson.setLessonStatus(rs.getInt("lesson_status"));

					list.add(lesson); // Store the row in the list
				}

				// Handle any driver errors
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
			return list;
		}

		@Override
		public byte[] getImage(String lessonNo) {
			byte[] lessonImg = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_IMG_BY_LESSON_NO);

				pstmt.setString(1, lessonNo);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					lessonImg = rs.getBytes(1);
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

			return lessonImg;
		}
}
