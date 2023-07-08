package com.lesson_order.model;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lesson.model.LessonJNDIDAO;
import com.lesson.model.LessonService;
import com.lesson.model.LessonVO;
import com.lesson_detail.model.LessonDetailVO;
import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member_lesson_detail.model.MemberLessonDetailJNDIDAO;

public class LessonOrderJNDIDAO implements LessonOrderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO lesson_order (lesson_order_no,member_no,lesson_no,lesson_price,date_acquisition,"
			+ "lesson_status) VALUES ('LO'||LPAD(TO_CHAR(LESSON_ORDER_NO_SEQ.NEXTVAL),3,'0') , ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT lesson_order_no,member_no,lesson_no,lesson_price,TO_char(date_acquisition,'YYYY-MM-DD HH24:MI:SS')date_acquisition,lesson_status "
			+ "FROM lesson_order order by lesson_order_no";
	private static final String GET_ONE_ALL_STMT = "SELECT lesson_order_no,member_no,lesson_no,lesson_price,TO_char(date_acquisition,'YYYY-MM-DD HH24:MI:SS')date_acquisition,lesson_status"
			+ " FROM lesson_order where member_no = ? order by member_no";
	private static final String GET_ALL_MEM_STMT = "SELECT lesson_order_no,member_no,lesson_no,lesson_price,TO_char(date_acquisition,'YYYY-MM-DD HH24:MI:SS')date_acquisition,lesson_status"
			+ " FROM lesson_order where LESSON_no = ? order by date_acquisition";
	private static final String GET_ONE_STMT = "SELECT lesson_order_no,member_no,lesson_no,lesson_price,TO_char(date_acquisition,'YYYY-MM-DD HH24:MI:SS')date_acquisition,lesson_status"
			+ " FROM lesson_order where lesson_order_no = ? ";
	private static final String DELETE = "DELETE FROM lesson_order where lesson_order_no = ?";
	private static final String DELETE2_LO = "DELETE FROM lesson_order where lesson_order_no = ?";
	private static final String DELETE2_LMD = "DELETE FROM member_lesson_detail where lesson_order_no = ?";

	private static final String UPDATE = "UPDATE lesson_order set member_no = ?,lesson_no = ?,lesson_price = ?,date_acquisition = ?,lesson_status = ?"
			+ " where lesson_order_no = ?";

	@Override
	public void insert(LessonOrderVO lessonOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, lessonOrderVO.getMemberNo());
			pstmt.setString(2, lessonOrderVO.getLessonNo());
			pstmt.setInt(3, lessonOrderVO.getLessonPrice());
			pstmt.setTimestamp(4, lessonOrderVO.getDateAcquisition());
			pstmt.setInt(5, lessonOrderVO.getLessonStatus());

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
	public void update(LessonOrderVO lessonOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, lessonOrderVO.getMemberNo());
			pstmt.setString(2, lessonOrderVO.getLessonNo());
			pstmt.setInt(3, lessonOrderVO.getLessonPrice());
			pstmt.setTimestamp(4, lessonOrderVO.getDateAcquisition());
			pstmt.setInt(5, lessonOrderVO.getLessonStatus());
			pstmt.setString(6, lessonOrderVO.getLessonOrderNo());
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
	public void delete(String lessonOrderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
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
	public void delete2(LessonOrderVO lessonOrderVO) {
		int updateCount_LMDs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除明細
			pstmt = con.prepareStatement(DELETE2_LMD);
			pstmt.setString(1, lessonOrderVO.getLessonOrderNo());
			updateCount_LMDs = pstmt.executeUpdate();
			// 再刪除訂單
			pstmt = con.prepareStatement(DELETE2_LO);
			pstmt.setString(1, lessonOrderVO.getLessonOrderNo());

			//修改會員點數
			MemberService memSvc = new MemberService();
			int memberPoint=memSvc.findOnePoints(lessonOrderVO.getMemberNo()).getMember_points();
			memberPoint = memberPoint+lessonOrderVO.getLessonPrice();
			
			MemberDAO dao2 = new MemberDAO();
			dao2.updateOnePointForLesson(memberPoint,lessonOrderVO.getMemberNo(),con);			
			
			//修改課程報名人數
			LessonService lessonSvc = new LessonService();
			LessonVO lessonVO = lessonSvc.getOneLesson(lessonOrderVO.getLessonNo());
			LessonJNDIDAO dao3 =new LessonJNDIDAO();
			dao3.update4(lessonOrderVO.getLessonNo(),lessonVO.getLessonRegistration(),con);//-1
			
			
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除課程訂單:" + lessonOrderVO.getLessonOrderNo() + "時,共有訂單明細" + updateCount_LMDs + "筆同時被刪除");

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
	public LessonOrderVO findByPrimaryKey(String lessonOrderNo) {
		LessonOrderVO lessonOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ALL_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));
				olist.add(lessonOrderVO);
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
		return olist;
	}
	@Override
	public List<LessonOrderVO> getLessonJoinMember(String lessonNo) {
		List<LessonOrderVO> olist = new ArrayList<LessonOrderVO>();
		LessonOrderVO lessonOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEM_STMT);
			pstmt.setString(1, lessonNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));
				olist.add(lessonOrderVO);
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				lessonOrderVO = new LessonOrderVO();
				lessonOrderVO.setLessonOrderNo(rs.getString("lesson_order_no"));
				lessonOrderVO.setMemberNo(rs.getString("member_no"));
				lessonOrderVO.setLessonNo(rs.getString("lesson_no"));
				lessonOrderVO.setLessonPrice(rs.getInt("lesson_price"));
				lessonOrderVO.setDateAcquisition(rs.getTimestamp("date_acquisition"));
				lessonOrderVO.setLessonStatus(rs.getInt("lesson_status"));
				list.add(lessonOrderVO);
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
	public void insert2(LessonOrderVO lessonOrderVO, List<LessonDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			
			String cols[] = {"lesson_order_no"}; //自增主鍵
			pstmt = con.prepareStatement(INSERT_STMT , cols);		
			
			pstmt.setString(1, lessonOrderVO.getMemberNo());
			pstmt.setString(2, lessonOrderVO.getLessonNo());
			pstmt.setInt(3, lessonOrderVO.getLessonPrice());
			pstmt.setTimestamp(4, lessonOrderVO.getDateAcquisition());
			pstmt.setInt(5, lessonOrderVO.getLessonStatus());
			pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			String next_lessonOrderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_lessonOrderNo = rs.getString(1);
			} else {
			}
			rs.close();
			// 再同時新增明細
			MemberLessonDetailJNDIDAO dao = new MemberLessonDetailJNDIDAO();
			for (LessonDetailVO aLessonDetail : list) {
				aLessonDetail.setLessonNo(new String(next_lessonOrderNo)) ;
				dao.insert2(aLessonDetail,con);
			}
			//修改會員點數
			MemberService memSvc = new MemberService();
			int memberPoint=memSvc.findOnePoints(lessonOrderVO.getMemberNo()).getMember_points();
			memberPoint = memberPoint-lessonOrderVO.getLessonPrice();
			
			MemberDAO dao2 = new MemberDAO();
			dao2.updateOnePointForLesson(memberPoint,lessonOrderVO.getMemberNo(),con);
			
			//修改課程報名人數
			LessonService lessonSvc = new LessonService();
			LessonVO lessonVO = lessonSvc.getOneLesson(lessonOrderVO.getLessonNo());
			LessonJNDIDAO dao3 =new LessonJNDIDAO();
			dao3.update3(lessonOrderVO.getLessonNo(),lessonVO.getLessonRegistration(),con);
			
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-lessonOrderDetail");
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
	public void delete2(String lessonOrderNo) {
		// TODO Auto-generated method stub
		
	}

}
