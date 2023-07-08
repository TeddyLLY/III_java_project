package com.report_coach.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;


public class ReportCoachJDBCDAO implements ReportCoachDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
"INSERT INTO REPORT_COACH(REPORT_COACH_NO                            ,COACH_NO ,MEMBER_NO ,REPORT_COACH_CONTENT ,REPORT_COACH_TIME   ,REPORT_COACH_STATUS )"
+"VALUES ('RC'||LPAD(TO_CHAR(REPORT_COACH_NO_SEQ.NEXTVAL),3,'0')     ,?  	   ,?		  ,?		            ,?		             ,?)";
	private static final String GET_ALL_STMT =
"SELECT REPORT_COACH_NO , COACH_NO ,MEMBER_NO ,REPORT_COACH_CONTENT , REPORT_COACH_TIME ,REPORT_COACH_STATUS FROM REPORT_COACH ORDER BY REPORT_COACH_TIME DESC";
	private static final String GET_ONE_STMT =
"SELECT REPORT_COACH_NO , COACH_NO ,MEMBER_NO ,REPORT_COACH_CONTENT , REPORT_COACH_TIME ,REPORT_COACH_STATUS FROM REPORT_COACH WHERE REPORT_COACH_NO=? ORDER BY REPORT_COACH_TIME DESC";
	private static final String DELETE =
"DELETE FROM REPORT_COACH WHERE REPORT_COACH_NO =?";
	private static final String UPDATE =
"UPDATE REPORT_COACH SET COACH_NO=? ,MEMBER_NO=? ,REPORT_COACH_CONTENT=? ,REPORT_COACH_TIME=? ,REPORT_COACH_STATUS=? WHERE REPORT_COACH_NO=?";
	private static final String GET_ALL_SAME_STATUS =
"SELECT REPORT_COACH_NO , COACH_NO , MEMBER_NO , REPORT_COACH_CONTENT , REPORT_COACH_TIME ,REPORT_COACH_STATUS FROM REPORT_COACH WHERE REPORT_COACH_STATUS=? ORDER BY REPORT_COACH_TIME DESC ";
	
	
	@Override
	public List<ReportCoachVO> getAllSameStatus(Integer report_coach_status) {
		List<ReportCoachVO> list = new ArrayList<ReportCoachVO>();
		ReportCoachVO ReportCoachVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SAME_STATUS);
			pstmt.setInt(1, report_coach_status);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReportCoachVO = new ReportCoachVO();
				ReportCoachVO.setReport_coach_no(rs.getString("REPORT_COACH_NO"));
				ReportCoachVO.setCoach_no(rs.getString("COACH_NO"));
				ReportCoachVO.setMember_no(rs.getString("MEMBER_NO"));
				ReportCoachVO.setReport_coach_content(rs.getString("REPORT_COACH_CONTENT"));
				ReportCoachVO.setReport_coach_time(rs.getTimestamp("REPORT_COACH_TIME"));
				ReportCoachVO.setReport_coach_status(rs.getInt("REPORT_COACH_STATUS"));
				list.add(ReportCoachVO);	
}
			
		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt != null) {
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

		} // finally END
		return list;
			
	}
	
	@Override
	public void insert(ReportCoachVO ReportCoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,ReportCoachVO.getCoach_no());
			pstmt.setString(2,ReportCoachVO.getMember_no());
			pstmt.setString(3,ReportCoachVO.getReport_coach_content());
			pstmt.setTimestamp(4,ReportCoachVO.getReport_coach_time());
			pstmt.setInt(5,ReportCoachVO.getReport_coach_status());
			pstmt.executeQuery();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao insert error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}//finally end
	}

	@Override
	public void update(ReportCoachVO ReportCoachVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ReportCoachVO.getCoach_no());
			pstmt.setString(2, ReportCoachVO.getMember_no());
			pstmt.setString(3, ReportCoachVO.getReport_coach_content());
			pstmt.setTimestamp(4, ReportCoachVO.getReport_coach_time());
			pstmt.setInt(5, ReportCoachVO.getReport_coach_status());
			pstmt.setString(6, ReportCoachVO.getReport_coach_no());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao insert error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
		} // finally end
	}

	@Override
	public void delete(String report_coach_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd );
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, report_coach_no);
			pstmt.executeQuery();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao delete error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
		} // finally end
	}

	@Override
	public ReportCoachVO findByPrimaryKey(String report_coach_no) {
		ReportCoachVO ReportCoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, report_coach_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReportCoachVO = new ReportCoachVO();
				ReportCoachVO.setReport_coach_no(rs.getString("REPORT_COACH_NO"));
				ReportCoachVO.setCoach_no(rs.getString("COACH_NO"));
				ReportCoachVO.setMember_no(rs.getString("MEMBER_NO"));
				ReportCoachVO.setReport_coach_content(rs.getString("REPORT_COACH_CONTENT"));
				ReportCoachVO.setReport_coach_time(rs.getTimestamp("REPORT_COACH_TIME"));
				ReportCoachVO.setReport_coach_status(rs.getInt("REPORT_COACH_STATUS"));
}
			
		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}if (pstmt != null) {
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

		} // finally END
		return ReportCoachVO;
	}

	@Override
	public List<ReportCoachVO> getAll() {
		
		List<ReportCoachVO> list = new ArrayList<ReportCoachVO>();
		ReportCoachVO ReportCoachVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReportCoachVO = new ReportCoachVO();
				ReportCoachVO.setReport_coach_no(rs.getString("REPORT_COACH_NO"));
				ReportCoachVO.setCoach_no(rs.getString("COACH_NO"));
				ReportCoachVO.setMember_no(rs.getString("MEMBER_NO"));
				ReportCoachVO.setReport_coach_content(rs.getString("REPORT_COACH_CONTENT"));
				ReportCoachVO.setReport_coach_time(rs.getTimestamp("REPORT_COACH_TIME"));
				ReportCoachVO.setReport_coach_status(rs.getInt("REPORT_COACH_STATUS"));
				
				list.add(ReportCoachVO);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}finally {
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
		ReportCoachJDBCDAO dao = new ReportCoachJDBCDAO();
		Timestamp time= new Timestamp(System.currentTimeMillis());
		//新增
//		ReportCoachVO vo1= new ReportCoachVO();
//		vo1.setCoach_no("C003");
//		vo1.setMember_no("M001");
//		vo1.setReport_coach_content("JDBCDAO");
//		vo1.setReport_coach_time(time);
//		vo1.setReport_coach_status(0);
//		dao.insert(vo1);
//		//刪除
////		dao.delete("RC011");
//		//修改
//		ReportCoachVO vo2= new ReportCoachVO();
//		vo2.setCoach_no("C006");
//		vo2.setMember_no("M009");
//		vo2.setReport_coach_content("教練摸魚");
//		vo2.setReport_coach_time(time);
//		vo2.setReport_coach_status(2);
//		vo2.setReport_coach_no("RC001");
//		dao.update(vo2);
//		
//		//查詢
		ReportCoachVO vo3 = new ReportCoachVO();
		vo3 = dao.findByPrimaryKey("RC010");
		System.out.println(vo3.getReport_coach_no());
		System.out.println(vo3.getCoach_no());
		System.out.println(vo3.getMember_no());
		System.out.println(vo3.getReport_coach_content());
		System.out.println(vo3.getReport_coach_time());
		System.out.println(vo3.getReport_coach_status());
		System.out.println("---------------------------------");
		//查詢
		List<ReportCoachVO> list = dao.getAll();
		for(ReportCoachVO vo4 : list) {
			System.out.println(vo4.getReport_coach_no());
			System.out.println(vo4.getCoach_no());
			System.out.println(vo4.getMember_no());
			System.out.println(vo4.getReport_coach_content());
			System.out.println(vo4.getReport_coach_time());
			System.out.println(vo4.getReport_coach_status());
		System.out.println("-->");
		}
		
		//查相同狀態desc
List<ReportCoachVO> list1 = dao.getAllSameStatus(1);
	
		for(ReportCoachVO vo5 : list1) {
		System.out.println(vo5.getReport_coach_no());
		System.out.println(vo5.getCoach_no());
		System.out.println(vo5.getMember_no());
		System.out.println(vo5.getReport_coach_content());
		System.out.println(vo5.getReport_coach_time());
		System.out.println(vo5.getReport_coach_status());
		System.out.println("---------------------------------");
		}
		
		
	}

	
}
