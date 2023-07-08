package com.top_up_record.model;

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

public class TopUpRecordDAO implements TopUpRecordDAO_interface {

	public TopUpRecordDAO() {
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
"INSERT INTO TOP_UP_RECORD(TOP_UP_RECORD_NO                      ,MEMBER_NO ,RECORD_TIME ,MONEY_RECORD)"
+"VALUES( 'T'||LPAD(TO_CHAR(TOP_UP_RECORD_NO_SEQ.NEXTVAL),3,'0') ,?         ,?           , ? )";

	private static final String GET_ALL_STMT =
"SELECT TOP_UP_RECORD_NO , MEMBER_NO ,RECORD_TIME ,MONEY_RECORD FROM TOP_UP_RECORD ORDER BY RECORD_TIME DESC";
	private static final String GET_ONE_STMT =
"SELECT TOP_UP_RECORD_NO , MEMBER_NO ,RECORD_TIME ,MONEY_RECORD FROM TOP_UP_RECORD WHERE TOP_UP_RECORD_NO=? ORDER BY RECORD_TIME DESC";	
	private static final String DELETE =
"DELETE FROM TOP_UP_RECORD WHERE TOP_UP_RECORD_NO=?";
	private static final String UPDATE =
"UPDATE TOP_UP_RECORD SET MEMBER_NO=? ,RECORD_TIME=? ,MONEY_RECORD=? WHERE TOP_UP_RECORD_NO=?";
	private static final String FIND_ONE_MEMBER_NO =
"SELECT TOP_UP_RECORD_NO, MEMBER_NO ,RECORD_TIME, MONEY_RECORD FROM TOP_UP_RECORD WHERE MEMBER_NO=? ORDER BY RECORD_TIME DESC";
	

	@Override
	public void insert(TopUpRecordVO TopUpRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, TopUpRecordVO.getMember_no());
			pstmt.setTimestamp(2, TopUpRecordVO.getRecord_time());
			pstmt.setInt(3, TopUpRecordVO.getMoney_record());
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
	public void update(TopUpRecordVO TopUpRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, TopUpRecordVO.getMember_no());
			pstmt.setTimestamp(2, TopUpRecordVO.getRecord_time());
			pstmt.setInt(3, TopUpRecordVO.getMoney_record());
			pstmt.setString(4, TopUpRecordVO.getTop_up_record_no());
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
	public TopUpRecordVO findByPrimaryKey(String top_up_record_no) {
		TopUpRecordVO TopUpRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, top_up_record_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
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
		return TopUpRecordVO;
	}

	@Override
	public List<TopUpRecordVO> findOneMemberRecord(String member_no) {
		List<TopUpRecordVO> list = new ArrayList<TopUpRecordVO>();
		TopUpRecordVO TopUpRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_MEMBER_NO);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
				list.add(TopUpRecordVO);
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
	public List<TopUpRecordVO> getAll() {
		List<TopUpRecordVO> list = new ArrayList<TopUpRecordVO>();
		TopUpRecordVO TopUpRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TopUpRecordVO = new TopUpRecordVO();
				TopUpRecordVO.setTop_up_record_no(rs.getString("TOP_UP_RECORD_NO"));
				TopUpRecordVO.setMember_no(rs.getString("MEMBER_NO"));
				TopUpRecordVO.setRecord_time(rs.getTimestamp("RECORD_TIME"));
				TopUpRecordVO.setMoney_record(rs.getInt("MONEY_RECORD"));
				list.add(TopUpRecordVO);
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
