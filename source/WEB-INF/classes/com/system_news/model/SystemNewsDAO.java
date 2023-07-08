package com.system_news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class SystemNewsDAO implements SystemNewsDAO_interface {

	public SystemNewsDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


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
			con =ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public void update(SystemNewsVO SystemNewsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			throw new RuntimeException("CoachDao update error . " + e.getMessage());
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
			con = ds.getConnection();
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, system_news_no);

			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("report_coach_no delete error . " + e.getMessage());
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, system_news_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SystemNewsVO = new SystemNewsVO();
				SystemNewsVO.setSystem_news_no(rs.getString("SYSTEM_NEWS_NO"));
				SystemNewsVO.setMember_no(rs.getString("MEMBER_NO"));
				SystemNewsVO.setCoach_no(rs.getString("COACH_NO"));
				SystemNewsVO.setSystem_content(rs.getString("SYSTEM_CONTENT"));
				SystemNewsVO.setSystem_time(rs.getTimestamp("SYSTEM_TIME"));
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A findByPrimaryKey error occured. " + e.getMessage());
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
			} // if end
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}

			} // if end
		} // finally END
		return SystemNewsVO;
	}

	@Override
	public List<SystemNewsVO> getAll() {
		List<SystemNewsVO> list = new ArrayList<SystemNewsVO>();
		SystemNewsVO SystemNewsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

		} catch (SQLException e) {
			e.printStackTrace(System.err);
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
