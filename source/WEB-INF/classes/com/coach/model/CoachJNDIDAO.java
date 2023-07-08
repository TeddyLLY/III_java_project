package com.coach.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CoachJNDIDAO implements CoachDAO_interface {

	
	public CoachJNDIDAO() {
		super();
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

	private static final String INSERT_STMT = "INSERT INTO COACH (COACH_NO         ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT   ,COACH_PASSWORD  ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT)"
			+ "VALUES( 'C'||LPAD(TO_CHAR(COACH_NO_SEQ.NEXTVAL),3,'0')   		   ,?          ,?         ,?        	  ,?   			   ,?     			,?             , ?          , ?                     , ?          					,?           ,?           , ?                       , ?           ,?            ,?					,?)";

	private static final String GET_ALL_STMT = "SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH ";

	private static final String GET_ALL_STMT_DESC = "SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH ORDER BY COACH_NO DESC ";

	private static final String GET_ONE_STMT = "SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH WHERE COACH_NO =?";

	private static final String DELETE = "DELETE FROM COACH WHERE COACH_NO =?";

	private static final String UPDATE = "UPDATE COACH SET COACH_SEX = ? , COACH_CELLPHONE =? ,COACH_PASSWORD=? ,COACH_ADDRESS=? ,COACH_PHOTO=? ,COACH_TOTAL_EVALUATION=? ,COACH_TOTAL_PEOPLE_EVALUATION=? ,COACH_REVIEW=? ,COACH_AUTH=? ,COACH_AVERAGE_EVALUATION=? ,COACH_LICENSE=? ,COACH_INCOME=? ,COACH_INTRODUCTION=? ,COACH_BANK_ACCOUNT=? WHERE COACH_NO=?";

	//**修改單一教練狀態  caoch_review coach_auth
	private static final String UPDATE_ONE_STATUS  ="UPDATE COACH SET COACH_REVIEW = ? , COACH_AUTH = ? WHERE COACH_NO = ?";
	//**查詢單一教練收益
	private static final String FIND_ONE_INCOME ="SELECT COACH_INCOME FROM COACH WHERE COACH_NO = ?";	
	
	//查詢教練帳號
	private static final String FIND_ONE_COACH_ACCOUNT = "SELECT * FROM COACH WHERE COACH_ACCOUNT=? ";
	
	
	// Android
		private static final String GET_ONE_COACH_NAME = "SELECT COACH_NAME FROM COACH WHERE COACH_NO =?";
		private static final String FIND_BY_ID_PASWD = "SELECT * FROM COACH WHERE COACH_ACCOUNT = ? AND COACH_PASSWORD = ?";
		
	@Override
	public void insert(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, CoachVO.getCoach_name());
			pstmt.setString(2, CoachVO.getCoach_sex());
			pstmt.setString(3, CoachVO.getCoach_cellphone());
			pstmt.setString(4, CoachVO.getCoach_account());
			pstmt.setString(5, CoachVO.getCoach_password());
			pstmt.setString(6, CoachVO.getCoach_address());
			pstmt.setBytes(7, CoachVO.getCoach_photo());
			pstmt.setInt(8, CoachVO.getCoach_total_evaluation());
			pstmt.setInt(9, CoachVO.getCoach_total_people_evaluation());
			pstmt.setInt(10, CoachVO.getCoach_review());
			pstmt.setInt(11, CoachVO.getCoach_auth());
			pstmt.setDouble(12, CoachVO.getCoach_average_evaluation());
			pstmt.setBytes(13, CoachVO.getCoach_license());
			pstmt.setInt(14, CoachVO.getCoach_income());
			pstmt.setString(15, CoachVO.getCoach_introduction());
			pstmt.setString(16, CoachVO.getCoach_bank_account());

			pstmt.executeUpdate();
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
	public void update(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			 con.setAutoCommit(false);  
			pstmt.setString(1, CoachVO.getCoach_sex());
			pstmt.setString(2, CoachVO.getCoach_cellphone());
			pstmt.setString(3, CoachVO.getCoach_password());
			pstmt.setString(4, CoachVO.getCoach_address());
			pstmt.setBytes(5, CoachVO.getCoach_photo());
			pstmt.setInt(6, CoachVO.getCoach_total_evaluation());
			pstmt.setInt(7, CoachVO.getCoach_total_people_evaluation());
			pstmt.setInt(8, CoachVO.getCoach_review());
			pstmt.setInt(9, CoachVO.getCoach_auth());
			pstmt.setDouble(10, CoachVO.getCoach_average_evaluation());
			pstmt.setBytes(11, CoachVO.getCoach_license());
			pstmt.setInt(12, CoachVO.getCoach_income());
			pstmt.setString(13, CoachVO.getCoach_introduction());
			pstmt.setString(14, CoachVO.getCoach_bank_account());
			pstmt.setString(15, CoachVO.getCoach_no());
			
			
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
	public void delete(String coach_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, coach_no);

			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException("CoachDao delete error . " + e.getMessage());
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
	public CoachVO findByPrimaryKey(String coach_no) {
		CoachVO CoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println(555555);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, coach_no);
			System.out.println("set");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(555555);
				CoachVO = new CoachVO();
				CoachVO.setCoach_no(rs.getString("COACH_NO"));
				CoachVO.setCoach_name(rs.getString("COACH_NAME"));
				CoachVO.setCoach_sex(rs.getString("COACH_SEX"));
				CoachVO.setCoach_cellphone(rs.getString("COACH_CELLPHONE"));
				CoachVO.setCoach_account(rs.getString("COACH_ACCOUNT"));
				CoachVO.setCoach_password(rs.getString("COACH_PASSWORD"));
				CoachVO.setCoach_address(rs.getString("COACH_ADDRESS"));
				CoachVO.setCoach_photo(rs.getBytes("COACH_PHOTO"));
				CoachVO.setCoach_total_evaluation(rs.getInt("COACH_TOTAL_EVALUATION"));
				CoachVO.setCoach_total_people_evaluation(rs.getInt("COACH_TOTAL_PEOPLE_EVALUATION"));
				CoachVO.setCoach_review(rs.getInt("COACH_REVIEW"));
				CoachVO.setCoach_auth(rs.getInt("COACH_AUTH"));
				CoachVO.setCoach_average_evaluation(rs.getDouble("COACH_AVERAGE_EVALUATION"));
				CoachVO.setCoach_license(rs.getBytes("COACH_LICENSE"));
				CoachVO.setCoach_income(rs.getInt("COACH_INCOME"));
				CoachVO.setCoach_introduction(rs.getString("COACH_INTRODUCTION"));
				CoachVO.setCoach_bank_account(rs.getString("COACH_BANK_ACCOUNT"));
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
		return CoachVO;

	}

	@Override
	public List<CoachVO> getAll() {
		List<CoachVO> list =new ArrayList<CoachVO>();
		CoachVO CoachVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CoachVO = new CoachVO();
				CoachVO.setCoach_no(rs.getString("COACH_NO"));
				CoachVO.setCoach_name(rs.getString("COACH_NAME"));
				CoachVO.setCoach_sex(rs.getString("COACH_SEX"));
				CoachVO.setCoach_cellphone(rs.getString("COACH_CELLPHONE"));
				CoachVO.setCoach_account(rs.getString("COACH_ACCOUNT"));
				CoachVO.setCoach_password(rs.getString("COACH_PASSWORD"));
				CoachVO.setCoach_address(rs.getString("COACH_ADDRESS"));
				CoachVO.setCoach_photo(rs.getBytes("COACH_PHOTO"));
				CoachVO.setCoach_total_evaluation(rs.getInt("COACH_TOTAL_EVALUATION"));
				CoachVO.setCoach_total_people_evaluation(rs.getInt("COACH_TOTAL_PEOPLE_EVALUATION"));
				CoachVO.setCoach_review(rs.getInt("COACH_REVIEW"));
				CoachVO.setCoach_auth(rs.getInt("COACH_AUTH"));
				CoachVO.setCoach_average_evaluation(rs.getDouble("COACH_AVERAGE_EVALUATION"));
				CoachVO.setCoach_license(rs.getBytes("COACH_LICENSE"));
				CoachVO.setCoach_income(rs.getInt("COACH_INCOME"));
				CoachVO.setCoach_introduction(rs.getString("COACH_INTRODUCTION"));
				CoachVO.setCoach_bank_account(rs.getString("COACH_BANK_ACCOUNT"));
				list.add(CoachVO);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
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

	@Override
	public void updateOneStatus(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			 con.setAutoCommit(false);  
			 pstmt = con.prepareStatement(UPDATE_ONE_STATUS);
				pstmt.setInt(1, CoachVO.getCoach_review());
				pstmt.setInt(2, CoachVO.getCoach_auth());
				pstmt.setString(3, CoachVO.getCoach_no());
			
			
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
	public CoachVO findOneIncome(String coach_no) {
		CoachVO CoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_INCOME);
			pstmt.setString(1, coach_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CoachVO = new CoachVO();
				CoachVO.setCoach_income(rs.getInt("COACH_INCOME"));
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
		return CoachVO;
		//"SELECT COACH_INCOME FROM COACH WHERE COACH_NO = ?;"
	}


	@Override
	public List<CoachVO> getAllDesc() {
		List<CoachVO> list =new ArrayList<CoachVO>();
		CoachVO CoachVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_DESC);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CoachVO = new CoachVO();
				CoachVO.setCoach_no(rs.getString("COACH_NO"));
				CoachVO.setCoach_name(rs.getString("COACH_NAME"));
				CoachVO.setCoach_sex(rs.getString("COACH_SEX"));
				CoachVO.setCoach_cellphone(rs.getString("COACH_CELLPHONE"));
				CoachVO.setCoach_account(rs.getString("COACH_ACCOUNT"));
				CoachVO.setCoach_password(rs.getString("COACH_PASSWORD"));
				CoachVO.setCoach_address(rs.getString("COACH_ADDRESS"));
				CoachVO.setCoach_photo(rs.getBytes("COACH_PHOTO"));
				CoachVO.setCoach_total_evaluation(rs.getInt("COACH_TOTAL_EVALUATION"));
				CoachVO.setCoach_total_people_evaluation(rs.getInt("COACH_TOTAL_PEOPLE_EVALUATION"));
				CoachVO.setCoach_review(rs.getInt("COACH_REVIEW"));
				CoachVO.setCoach_auth(rs.getInt("COACH_AUTH"));
				CoachVO.setCoach_average_evaluation(rs.getDouble("COACH_AVERAGE_EVALUATION"));
				CoachVO.setCoach_license(rs.getBytes("COACH_LICENSE"));
				CoachVO.setCoach_income(rs.getInt("COACH_INCOME"));
				CoachVO.setCoach_introduction(rs.getString("COACH_INTRODUCTION"));
				CoachVO.setCoach_bank_account(rs.getString("COACH_BANK_ACCOUNT"));
				list.add(CoachVO);
			}
		} catch (SQLException e) {
			e.printStackTrace(System.err);
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

	@Override
	public CoachVO findOneCoachAccount(String coach_account) {
		CoachVO coachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_COACH_ACCOUNT);
			pstmt.setString(1, coach_account);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				coachVO = new CoachVO();
				coachVO.setCoach_no(rs.getString("COACH_NO"));
				coachVO.setCoach_name(rs.getString("COACH_NAME"));
				coachVO.setCoach_sex(rs.getString("COACH_SEX"));
				coachVO.setCoach_cellphone(rs.getString("COACH_CELLPHONE"));
				coachVO.setCoach_account(rs.getString("COACH_ACCOUNT"));
				coachVO.setCoach_password(rs.getString("COACH_PASSWORD"));
				coachVO.setCoach_address(rs.getString("COACH_ADDRESS"));
				coachVO.setCoach_photo(rs.getBytes("COACH_PHOTO"));
				coachVO.setCoach_total_evaluation(rs.getInt("COACH_TOTAL_EVALUATION"));
				coachVO.setCoach_total_people_evaluation(rs.getInt("COACH_TOTAL_PEOPLE_EVALUATION"));
				coachVO.setCoach_review(rs.getInt("COACH_REVIEW"));
				coachVO.setCoach_auth(rs.getInt("COACH_AUTH"));
				coachVO.setCoach_average_evaluation(rs.getDouble("COACH_AVERAGE_EVALUATION"));
				coachVO.setCoach_license(rs.getBytes("COACH_LICENSE"));
				coachVO.setCoach_income(rs.getInt("COACH_INCOME"));
				coachVO.setCoach_introduction(rs.getString("COACH_INTRODUCTION"));
				coachVO.setCoach_bank_account(rs.getString("COACH_BANK_ACCOUNT"));

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
		return coachVO;
		//"SELECT COACH_INCOME FROM COACH WHERE COACH_NO = ?;"
	}

	@Override
	public List<CoachVO> getAny(Map<String, String[]> map) {
		List<CoachVO> list = new ArrayList<>();
		CoachVO coachVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String FINALSQL = " SELECT * FROM COACH " +com.toolclass.JNDIMap.get_CoachCondition(map) +" ORDER  BY COACH_NO";
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINALSQL);
			System.out.println("final sql by coach dao " +FINALSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				coachVO = new CoachVO();
				
				coachVO.setCoach_no(rs.getString("coach_no"));
				coachVO.setCoach_name(rs.getString("coach_name"));
				coachVO.setCoach_sex(rs.getString("coach_sex"));
				coachVO.setCoach_cellphone(rs.getString("coach_cellphone"));
				coachVO.setCoach_account(rs.getString("coach_account"));
				coachVO.setCoach_address(rs.getString("coach_address"));
				coachVO.setCoach_photo(rs.getBytes("coach_photo"));
				coachVO.setCoach_total_evaluation(rs.getInt("coach_total_evaluation"));
				coachVO.setCoach_total_people_evaluation(rs.getInt("coach_total_people_evaluation"));
				coachVO.setCoach_review(rs.getInt("coach_review"));
				coachVO.setCoach_auth(rs.getInt("coach_auth"));
				coachVO.setCoach_average_evaluation(rs.getDouble("coach_average_evaluation"));
				coachVO.setCoach_license(rs.getBytes("coach_license"));
				coachVO.setCoach_income(rs.getInt("coach_income"));
				coachVO.setCoach_introduction(rs.getString("coach_introduction"));
				coachVO.setCoach_bank_account(rs.getString("coach_bank_account"));
				list.add(coachVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() + "DB error");
		
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
			if(pstmt!= null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					e2.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace(System.err);
				}
			}
			
		}
		return list;
	}
	
	
	// Android-------------------------------------------------------------
		public CoachVO findOneName(String coach_no) {
			CoachVO CoachVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_COACH_NAME);
				pstmt.setString(1, coach_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					CoachVO = new CoachVO();
					CoachVO.setCoach_name(rs.getString("COACH_NAME"));
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
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}

			}
			return CoachVO;

		}

		@Override
		public boolean isCoach(String coach_account, String coach_password) {
			Connection conn = null;
			  PreparedStatement ps = null;
			  boolean isCoach = false;
			  try {
			   conn = ds.getConnection();
			   ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			   ps.setString(1, coach_account);
			   ps.setString(2, coach_password);
			   ResultSet rs = ps.executeQuery();
			   isCoach = rs.next();
			   return isCoach;
			  } catch (SQLException e) {
			   e.printStackTrace();
			  } finally {
			   try {
			    if (ps != null) {
			     ps.close();
			    }
			    if (conn != null) {
			     conn.close();
			    }
			   } catch (SQLException e) {
			    e.printStackTrace();
			   }
			  }
			  return isCoach;
		}

		@Override
		public boolean isCoachIdExist(String coach_no) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean insertToAndroid(CoachVO coachVO) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean updateFromAndroid(CoachVO coachVO) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean deleteFromAndroid(String coach_no) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public CoachVO findById(String coach_no) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<CoachVO> getAllFromAndroid() {
			// TODO Auto-generated method stub
			return null;
		}


}
