package com.product_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product_order_detail.model.ProductOrderDetailDAO;
import com.product_order_detail.model.ProductOrderDetailDAO_interface;
import com.product_order_detail.model.ProductOrderDetailJDBCDAO;
import com.product_order_detail.model.ProductOrderDetailVO;

public class ProductOrderDAO implements ProductOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = " INSERT INTO PRODUCT_ORDER (ORDER_NO,MEMBER_NO,ORDER_RECIPIENT,ORDER_ADDRESS,ORDER_DATE,ORDER_STATUS) VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-P'||LPAD(TO_CHAR(PRODUCT_ORDER_NO_SEQ.NEXTVAL),3,'0'),?,?,?,SYSDATE,0)";
//	private static final String INSERT_STMT = " INSERT INTO PRODUCT_ORDER (ORDER_NO,MEMBER_NO,ORDER_RECIPIENT,ORDER_ADDRESS,ORDER_DATE,ORDER_STATUS) VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-P'||LPAD(TO_CHAR(PRODUCT_ORDER_NO_SEQ.NEXTVAL),3,'0'),'M010',?,?,SYSDATE,0)";
	private static final String DELETE = "DELETE FROM PRODUCT_ORDER WHERE ORDER_NO=?";
	private static final String UPDATE = "UPDATE PRODUCT_ORDER SET ORDER_STATUS=? WHERE ORDER_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_ORDER WHERE ORDER_NO=?";
	private static final String GET_ONE_STMT_BY_MEM = "SELECT * FROM PRODUCT_ORDER WHERE MEMBER_NO=?";
	private static final String GET_ONE_ORDER_COMPLETE = "SELECT * FROM PRODUCT_ORDER WHERE MEMBER_NO=? AND ORDER_STATUS=3";
	
	
	
	//Android
	 private static final String FIND_BY_ID_ANDROID = "SELECT * FROM PRODUCT_ORDER WHERE ORDER_NO = ?";
	 private static final String GET_ALL_ANDROID = "SELECT * FROM PRODUCT_ORDER WHERE ORDER_NO = ? ORDER BY ORDER_DATE DESC";
	 private static final String INSERT_STMT_ANDROID = "INSERT INTO PRODUCT_ORDER (ORDER_NO,MEMBER_NO,ORDER_RECIPIENT,ORDER_ADDRESS,ORDER_DATE,ORDER_STATUS) VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-P'||LPAD(TO_CHAR(PRODUCT_ORDER_NO_SEQ.NEXTVAL),3,'0'),?,?,?,SYSDATE,?)";
	 private static final String GET_BY_DATE_ANDROID = "SELECT * FROM PRODUCT_ORDER WHERE ORDER_NO = ? AND ORDER_DATE BETWEEN ? AND ? ORDER BY ORDER_DATE DESC";
	@Override
	public void insertWithProductOrderDetail(ProductOrderVO productorderVO, List<ProductOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"ORDER_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			pstmt.setString(1,productorderVO.getMember_no());
			pstmt.setString(2, productorderVO.getOrder_recipient());
			pstmt.setString(3, productorderVO.getOrder_address());
			  pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_productOrderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_productOrderNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_productOrderNo +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			ProductOrderDetailJDBCDAO dao = new ProductOrderDetailJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (ProductOrderDetailVO aOrder : list) {
				aOrder.setOrder_no(new String(next_productOrderNo));
				dao.insertOrderDetailByOrder(aOrder,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_productOrderNo + "時,共有訂單明細" + list.size()
					+ "同時被新增");
			
			// Handle any driver errors
		}  catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		
	}
	
	@Override
	public void insert(ProductOrderVO productorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productorderVO.getOrder_recipient());
			pstmt.setString(2, productorderVO.getOrder_address());
			pstmt.setDate(3, productorderVO.getOrder_date());
//			pstmt.setInt(4, productorderVO.getOrder_status());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	}

	@Override
	public void update(ProductOrderVO productorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,productorderVO.getOrder_status());
			System.out.println(12315654);
			System.out.println(productorderVO.getOrder_status());
			pstmt.setString(2, productorderVO.getOrder_no());
			System.out.println(productorderVO.getOrder_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	}

	@Override
	public void delete(String order_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + order_no + ":" + updateCount_record + "筆");

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	}

	@Override
	public ProductOrderVO findByPrimaryKey(String order_no) {

		ProductOrderVO productOrderVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

					
				
				productOrderVo = new ProductOrderVO();
				productOrderVo.setOrder_no(rs.getString("order_no"));
				productOrderVo.setMember_no(rs.getString("member_no"));
				productOrderVo.setOrder_recipient(rs.getString("order_recipient"));
				productOrderVo.setOrder_address(rs.getString("order_address"));
				productOrderVo.setOrder_date(rs.getDate("order_date"));
				productOrderVo.setOrder_status(rs.getInt("order_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return productOrderVo;
	}
	
	@Override
	public List<ProductOrderVO> findByMemberKey(String member_no) {
		List<ProductOrderVO> list = new ArrayList<ProductOrderVO>();
		ProductOrderVO productOrderVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEM);
			pstmt.setString(1, member_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				
				productOrderVo = new ProductOrderVO();
				productOrderVo.setOrder_no(rs.getString("order_no"));
				productOrderVo.setMember_no(rs.getString("member_no"));
				productOrderVo.setOrder_recipient(rs.getString("order_recipient"));
				productOrderVo.setOrder_address(rs.getString("order_address"));
				productOrderVo.setOrder_date(rs.getDate("order_date"));
				productOrderVo.setOrder_status(rs.getInt("order_status"));
				 list.add(productOrderVo);
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}
	
	@Override
	public ProductOrderVO findByMemberOrderComplete(String member_no) {
		ProductOrderVO productOrderVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ORDER_COMPLETE);
			pstmt.setString(1, member_no);
		System.out.println(member_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				
				productOrderVo = new ProductOrderVO();
				productOrderVo.setOrder_no(rs.getString("order_no"));
				productOrderVo.setMember_no(rs.getString("member_no"));
				productOrderVo.setOrder_recipient(rs.getString("order_recipient"));
				productOrderVo.setOrder_address(rs.getString("order_address"));
				productOrderVo.setOrder_date(rs.getDate("order_date"));
				productOrderVo.setOrder_status(rs.getInt("order_status"));
				
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return productOrderVo;
	}
	

	

	

	@Override
	public List<ProductOrderVO> getAll() {
		List<ProductOrderVO> list = new ArrayList<ProductOrderVO>();
		ProductOrderVO productordervo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productordervo = new ProductOrderVO();
				productordervo.setOrder_no(rs.getString("order_no"));
			    productordervo.setMember_no(rs.getString("member_no"));
			    productordervo.setOrder_recipient(rs.getString("order_recipient"));
			    productordervo.setOrder_address(rs.getString("order_address"));
			    productordervo.setOrder_date(rs.getDate("order_date"));
			    productordervo.setOrder_status(rs.getInt("order_status"));
			    list.add(productordervo); // Store the row in the list
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
		return list;
	}
	
	
	 //-------------------------------------Android Start-------------------------------------
	 @Override
	 public ProductOrderVO findById(String order_no) {
	  ProductOrderVO productOrder = null;
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  try {

	   con = ds.getConnection();

	   pstmt = con.prepareStatement(FIND_BY_ID_ANDROID);

	   pstmt.setString(1, order_no);

	   rs = pstmt.executeQuery();

	   while (rs.next()) {
	    productOrder = new ProductOrderVO();
	    productOrder.setOrder_no(rs.getString(1));
	    productOrder.setMember_no(rs.getString(2));
	    productOrder.setOrder_recipient(rs.getNString(3));
	    productOrder.setOrder_address(rs.getString(4));
	    productOrder.setOrder_date(rs.getDate(5));
	    productOrder.setOrder_status(rs.getInt(6));
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
	  return productOrder;
	 }
	 
	 
	 @Override
	 public List<ProductOrderVO> getAll(String member_no, String start, String end) {
	  List<ProductOrderVO> productOrderList = new ArrayList<>();
	  ProductOrderVO productOrder = null;
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  try {

	   con = ds.getConnection();
	   if (start == null || end == null || start.isEmpty()
	     || end.isEmpty()) {
	    pstmt = con.prepareStatement(GET_ALL_ANDROID);
	    pstmt.setString(1, member_no);
	   } else {
	    pstmt = con.prepareStatement(GET_BY_DATE_ANDROID);
	    pstmt.setString(1, member_no);
	    pstmt.setString(2, start);
	    // include the whole day!
	    pstmt.setString(3, end);
	   }
	   rs = pstmt.executeQuery();

	   while (rs.next()) {
	    productOrder = new ProductOrderVO();
	    productOrder.setOrder_no(rs.getNString(1));
	    productOrder.setMember_no(rs.getNString(2));
	    productOrder.setOrder_recipient(rs.getNString(3));
	    productOrder.setOrder_address(rs.getNString(4));
	    productOrder.setOrder_date(rs.getDate(5));
	    productOrder.setOrder_status(rs.getInt(6));
	    productOrderList.add(productOrder);
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
	  return productOrderList;
	 }
	 
	 
	 @Override
	 public String addWithProductOrderDetail(ProductOrderVO productOrder,
	   List<ProductOrderDetailVO> productOrderDetailList) {
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  String next_order_no = null;
	  try {

	   con = ds.getConnection();

	   // 1.設定於pstmt.executeUpdate()之前
	   con.setAutoCommit(false);

	   // 先新增訂單
	   String cols[] = { "order_no" };
	   pstmt = con.prepareStatement(INSERT_STMT_ANDROID, cols);
	   pstmt.setString(1, productOrder.getMember_no());
	   pstmt.setString(2, productOrder.getOrder_recipient());
	   pstmt.setString(3, productOrder.getOrder_address());
	   pstmt.setInt(4, productOrder.getOrder_status());
	   pstmt.executeUpdate();

	   // 取得對應的自增主鍵值
	   ResultSet rs = pstmt.getGeneratedKeys();
	   if (rs.next()) {
	    next_order_no = rs.getString(1);
	    System.out.println("自增主鍵值= " + next_order_no + "(剛新增成功的訂單編號)");
	   } else {
	    System.out.println("未取得自增主鍵值");
	   }
	   rs.close();
	   // 再同時新增訂單明細內容
	   ProductOrderDetailDAO_interface dao = new ProductOrderDetailDAO();
	   System.out.println("oproductOrderDetailList.size()-A= "
	     + productOrderDetailList.size());
	   for (ProductOrderDetailVO productOrderDetail : productOrderDetailList) {
	    productOrderDetail.setOrder_no(new String(next_order_no));
	    dao.addWithProductOrder(productOrderDetail, con);
	   }

	   // 2.設定於pstmt.executeUpdate()之後
	   con.commit();
	   con.setAutoCommit(true);
	   System.out.println("orderItemList.size()-B= "
	     + productOrderDetailList.size());
	   System.out.println("新增訂單編號" + next_order_no + "時，共有明細"
	     + productOrderDetailList.size() + "筆同時被新增");

	   // Handle any driver errors
	  } catch (SQLException se) {
	   if (con != null) {
	    try {
	     // 3.設定於當有exception發生時之catch區塊內
	     System.err.print("Transaction is being ");
	     System.err.println("rolled back-由-OrderItem");
	     con.rollback();
	    } catch (SQLException excep) {
	     throw new RuntimeException("rollback error occured. "
	       + excep.getMessage());
	    }
	   }
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
	  return next_order_no;
	 }
	 //-------------------------------------Android End  -------------------------------------
}
