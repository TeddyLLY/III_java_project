package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.coach.model.CoachVO;

public class MemberJNDIDAO implements MemberDAO_interface {

	public MemberJNDIDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updatePoint(Integer memberPoint, String memberNo) {
		// TODO Auto-generated method stub
		
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

	private static final String INSERT_STMT = "INSERT INTO MEMBER (MEMBER_NO        ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD  ,MEMBER_WING_SPAN)"
			+ "VALUES('M'||LPAD(TO_CHAR(MEMBER_NO_SEQ.NEXTVAL),3,'0') 				 , ?	   	,?           ,?        		   ,?			  ,?       	  		, ?   		    , ?        	  , ?           ,?       	   ,? 		     ,?             ,?		     ,?         	 ,?				,?)";
	private static final String GET_ALL_STMT = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN  FROM MEMBER";
	private static final String GET_ALL_STMT_DESC = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN FROM MEMBER ORDER BY MEMBER_NO DESC";
	private static final String GET_ONE_STMT = "SELECT MEMBER_NO ,MEMBER_NAME ,MEMBER_SEX ,MEMBER_CELLPHONE ,MEMBER_ACCOUNT ,MEMBER_PASSWORD ,MEMBER_ADDRESS ,MEMBER_PHOTO ,MEMBER_POINTS ,MEMBER_HEIGHT ,MEMBER_WEIGHT ,MEMBER_REVIEW ,MEMBER_AUTH ,MEMBER_BODYFAT ,MEMBER_CARD ,MEMBER_WING_SPAN FROM MEMBER WHERE MEMBER_NO=?";
	private static final String DELETE = "DELETE FROM MEMBER WHERE MEMBER_NO =?";
	private static final String UPDATE = "UPDATE MEMBER SET MEMBER_SEX=? ,MEMBER_CELLPHONE =? ,MEMBER_PASSWORD=? ,MEMBER_ADDRESS=? ,MEMBER_PHOTO=? ,MEMBER_POINTS=? ,MEMBER_HEIGHT=? ,MEMBER_WEIGHT=? ,MEMBER_REVIEW=? ,MEMBER_AUTH=? ,MEMBER_BODYFAT=? , MEMBER_CARD =? , MEMBER_WING_SPAN=? WHERE MEMBER_NO=? ";
	private static final String UPDATE_ONE_STATUS = "UPDATE MEMBER SET MEMBER_REVIEW =? , MEMBER_AUTH=? WHERE MEMBER_NO =?";
	private static final String GET_ONE_POINT = "SELECT MEMBER_POINTS FROM MEMBER WHERE MEMBER_NO =?";
	// 查教練帳號
	private static final String FIND_ONE_MEMBER_ACCOUNT = "SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT=? ";
	//-----------------------------------Android Start----------------------------------- 
	 private static final String FIND_BY_ID_PASWD = "SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT = ? AND MEMBER_PASSWORD = ?";
	 private static final String FIND_BY_ID = "SELECT * FROM MEMBER WHERE MEMBER_ACCOUNT = ?";
	 private static final String CHECK_ID_EXIST = "SELECT MEMBER_ACCOUNT FROM MEMBER WHERE MEMBER_ACCOUNT = ?";
	 private static final String MEMBER_POINT_RENEW = "UPDATE MEMBER SET MEMBER_POINTS=? WHERE MEMBER_NO=?";
	//-----------------------------------Android End-------------------------------------
	@Override
	public void updateOneStatus(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_ONE_STATUS);

			pstmt.setInt(1, MemberVO.getMember_review());
			pstmt.setInt(2, MemberVO.getMember_auth());
			pstmt.setString(3, MemberVO.getMember_no());
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
	public void insert(MemberVO MemberVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, MemberVO.getMember_name());
			pstmt.setString(2, MemberVO.getMember_sex());
			pstmt.setString(3, MemberVO.getMember_cellphone());
			pstmt.setString(4, MemberVO.getMember_account());
			pstmt.setString(5, MemberVO.getMember_password());
			pstmt.setString(6, MemberVO.getMember_address());
			pstmt.setBytes(7, MemberVO.getMember_photo());
			pstmt.setInt(8, MemberVO.getMember_points());
			pstmt.setInt(9, MemberVO.getMember_height());
			pstmt.setInt(10, MemberVO.getMember_weight());
			pstmt.setInt(11, MemberVO.getMember_review());
			pstmt.setInt(12, MemberVO.getMember_auth());
			pstmt.setDouble(13, MemberVO.getMember_bodyfat());
			pstmt.setString(14, MemberVO.getMember_card());
			pstmt.setInt(15, MemberVO.getMember_wing_span());
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
	public void update(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(UPDATE);
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
			pstmt.setString(12, MemberVO.getMember_card());
			pstmt.setInt(13, MemberVO.getMember_wing_span());
			pstmt.setString(14, MemberVO.getMember_no());
			pstmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			throw new RuntimeException("memberDAO update error . " + e.getMessage());
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
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_no);
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
	public MemberVO findByPrimaryKey(String member_no) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SPAN"));
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
		return MemberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SPAN"));
				list.add(MemberVO);
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
	public List<MemberVO> getAllDesc() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_DESC);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				MemberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SPAN"));
				list.add(MemberVO);
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
	public MemberVO findOnePoints(String member_no) {
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_POINT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_points(rs.getInt("MEMBER_POINTS"));
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
		} // finally END
		return MemberVO;
	}

	@Override
	public MemberVO findOneMemberAccount(String member_account) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ONE_MEMBER_ACCOUNT);
			pstmt.setString(1, member_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
				memberVO.setMember_wing_span(rs.getInt("MEMBER_WING_SPAN"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A findOneAccount error occured. " + e.getMessage());
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

		} // finally END
		return memberVO;
	}

	@Override
	public List<MemberVO> getAny(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 複合查詢
			String FINALSQL = " SELECT * FROM MEMBER " +com.toolclass.JNDIMap.get_WhereCondition(map)+ "  ORDER  BY MEMBER_NO ";
			System.out.println(9);
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINALSQL);
			System.out.println("final sql by member dao " + FINALSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	
	
	
	//---------------------------------------Android Start------------------------------------------------
	 
	 @Override
	 public boolean insertToAndroid(MemberVO member) {
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  boolean isAdded = false;

	  try {
	   con = ds.getConnection();
	   pstmt = con.prepareStatement(INSERT_STMT);

	   pstmt.setString(1,member.getMember_name());
	   pstmt.setString(2,member.getMember_sex());
	   pstmt.setString(3,member.getMember_cellphone());
	   pstmt.setString(4,member.getMember_account());
	   pstmt.setString(5,member.getMember_password());
	   pstmt.setString(6,member.getMember_address());
	   pstmt.setBytes(7,member.getMember_photo());
	   pstmt.setInt(8,member.getMember_points());
	   pstmt.setInt(9,member.getMember_height());
	   pstmt.setInt(10,member.getMember_weight());
	   pstmt.setInt(11,member.getMember_review());
	   pstmt.setInt(12,member.getMember_auth());
	   pstmt.setDouble(13,member.getMember_bodyfat());
	   pstmt.setString(14,member.getMember_card());
	   
	   
	   int count = pstmt.executeUpdate();
	   isAdded = count > 0 ? true : false;

	   // Handle any driver errors
	  } catch (SQLException se) {
	   throw new RuntimeException("A database error occured. "
	     + se.getMessage());
	   // Clean up JDBC resources
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
	  return isAdded;
	 }
	 
	 
	 public boolean updateFromAndroid(MemberVO member) {
		  Connection con = null;
		  PreparedStatement pstmt = null;
		  boolean isUpdated = false;
		  
		  try {
		   con = ds.getConnection();
		   pstmt = con.prepareStatement(UPDATE);

		   pstmt.setString(1, member.getMember_cellphone());
		   pstmt.setString(2, member.getMember_password());
		   pstmt.setString(3, member.getMember_address());
		   pstmt.setBytes(4, member.getMember_photo());
		   pstmt.setInt(5, member.getMember_points());
		   pstmt.setInt(6, member.getMember_height());
		   pstmt.setInt(7, member.getMember_weight());
		   pstmt.setInt(8, member.getMember_review());
		   pstmt.setInt(9, member.getMember_auth());
		   pstmt.setDouble(10, member.getMember_bodyfat());
		   pstmt.setString(11,member.getMember_card());
		   pstmt.setString(12, member.getMember_no());

		   int count = pstmt.executeUpdate();
		   isUpdated = count > 0 ? true : false;

		   // Handle any driver errors
		  } catch (SQLException se) {
		   throw new RuntimeException("A database error occured. "
		     + se.getMessage());
		   // Clean up JDBC resources
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
		  return isUpdated;
		 }
	
	
	 
	 
	 @Override
	 public boolean deleteFromAndroin(String member_no) {
	  return false;
	 }
	 
	 
	 
	 
	 @Override
	 public MemberVO findById(String member_account) {
	  MemberVO member = null;
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  try {

	   con = ds.getConnection();
	   pstmt = con.prepareStatement(FIND_BY_ID);

	   pstmt.setString(1, member_account);

	   rs = pstmt.executeQuery();

	   while (rs.next()) {
	    member = new MemberVO();
	    
	    member.setMember_no(rs.getString(1));
	    member.setMember_name(rs.getString(2));
	    member.setMember_sex(rs.getString(3));
	    member.setMember_cellphone(rs.getString(4));
	    member.setMember_account(rs.getString(5));
	    member.setMember_password(rs.getString(6));
	    member.setMember_address(rs.getString(7));
	    member.setMember_photo(rs.getBytes(8));
	    member.setMember_points(rs.getInt(9));
	    member.setMember_height(rs.getInt(10));
	    member.setMember_weight(rs.getInt(11));
	    member.setMember_review(rs.getInt(12));
	    member.setMember_auth(rs.getInt(13));
	    member.setMember_bodyfat(rs.getDouble(14));
	    member.setMember_card(rs.getString(15));
	    
	   }
	   // Handle any driver errors
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
	  return member;
	 }
	
	 
	 
	 @Override
	 public List<MemberVO> getAllFromAndroid() {
	  return null;
	 }
	 
	 
	 @Override
	 public boolean isMember(String member_account, String member_password) {
	  Connection conn = null;
	  PreparedStatement ps = null;
	  boolean isMember = false;
	  try {
	   conn = ds.getConnection();
	   ps = conn.prepareStatement(FIND_BY_ID_PASWD);
	   ps.setString(1, member_account);
	   ps.setString(2, member_password);
	   ResultSet rs = ps.executeQuery();
	   isMember = rs.next();
	   return isMember;
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
	  return isMember;
	 }
	 
	 
	 @Override
	 public boolean isUserIdExist(String member_no) {
	  Connection conn = null;
	  PreparedStatement ps = null;
	  boolean isUserIdExist = false;
	  try {
	   conn = ds.getConnection();
	   ps = conn.prepareStatement(CHECK_ID_EXIST);
	   ps.setString(1, member_no);
	   ResultSet rs = ps.executeQuery();
	   isUserIdExist = rs.next();
	   return isUserIdExist;
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
	  return isUserIdExist;
	 }
	//---------------------------------------Android End------------------------------------------------

}
