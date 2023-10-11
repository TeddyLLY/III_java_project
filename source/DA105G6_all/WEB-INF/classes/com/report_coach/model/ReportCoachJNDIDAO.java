package com.report_coach.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class ReportCoachJNDIDAO implements ReportCoachDAO_interface {
	public ReportCoachJNDIDAO() {
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
	public void insert(ReportCoachVO ReportCoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public void update(ReportCoachVO ReportCoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public void delete(String report_coach_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, report_coach_no);

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
	public ReportCoachVO findByPrimaryKey(String report_coach_no) {
		ReportCoachVO ReportCoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, report_coach_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
		return ReportCoachVO;
	}

	@Override
	public List<ReportCoachVO> getAllSameStatus(Integer report_coach_status) {
		List<ReportCoachVO> list = new ArrayList<ReportCoachVO>();
		ReportCoachVO ReportCoachVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	


		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SAME_STATUS);
			pstmt.setInt(1, report_coach_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportCoachVO  = new ReportCoachVO();
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
	public List<ReportCoachVO> getAll() {
		List<ReportCoachVO> list = new ArrayList<ReportCoachVO>();
		ReportCoachVO ReportCoachVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportCoachVO  = new ReportCoachVO();
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
