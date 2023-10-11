package com.member.model;

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
import java.util.TreeMap;

import com.coach.model.CoachVO;

public class MemberJDBCDAO implements MemberDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";
	
	
	
	@Override
	public boolean isMember(String member_account, String member_password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isUserIdExist(String member_no) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertToAndroid(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateFromAndroid(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean deleteFromAndroin(String member_no) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public MemberVO findById(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<MemberVO> getAllFromAndroid() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updatePoint(Integer memberPoint, String memberNo) {
		// TODO Auto-generated method stub
		
	}

	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_NO        ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD  ,MEMBER_WING_SPAN)"
			+ "VALUES('M'||LPAD(TO_CHAR(MEMBER_NO_SEQ.NEXTVAL),3,'0') 				 , ?	   	,?           ,?        		   ,?			  ,?       	  		, ?   		    , ?        	  , ?           ,?       	   ,? 		     ,?             ,?		     ,?         	 ,?				,?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN FROM MEMBER" ;
	private static final String GET_ALL_STMT_DESC = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN FROM MEMBER ORDER BY MEMBER_NO DESC" ;
	private static final String GET_ONE_STMT 	  = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN FROM MEMBER WHERE MEMBER_NO=?" ;
	private static final String DELETE = "DELETE FROM MEMBER WHERE MEMBER_NO =?"; 
	private static final String UPDATE = "UPDATE MEMBER SET MEMBER_SEX=? ,MEMBER_CELLPHONE =? ,MEMBER_PASSWORD=? ,MEMBER_ADDRESS=? ,MEMBER_PHOTO=? ,MEMBER_POINTS=? ,MEMBER_HEIGHT=? ,MEMBER_WEIGHT=? ,MEMBER_REVIEW=? ,MEMBER_AUTH=? ,MEMBER_BODYFAT=? , MEMBER_CARD =? , MEMBER_WING_SPAN=? WHERE MEMBER_NO=? ";
	private static final String UPDATE_ONE_STATUS = "UPDATE MEMBER SET MEMBER_REVIEW =? , MEMBER_AUTH=? WHERE MEMBER_NO =?";
	private static final String GET_ONE_POINT ="SELECT MEMBER_POINTS FROM MEMBER WHERE MEMBER_NO =?";
		//查member帳號
	private static final String FIND_ONE_MEMBER_ACCOUNT ="SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT=? ";
	
	//上傳圖片
		private static final String UPDATEPIC = "UPDATE MEMBER SET MEMBER_PHOTO=? WHERE MEMBER_NO = ?";
		
	@Override
	public MemberVO findOneMemberAccount(String member_account) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid , passwd);
			pstmt = con.prepareStatement(FIND_ONE_MEMBER_ACCOUNT);
			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				
				memberVO.setMember_no(rs.getString("MEMBER_NO"));
				memberVO.setMember_name(rs.getString("MEMBER_NAME"));
				memberVO.setMember_sex(rs.getString("MEMBER_SEX"));
				memberVO.setMember_cellphone(rs.getString("MEMBER_CELLPHONE"));
				memberVO.setMember_account(rs.getString("MEMBER_ACCOUNT"));
				memberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				memberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				memberVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				memberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
				memberVO.setMember_height(rs.getInt("MEMBER_HEIGHT"));
				memberVO.setMember_weight(rs.getInt("MEMBER_WEIGHT"));
				memberVO.setMember_review(rs.getInt("MEMBER_REVIEW"));
				memberVO.setMember_auth(rs.getInt("MEMBER_AUTH"));
				memberVO.setMember_bodyfat(rs.getDouble("MEMBER_BODYFAT"));
				memberVO.setMember_card(rs.getString("MEMBER_CARD"));
				memberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SAPN"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A findOneAccount error occured. " + e.getMessage());
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
		
		
		return memberVO;
	}

	
	@Override
	public void insert(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			 con.setAutoCommit(false); 
			pstmt.setString(1,MemberVO.getMember_name());
			pstmt.setString(2,MemberVO.getMember_sex());
			pstmt.setString(3,MemberVO.getMember_cellphone());
			pstmt.setString(4,MemberVO.getMember_account());
			pstmt.setString(5,MemberVO.getMember_password());
			pstmt.setString(6,MemberVO.getMember_address());
			pstmt.setBytes(7,MemberVO.getMember_photo());
			pstmt.setInt(8,MemberVO.getMember_points());
			pstmt.setInt(9,MemberVO.getMember_height());
			pstmt.setInt(10,MemberVO.getMember_weight());
			pstmt.setInt(11,MemberVO.getMember_review());
			pstmt.setInt(12,MemberVO.getMember_auth());
			pstmt.setDouble(13,MemberVO.getMember_bodyfat());
			pstmt.setString(14,MemberVO.getMember_card());
			pstmt.setInt(15, MemberVO.getMember_wing_span());
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
		}
	}

	@Override
	public void update(MemberVO MemberVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			 con.setAutoCommit(false); 
			pstmt.setString(1, MemberVO.getMember_sex());
			pstmt.setString(2, MemberVO.getMember_cellphone());
			pstmt.setString(3, MemberVO.getMember_password());
			pstmt.setString(4, MemberVO.getMember_address());
			pstmt.setBytes(5, MemberVO.getMember_photo());
			pstmt.setInt(6, MemberVO.getMember_points());
			pstmt.setInt(7, MemberVO.getMember_height());
			pstmt.setInt(8, MemberVO.getMember_weight());
			pstmt.setInt(9, MemberVO.getMember_review());
			pstmt.setInt(10, MemberVO.getMember_auth());
			pstmt.setDouble(11, MemberVO.getMember_bodyfat());
			pstmt.setString(12,MemberVO.getMember_card());
			pstmt.setString(13, MemberVO.getMember_no());
			pstmt.setInt(15, MemberVO.getMember_wing_span());
			
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
	public void delete(String member_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd );
			 con.setAutoCommit(false); 
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_no);
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
	public MemberVO findOnePoints(String member_no) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_POINT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
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
		
		
		return MemberVO;
	}
	
	@Override
	public MemberVO findByPrimaryKey(String member_no) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO = new MemberVO();
				
				MemberVO.setMember_no(rs.getString("MEMBER_NO"));
				MemberVO.setMember_name(rs.getString("MEMBER_NAME"));
				MemberVO.setMember_sex(rs.getString("MEMBER_SEX"));
				MemberVO.setMember_cellphone(rs.getString("MEMBER_CELLPHONE"));
				MemberVO.setMember_account(rs.getString("MEMBER_ACCOUNT"));
				MemberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				MemberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				MemberVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				MemberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
				MemberVO.setMember_height(rs.getInt("MEMBER_HEIGHT"));
				MemberVO.setMember_weight(rs.getInt("MEMBER_WEIGHT"));
				MemberVO.setMember_review(rs.getInt("MEMBER_REVIEW"));
				MemberVO.setMember_auth(rs.getInt("MEMBER_AUTH"));
				MemberVO.setMember_bodyfat(rs.getDouble("MEMBER_BODYFAT"));
				MemberVO.setMember_card(rs.getString("MEMBER_CARD"));
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SAPN"));
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
		
		
		return MemberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_no(rs.getString("MEMBER_NO"));
				MemberVO.setMember_name(rs.getString("MEMBER_NAME"));
				MemberVO.setMember_sex(rs.getString("MEMBER_SEX"));
				MemberVO.setMember_cellphone(rs.getString("MEMBER_CELLPHONE"));
				MemberVO.setMember_account(rs.getString("MEMBER_ACCOUNT"));
				MemberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				MemberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				MemberVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				MemberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
				MemberVO.setMember_height(rs.getInt("MEMBER_HEIGHT"));
				MemberVO.setMember_weight(rs.getInt("MEMBER_WEIGHT"));
				MemberVO.setMember_review(rs.getInt("MEMBER_REVIEW"));
				MemberVO.setMember_auth(rs.getInt("MEMBER_AUTH"));
				MemberVO.setMember_bodyfat(rs.getDouble("MEMBER_BODYFAT"));
				MemberVO.setMember_card(rs.getString("MEMBER_CARD"));
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SAPN"));
				list.add(MemberVO);				
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
	public List<MemberVO> getAllDesc() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_DESC);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_no(rs.getString("MEMBER_NO"));
				MemberVO.setMember_name(rs.getString("MEMBER_NAME"));
				MemberVO.setMember_sex(rs.getString("MEMBER_SEX"));
				MemberVO.setMember_cellphone(rs.getString("MEMBER_CELLPHONE"));
				MemberVO.setMember_account(rs.getString("MEMBER_ACCOUNT"));
				MemberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				MemberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				MemberVO.setMember_photo(rs.getBytes("MEMBER_PHOTO"));
				MemberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
				MemberVO.setMember_height(rs.getInt("MEMBER_HEIGHT"));
				MemberVO.setMember_weight(rs.getInt("MEMBER_WEIGHT"));
				MemberVO.setMember_review(rs.getInt("MEMBER_REVIEW"));
				MemberVO.setMember_auth(rs.getInt("MEMBER_AUTH"));
				MemberVO.setMember_bodyfat(rs.getDouble("MEMBER_BODYFAT"));
				MemberVO.setMember_card(rs.getString("MEMBER_CARD"));
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SAPN"));
				list.add(MemberVO);				
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
	public void updateOneStatus(MemberVO MemberVO) {
	
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ONE_STATUS);
			 con.setAutoCommit(false); 
			pstmt.setInt(1, MemberVO.getMember_review());
			pstmt.setInt(2, MemberVO.getMember_auth());
			pstmt.setString(3, MemberVO.getMember_no());
			
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
	public List<MemberVO> getAny(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<>();
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//萬用查詢
			String FINALSQL = " SELECT * FROM MEMBER " +com.toolclass.JNDIMap.get_WhereCondition(map)+ "  ORDER  BY MEMBER_NO ";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FINALSQL);
			System.out.println("final sql by member dao" + FINALSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMember_account(rs.getString("member_account"));
				memberVO.setMember_address(rs.getString("member_address"));
				memberVO.setMember_auth(rs.getInt("member_auth"));
				memberVO.setMember_bodyfat(rs.getDouble("member_bodyfat"));
				memberVO.setMember_card(rs.getString("member_card"));
				memberVO.setMember_cellphone(rs.getString("member_cellphone"));
				memberVO.setMember_height(rs.getInt("member_height"));
				memberVO.setMember_name(rs.getString("member_name"));
				memberVO.setMember_no(rs.getString("member_no"));
				memberVO.setMember_password(rs.getString("member_password"));
				memberVO.setMember_photo(rs.getBytes("member_photo"));
				memberVO.setMember_points(rs.getInt("member_points"));
				memberVO.setMember_review(rs.getInt("member_review"));
				memberVO.setMember_sex(rs.getString("member_sex"));
				memberVO.setMember_weight(rs.getInt("member_weight"));
				list.add(memberVO);
			}
			
		}catch (SQLException | ClassNotFoundException se) {
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
	
	public void setPic(MemberVO memberVO ) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid ,passwd);
			con.setAutoCommit(false);  
			pstmt = con.prepareStatement(UPDATEPIC);
			pstmt.setBytes(1, memberVO.getMember_photo());
			pstmt.setString(2, memberVO.getMember_no());
			
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
	
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		//上傳會員照片
		try {
			for(int i = 1 ; i<15 ; i++ ) {
				byte[] photo = null;
				photo = picTurnBytes("C:/eclipse-wworkspace1/DA105G6/WebContent/tool/images/good/member" + i +".jfif");
				String member_no;
				if(i<10) {
					member_no = "M00"+i;
				}else {
					member_no = "M0"+i;
				}
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_photo(photo);
				memberVO.setMember_no(member_no);
				dao.setPic(memberVO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//新增
//		MemberVO vo1 = new MemberVO();
//		vo1.setMember_name("葉問");
//		vo1.setMember_sex("男");
//		vo1.setMember_cellphone("0943871254");
//		vo1.setMember_account("fighttoten@gmail.com");
//		vo1.setMember_password("8525301");
//		vo1.setMember_address("廣東");
//		vo1.setMember_photo(null);
//		vo1.setMember_points(1700);
//		vo1.setMember_height(183);
//		vo1.setMember_weight(80);
//		vo1.setMember_review(0);
//		vo1.setMember_auth(0);
//		vo1.setMember_bodyfat(5.6);
//		vo1.setMember_card("1111-2222-3333-4444");
//		dao.insert(vo1);
//		
//		//修改
//		
		
		
//		MemberVO vo2 = new MemberVO();
//		vo2.setMember_sex("?");
//		vo2.setMember_cellphone("0943521068");
//		vo2.setMember_password("123456");
//		vo2.setMember_address("新竹竹北");
//		vo2.setMember_photo(null);
//		vo2.setMember_points(3000);
//		vo2.setMember_height(190);
//		vo2.setMember_weight(91);
//		vo2.setMember_review(0);
//		vo2.setMember_auth(2);
//		vo2.setMember_bodyfat(4.4);
//		vo2.setMember_card("2222-3333-4444-5555");
//		vo2.setMember_no("M001");
//		dao.update(vo2);
//		
//		
//		
//		//查詢
//		MemberVO vo3 =new MemberVO();
//		vo3 = dao.findByPrimaryKey("M010");
//		System.out.println(vo3.getMember_no());
//		System.out.println(vo3.getMember_name());
//		System.out.println(vo3.getMember_sex());
//		System.out.println(vo3.getMember_cellphone());
//		System.out.println(vo3.getMember_account());
//		System.out.println(vo3.getMember_password());
//		System.out.println(vo3.getMember_address());
//		System.out.println(vo3.getMember_photo());
//		System.out.println(vo3.getMember_points());
//		System.out.println(vo3.getMember_height());
//		System.out.println(vo3.getMember_weight());
//		System.out.println(vo3.getMember_review());
//		System.out.println(vo3.getMember_auth());
//		System.out.println(vo3.getMember_bodyfat());
//		System.out.println(vo3.getMember_card());
//		System.out.println("--------------------------------------------------------------------------");
//		
		
		//查詢 all desc
//		List<MemberVO> list1 = dao.getAllDesc();
//		for(MemberVO vo7 : list1) {
//			System.out.println(vo7.getMember_no());
//			System.out.println(vo7.getMember_name());
//			System.out.println(vo7.getMember_sex());
//			System.out.println(vo7.getMember_cellphone());
//			System.out.println(vo7.getMember_account());
//			System.out.println(vo7.getMember_password());
//			System.out.println(vo7.getMember_address());
//			System.out.println(vo7.getMember_photo());
//			System.out.println(vo7.getMember_points());
//			System.out.println(vo7.getMember_height());
//			System.out.println(vo7.getMember_weight());
//			System.out.println(vo7.getMember_review());
//			System.out.println(vo7.getMember_auth());
//			System.out.println(vo7.getMember_bodyfat());
//			System.out.println(vo7.getMember_card());
//			System.out.println("-->");
//		}
//		
////		//刪除
////		
////		dao.delete("M011");
//		
//		
//		//查詢 all
//		List<MemberVO> list = dao.getAll();
//		for(MemberVO vo4 : list) {
//			System.out.println(vo4.getMember_no());
//			System.out.println(vo4.getMember_name());
//			System.out.println(vo4.getMember_sex());
//			System.out.println(vo4.getMember_cellphone());
//			System.out.println(vo4.getMember_account());
//			System.out.println(vo4.getMember_password());
//			System.out.println(vo4.getMember_address());
//			System.out.println(vo4.getMember_photo());
//			System.out.println(vo4.getMember_points());
//			System.out.println(vo4.getMember_height());
//			System.out.println(vo4.getMember_weight());
//			System.out.println(vo4.getMember_review());
//			System.out.println(vo4.getMember_auth());
//			System.out.println(vo4.getMember_bodyfat());
//			System.out.println(vo4.getMember_card());
//			System.out.println("-->");
//		}
		//查詢會員狀態
//		MemberVO vo5 = new MemberVO();
//	
//		vo5.setMember_review(0);
//		vo5.setMember_auth(2);
//
//		vo5.setMember_no("M001");
//		dao.updateOneStatus(vo5);
		
//		MemberVO vo6 =new MemberVO();
//		vo6 = dao.findOnePoints("M007");
//		System.out.println(vo6.getMember_points());
//		System.out.println("--------------------------------------------------------------------------");
		
//		MemberVO vo7 = new MemberVO();
//		vo7=dao.findOneMemberAccount("123@gmail.com");
//		System.out.println(vo7.getMember_no());
//		System.out.println(vo7.getMember_name());
//		System.out.println(vo7.getMember_sex());
//		System.out.println(vo7.getMember_cellphone());
//		System.out.println(vo7.getMember_account());
//		System.out.println(vo7.getMember_password());
//		System.out.println(vo7.getMember_address());
//		System.out.println(vo7.getMember_photo());
//		System.out.println(vo7.getMember_points());
//		System.out.println(vo7.getMember_height());
//		System.out.println(vo7.getMember_weight());
//		System.out.println(vo7.getMember_review());
//		System.out.println(vo7.getMember_auth());
//		System.out.println(vo7.getMember_bodyfat());
//		System.out.println(vo7.getMember_card());
//		System.out.println("--------------------------------------------------------------------------");
		
	}


}
