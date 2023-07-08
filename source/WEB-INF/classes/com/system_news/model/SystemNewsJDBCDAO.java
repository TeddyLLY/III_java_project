package com.system_news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SystemNewsJDBCDAO implements SystemNewsDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT =
"INSERT INTO SYSTEM_NEWS(SYSTEM_NEWS_NO                           , MEMBER_NO , COACH_NO    ,SYSTEM_CONTENT          ,SYSTEM_TIME)"
+"VALUES('S'||LPAD(TO_CHAR(SYSTEM_NEWS_NO_SEQ.NEXTVAL),3,'0')     ,?	      , ? 	        , ?					     ,? )" ;
	private static final String GET_ALL_STMT =
"SELECT SYSTEM_NEWS_NO , MEMBER_NO ,COACH_NO ,SYSTEM_CONTENT ,SYSTEM_TIME FROM SYSTEM_NEWS ORDER BY SYSTEM_TIME DESC";
	private static final String GET_ONE_STMT =
"SELECT SYSTEM_NEWS_NO , MEMBER_NO ,COACH_NO ,SYSTEM_CONTENT ,SYSTEM_TIME FROM SYSTEM_NEWS WHERE SYSTEM_NEWS_NO=? ORDER BY SYSTEM_TIME DESC";
	private static final String DELETE =
"DELETE FROM SYSTEM_NEWS WHERE SYSTEM_NEWS_NO=?";
	private static final String UPDATE =
"UPDATE SYSTEM_NEWS set MEMBER_NO=? ,COACH_NO=? ,SYSTEM_CONTENT=? ,SYSTEM_TIME=? WHERE SYSTEM_NEWS_NO=?";
	private static final String FIND_BY_ONE_MEMBER ="SELECT SYSTEM_NEWS_NO , MEMBER_NO ,COACH_NO ,SYSTEM_CONTENT ,SYSTEM_TIME FROM SYSTEM_NEWS WHERE MEMBER_NO=? ORDER BY SYSTEM_TIME DESC";
	private static final String FIND_BY_ONE_COACH ="SELECT SYSTEM_NEWS_NO , MEMBER_NO ,COACH_NO ,SYSTEM_CONTENT ,SYSTEM_TIME FROM SYSTEM_NEWS WHERE COACH_NO=? ORDER BY SYSTEM_TIME DESC";
	
	@Override
	public List<SystemNewsVO> findByOneMember(String member_no) {
		List<SystemNewsVO> list = new ArrayList<SystemNewsVO>();
		SystemNewsVO SystemNewsVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(FIND_BY_ONE_MEMBER);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SystemNewsVO = new SystemNewsVO();
				SystemNewsVO.setSystem_news_no(rs.getString("SYSTEM_NEWS_NO"));
				SystemNewsVO.setMember_no(rs.getString("MEMBER_NO"));
				SystemNewsVO.setCoach_no(rs.getString("COACH_NO"));
				SystemNewsVO.setSystem_content(rs.getString("SYSTEM_CONTENT"));
				SystemNewsVO.setSystem_time(rs.getTimestamp("SYSTEM_TIME"));
				list.add(SystemNewsVO);
			}
			
		}catch (SQLException e) {
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
	public List<SystemNewsVO> findByOneCoach(String coach_no) {
		List<SystemNewsVO> list = new ArrayList<SystemNewsVO>();
		SystemNewsVO SystemNewsVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(FIND_BY_ONE_COACH);
			pstmt.setString(1, coach_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SystemNewsVO = new SystemNewsVO();
				SystemNewsVO.setSystem_news_no(rs.getString("SYSTEM_NEWS_NO"));
				SystemNewsVO.setMember_no(rs.getString("MEMBER_NO"));
				SystemNewsVO.setCoach_no(rs.getString("COACH_NO"));
				SystemNewsVO.setSystem_content(rs.getString("SYSTEM_CONTENT"));
				SystemNewsVO.setSystem_time(rs.getTimestamp("SYSTEM_TIME"));
				list.add(SystemNewsVO);
			}
			
		}catch (SQLException e) {
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
	public void insert(SystemNewsVO SystemNewsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,SystemNewsVO.getMember_no());
			pstmt.setString(2,SystemNewsVO.getCoach_no());
			pstmt.setString(3,SystemNewsVO.getSystem_content());
			pstmt.setTimestamp(4,SystemNewsVO.getSystem_time());
			pstmt.executeQuery();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao insert error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		} // finally end
	}

	@Override
	public void update(SystemNewsVO SystemNewsVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,SystemNewsVO.getMember_no());
			pstmt.setString(2,SystemNewsVO.getCoach_no());
			pstmt.setString(3,SystemNewsVO.getSystem_content());
			pstmt.setTimestamp(4,SystemNewsVO.getSystem_time());
			pstmt.setString(5,SystemNewsVO.getSystem_news_no());
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
	public void delete(String system_news_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd );
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, system_news_no);
			pstmt.executeQuery();
			con.commit();
			
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
	public SystemNewsVO findByPrimaryKey(String system_news_no) {
		SystemNewsVO SystemNewsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, system_news_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SystemNewsVO = new SystemNewsVO();
				SystemNewsVO.setSystem_news_no(rs.getString("SYSTEM_NEWS_NO"));
				SystemNewsVO.setMember_no(rs.getString("MEMBER_NO"));
				SystemNewsVO.setCoach_no(rs.getString("COACH_NO"));
				SystemNewsVO.setSystem_content(rs.getString("SYSTEM_CONTENT"));
				SystemNewsVO.setSystem_time(rs.getTimestamp("SYSTEM_TIME"));
				
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
		return SystemNewsVO;
	}

	@Override
	public List<SystemNewsVO> getAll() {
		List<SystemNewsVO> list = new ArrayList<SystemNewsVO>();
		SystemNewsVO SystemNewsVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SystemNewsVO = new SystemNewsVO();
				SystemNewsVO.setSystem_news_no(rs.getString("SYSTEM_NEWS_NO"));
				SystemNewsVO.setMember_no(rs.getString("MEMBER_NO"));
				SystemNewsVO.setCoach_no(rs.getString("COACH_NO"));
				SystemNewsVO.setSystem_content(rs.getString("SYSTEM_CONTENT"));
				SystemNewsVO.setSystem_time(rs.getTimestamp("SYSTEM_TIME"));
				list.add(SystemNewsVO);				
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
		SystemNewsJDBCDAO dao = new SystemNewsJDBCDAO();
		Timestamp time= new Timestamp(System.currentTimeMillis());
		//新增
//		SystemNewsVO vo1 = new SystemNewsVO();
//		vo1.setMember_no("M001");
//		vo1.setCoach_no("C001");
//		vo1.setSystem_content("春節快樂");
//		vo1.setSystem_time(time);
//		dao.insert(vo1);
		//刪除
//		dao.delete("S001");
		//修改
//		SystemNewsVO vo2 = new SystemNewsVO();
//		vo2.setMember_no("M001");
//		vo2.setCoach_no("C001");
//		vo2.setSystem_content("春節期間小心駕駛");
//		vo2.setSystem_time(time);
//		vo2.setSystem_news_no("S001");
//		dao.update(vo2);
//		//查詢 DESC
//		SystemNewsVO vo3 = new SystemNewsVO();
//		vo3=dao.findByPrimaryKey("S007");
//		System.out.println(vo3.getSystem_news_no());
//		System.out.println(vo3.getMember_no());
//		System.out.println(vo3.getCoach_no());
//		System.out.println(vo3.getSystem_content());
//		System.out.println(vo3.getSystem_time());
//		System.out.println("-------------------------------------");
//		//查詢 all DESC
//		List<SystemNewsVO> list =dao.getAll();
//		for(SystemNewsVO vo4 :list){
//			System.out.println(vo4.getSystem_news_no());
//			System.out.println(vo4.getMember_no());
//			System.out.println(vo4.getCoach_no());
//			System.out.println(vo4.getSystem_content());
//			System.out.println(vo4.getSystem_time());
//			System.out.println("-->");
//		}
//		
		
		//查詢COACH DESC 
		List<SystemNewsVO> list1 =dao.findByOneCoach("C010");
		for(SystemNewsVO vo5 :list1){
			System.out.println(vo5.getSystem_news_no());
			System.out.println(vo5.getMember_no());
			System.out.println(vo5.getCoach_no());
			System.out.println(vo5.getSystem_content());
			System.out.println(vo5.getSystem_time());
			System.out.println("---------------------");
		}
		//查詢MEMBER DESC
			List<SystemNewsVO> list2 =dao.findByOneMember("M001");
			for(SystemNewsVO vo6 :list2){
				System.out.println(vo6.getSystem_news_no());
				System.out.println(vo6.getMember_no());
				System.out.println(vo6.getCoach_no());
				System.out.println(vo6.getSystem_content());
				System.out.println(vo6.getSystem_time());
				System.out.println("-->");
			}
	}


}
