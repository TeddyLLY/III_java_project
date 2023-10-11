package com.coach.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoachJDBCDAO implements CoachDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO COACH (COACH_NO         ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT   ,COACH_PASSWORD  ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT)"
			+ "VALUES( 'C'||LPAD(TO_CHAR(COACH_NO_SEQ.NEXTVAL),3,'0')   		   ,?          ,?         ,?        	  ,?   			   ,?     			,?             , ?          , ?                     , ?          					,?           ,?           , ?                       , ?           ,?            ,?					,?)";

	private static final String GET_ALL_STMT_DESC = 
			"SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH ORDER BY COACH_NO DESC ";

	private static final String GET_ALL_STMT = 
			"SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH  ";

	private static final String GET_ONE_STMT = 
			"SELECT COACH_NO ,COACH_NAME ,COACH_SEX ,COACH_CELLPHONE,COACH_ACCOUNT ,COACH_PASSWORD ,COACH_ADDRESS ,COACH_PHOTO ,COACH_TOTAL_EVALUATION ,COACH_TOTAL_PEOPLE_EVALUATION ,COACH_REVIEW ,COACH_AUTH ,COACH_AVERAGE_EVALUATION ,COACH_LICENSE ,COACH_INCOME ,COACH_INTRODUCTION ,COACH_BANK_ACCOUNT FROM COACH WHERE COACH_NO =?";

	private static final String DELETE = 
			"DELETE FROM COACH WHERE COACH_NO =?";

	private static final String UPDATE = 
			"UPDATE COACH SET COACH_SEX=? , COACH_CELLPHONE =? ,COACH_PASSWORD=? ,COACH_ADDRESS=? ,COACH_PHOTO=? ,COACH_TOTAL_EVALUATION=? ,COACH_TOTAL_PEOPLE_EVALUATION=? ,COACH_REVIEW=? ,COACH_AUTH=? ,COACH_AVERAGE_EVALUATION=? ,COACH_LICENSE=? ,COACH_INCOME=? ,COACH_INTRODUCTION=? ,COACH_BANK_ACCOUNT=? WHERE COACH_NO=?";

	//**修改單一教練狀態  caoch_review coach_auth
	private static final String UPDATE_ONE_STATUS  =
			"UPDATE COACH SET COACH_REVIEW = ? , COACH_AUTH = ? WHERE COACH_NO = ?";
	//**查詢單一教練收益
	private static final String FIND_ONE_INCOME =
			"SELECT COACH_INCOME FROM COACH WHERE COACH_NO = ?";	
	//查詢教練帳號
	private static final String FIND_ONE_COACH_ACCOUNT = 
			"SELECT * FROM COACH WHERE COACH_ACCOUNT=? ";
	
	//上傳圖片
	private static final String UPDATEPIC = "UPDATE COACH SET COACH_PHOTO=? , COACH_LICENSE=? WHERE COACH_NO = ?";
	
	@Override
	public void insert(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
	public void update(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(UPDATE);
			
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
	public void delete(String coach_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			 con.setAutoCommit(false);  
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, coach_no);
			pstmt.executeUpdate();
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
	public CoachVO findByPrimaryKey(String coach_no) {
		CoachVO CoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, coach_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	@Override
	public List<CoachVO> getAllDesc() {
		List<CoachVO> list =new ArrayList<CoachVO>();
		CoachVO CoachVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	@Override
	public void updateOneStatus(CoachVO CoachVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid ,passwd);
			con.setAutoCommit(false);  
			pstmt = con.prepareStatement(UPDATE_ONE_STATUS);
			pstmt.setInt(1, CoachVO.getCoach_review());
			pstmt.setInt(2, CoachVO.getCoach_auth());
			pstmt.setString(3, CoachVO.getCoach_no());
			
			pstmt.executeUpdate();
			con.commit();
			
//			"UPDATE COACH SET COACH_REVIEW = ? , COACH_AUTH = ? WHERE COACH_NO = ?
		} catch (SQLException e) {
			
			throw new RuntimeException("CoachDao update error . " + e.getMessage());
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
	public CoachVO findOneIncome(String coach_no) {
		CoachVO CoachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ONE_INCOME);
			pstmt.setString(1, coach_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CoachVO = new CoachVO();
				CoachVO.setCoach_income(rs.getInt("COACH_INCOME"));
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
		return CoachVO;
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String FINALSQL = " SELECT * FROM COACH " +com.toolclass.JNDIMap.get_CoachCondition(map) +" ORDER  BY COACH_NO";
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
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	
	@Override
	public CoachVO findOneCoachAccount(String coach_account) {
		CoachVO coachVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return coachVO;
		//"SELECT COACH_INCOME FROM COACH WHERE COACH_NO = ?;"
	}
	
	public void setPic(CoachVO coachVO ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid ,passwd);
			con.setAutoCommit(false);  
			pstmt = con.prepareStatement(UPDATEPIC);
			pstmt.setBytes(1, coachVO.getCoach_photo());
			pstmt.setBytes(2, coachVO.getCoach_license());
			pstmt.setString(3, coachVO.getCoach_no());
			
			pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			throw new RuntimeException(" update pic  error . " + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	public static byte[] picTurnBytes(String path) throws IOException {
		  File file = new File(path);
		  FileInputStream fis = new FileInputStream(file);
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  byte[] buffer = new byte[8192];
		  int i;
		  while ((i = fis.read(buffer)) != -1) {
		   baos.write(buffer, 0, i);
		  }
		  baos.close();
		  fis.close();

		  return baos.toByteArray();
		 }
	
	public static void main (String[] Args) {
		CoachJDBCDAO dao = new CoachJDBCDAO();
		
		//上傳教練照片
		try {
			for(int i = 1 ; i<14 ; i++ ) {
				byte[] photo = null;
				
				photo = picTurnBytes("C:/eclipse-wworkspace1/DA105G6/WebContent/tool/images/good/good" + i +".jfif");
				byte[] license = null;
				license = picTurnBytes("C:/eclipse-wworkspace1/DA105G6/WebContent/tool/images/license.jpg");
				String coach_no;
				if(i<10) {
			     coach_no = "C00"+i;
				}else {
				 coach_no = "C0"+i;
				}
				
				CoachVO coachVO = new CoachVO();
				coachVO.setCoach_license(license);
				coachVO.setCoach_photo(photo);
				coachVO.setCoach_no(coach_no);
				dao.setPic(coachVO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

////////////////////////////////////////////////////////////////////////////////////////		
//		//**修改單一教練狀態  caoch_review coach_auth
//		CoachVO vo5 =new CoachVO();
//		vo5.setCoach_review(1);
//		vo5.setCoach_auth(2);
//		vo5.setCoach_no("C008");
//		dao.updateOneStatus(vo5);
//		
//		// 新增
//			CoachVO vo1 =new CoachVO();
//			vo1.setCoach_name("linux");
//			vo1.setCoach_sex("男");
//			vo1.setCoach_cellphone("0978312546");
//			vo1.setCoach_account("linux468@gmail.com");
//			vo1.setCoach_password("ckok101");
//			vo1.setCoach_address("彰化花壇");
//			vo1.setCoach_photo(null);
//			vo1.setCoach_total_evaluation(250);//漢堡刷起來
//			vo1.setCoach_total_people_evaluation(8888);
//			vo1.setCoach_review(4);
//			vo1.setCoach_auth(1);
//			vo1.setCoach_average_evaluation(4.1);
//			vo1.setCoach_license(null);
//			vo1.setCoach_income(1000);
//			vo1.setCoach_introduction("i am linux hello~");
//			vo1.setCoach_bank_account("999-999-999999");
//			dao.insert(vo1);
//			System.out.println("新增完成");
//		 //修改
//			CoachVO vo2 =new CoachVO();
//			vo2.setCoach_sex("?");
//			vo2.setCoach_cellphone("0227400920");
//			vo2.setCoach_password("ichangethepsw");
//			vo2.setCoach_address("u.s.");
//			vo2.setCoach_photo(null);
//			vo2.setCoach_total_evaluation(250);//漢堡刷起來
//			vo2.setCoach_total_people_evaluation(9999);
//			vo2.setCoach_review(4);
//			vo2.setCoach_auth(1);
//			vo2.setCoach_average_evaluation(4.1);
//			vo2.setCoach_license(null);
//			vo2.setCoach_income(1500);
//			vo2.setCoach_introduction(" hello~ WOeld~");
//			vo2.setCoach_bank_account("666-666-666666");
//			vo2.setCoach_no("C011");
//			
//			dao.update(vo2);
//			System.out.println("修改完成");
//			
//		
//		// 查詢
//			CoachVO vo3 = new CoachVO();
//			vo3 =dao.findByPrimaryKey("C011");
//			System.out.println(vo3.getCoach_no());
//			System.out.println(vo3.getCoach_name());
//			System.out.println(vo3.getCoach_sex());
//			System.out.println(vo3.getCoach_cellphone());
//			System.out.println(vo3.getCoach_account());
//			System.out.println(vo3.getCoach_password());
//			System.out.println(vo3.getCoach_address());
//			System.out.println(vo3.getCoach_photo());
//			System.out.println(vo3.getCoach_total_evaluation());
//			System.out.println(vo3.getCoach_total_people_evaluation());
//			System.out.println(vo3.getCoach_review());
//			System.out.println(vo3.getCoach_auth());
//			System.out.println(vo3.getCoach_average_evaluation());
//			System.out.println(vo3.getCoach_license());
//			System.out.println(vo3.getCoach_income());
//			System.out.println(vo3.getCoach_introduction());
//			System.out.println(vo3.getCoach_bank_account());
//			System.out.println("-------------------------------------單一查詢");
//			
		// 查詢
//			List<CoachVO> list = dao.getAll();
//			for(CoachVO vo4 : list) {
//				System.out.println(vo4.getCoach_no());
//				System.out.println(vo4.getCoach_name());
//				System.out.println(vo4.getCoach_sex());
//				System.out.println(vo4.getCoach_cellphone());
//				System.out.println(vo4.getCoach_account());
//				System.out.println(vo4.getCoach_password());
//				System.out.println(vo4.getCoach_address());
//				System.out.println(vo4.getCoach_photo());
//				System.out.println(vo4.getCoach_total_evaluation());
//				System.out.println(vo4.getCoach_total_people_evaluation());
//				System.out.println(vo4.getCoach_review());
//				System.out.println(vo4.getCoach_auth());
//				System.out.println(vo4.getCoach_average_evaluation());
//				System.out.println(vo4.getCoach_license());
//				System.out.println(vo4.getCoach_income());
//				System.out.println(vo4.getCoach_introduction());
//				System.out.println(vo4.getCoach_bank_account());
//				System.out.println("--");
//			}
			// 查詢Desc
//			List<CoachVO> list1 = dao.getAllDesc();
//			for(CoachVO vo6 : list1) {
//				System.out.println(vo6.getCoach_no());
//				System.out.println(vo6.getCoach_name());
//				System.out.println(vo6.getCoach_sex());
//				System.out.println(vo6.getCoach_cellphone());
//				System.out.println(vo6.getCoach_account());
//				System.out.println(vo6.getCoach_password());
//				System.out.println(vo6.getCoach_address());
//				System.out.println(vo6.getCoach_photo());
//				System.out.println(vo6.getCoach_total_evaluation());
//				System.out.println(vo6.getCoach_total_people_evaluation());
//				System.out.println(vo6.getCoach_review());
//				System.out.println(vo6.getCoach_auth());
//				System.out.println(vo6.getCoach_average_evaluation());
//				System.out.println(vo6.getCoach_license());
//				System.out.println(vo6.getCoach_income());
//				System.out.println(vo6.getCoach_introduction());
//				System.out.println(vo6.getCoach_bank_account());
//				System.out.println("--");
//			}
////				// 刪除
//				dao.delete("C011");
//				System.out.println("刪除完成");
//			
//			CoachVO vo7 = new CoachVO();
//			vo7 = dao.findOneCoachAccount("123@gmail.com");
//			System.out.println(vo7.getCoach_no());
//			System.out.println(vo7.getCoach_name());
//			System.out.println(vo7.getCoach_sex());
//			System.out.println(vo7.getCoach_cellphone());
//			System.out.println(vo7.getCoach_account());
//			System.out.println(vo7.getCoach_password());
//			System.out.println(vo7.getCoach_address());
//			System.out.println(vo7.getCoach_photo());
//			System.out.println(vo7.getCoach_total_evaluation());
//			System.out.println(vo7.getCoach_total_people_evaluation());
//			System.out.println(vo7.getCoach_review());
//			System.out.println(vo7.getCoach_auth());
//			System.out.println(vo7.getCoach_average_evaluation());
//			System.out.println(vo7.getCoach_license());
//			System.out.println(vo7.getCoach_income());
//			System.out.println(vo7.getCoach_introduction());
//			System.out.println(vo7.getCoach_bank_account());
//			System.out.println("--");
//			
//			//**查詢單一教練收益
//			CoachVO vo8 =new CoachVO();
//			vo8 =dao.findOneIncome("C009");
//			System.out.println(vo6.getCoach_income());
			
	}

	@Override
	public CoachVO findOneName(String coach_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCoach(String coach_account, String coach_password) {
		// TODO Auto-generated method stub
		return false;
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