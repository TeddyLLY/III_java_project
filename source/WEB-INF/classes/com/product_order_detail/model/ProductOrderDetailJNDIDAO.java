package com.product_order_detail.model;

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

public class ProductOrderDetailJNDIDAO implements ProductOrderDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = " INSERT INTO PRODUCT_ORDER_DETAIL (ORDER_NO,PRODUCT_NO,DETAIL_UNIT_PRICE,DETAIL_QTY) VALUES (?,?,?,?)";
	private static final String DELETE = "DELETE FROM PRODUCT_ORDER_DETAIL WHERE ORDER_NO=?";
	private static final String UPDATE = "UPDATE PRODUCT_ORDER_DETAIL SET DETAIL_UNIT_PRICE=?,DETAIL_QTY=? WHERE ORDER_NO=? AND PRODUCT_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_ORDER_DETAIL";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_ORDER_DETAIL WHERE ORDER_NO=?";
	
	//Android
	 private static final String INSERT_STMT_ANDROID = "INSERT INTO PRODUCT_ORDER_DETAIL(ORDER_NO,PRODUCT_NO,DETAIL_UNIT_PRICE,DETAIL_QTY) VALUES(?,?,?,?)";
	 private static final String FIND_BY_ORDER_ANDROID = "SELECT * FROM orderitem WHERE ORDER_NO = ?";

	@Override
	public List<ProductOrderDetailVO> findByProductNo(String product_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrderDetailByOrder(ProductOrderDetailVO productorderdetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setString(1, productorderdetailVO.getOrder_no());
			pstmt.setString(2, productorderdetailVO.getProduct_no());
			
			System.out.println(productorderdetailVO.getOrder_no());
			System.out.println(productorderdetailVO.getProduct_no());
			System.out.println(productorderdetailVO.getDetail_unit_price());
			System.out.println(productorderdetailVO.getDetail_qty());
			
			pstmt.setInt(3, productorderdetailVO.getDetail_unit_price());
			pstmt.setInt(4, productorderdetailVO.getDetail_qty());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
		}
	}

	@Override
	public void insert(ProductOrderDetailVO productorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productorderdetailVO.getOrder_no());
			pstmt.setString(2, productorderdetailVO.getProduct_no());
			pstmt.setInt(3, productorderdetailVO.getDetail_unit_price());
			pstmt.setInt(4, productorderdetailVO.getDetail_qty());
			pstmt.executeUpdate();

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
	public void update(ProductOrderDetailVO productorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productorderdetailVO.getDetail_unit_price());
			pstmt.setInt(2, productorderdetailVO.getDetail_qty());
			pstmt.setString(3, productorderdetailVO.getOrder_no());
			pstmt.setString(4, productorderdetailVO.getProduct_no());

			pstmt.executeUpdate();

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
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ProductOrderDetailVO> findByPrimaryKey(String order_no) {
		List<ProductOrderDetailVO> list = new ArrayList<ProductOrderDetailVO>();
		ProductOrderDetailVO productorderdetailvo = null;
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
				productorderdetailvo = new ProductOrderDetailVO();
				productorderdetailvo.setOrder_no(rs.getString("order_no"));
				productorderdetailvo.setProduct_no(rs.getString("product_no"));
				productorderdetailvo.setDetail_unit_price(rs.getInt("detail_unit_price"));
				productorderdetailvo.setDetail_qty(rs.getInt("detail_qty"));
				list.add(productorderdetailvo);
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
	public List<ProductOrderDetailVO> getAll() {
		List<ProductOrderDetailVO> list = new ArrayList<ProductOrderDetailVO>();
		ProductOrderDetailVO productorderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productorderdetailVO = new ProductOrderDetailVO();

				productorderdetailVO.setOrder_no(rs.getString("order_no"));
				productorderdetailVO.setProduct_no(rs.getString("product_no"));
				productorderdetailVO.setDetail_unit_price(rs.getInt("detail_unit_price"));
				productorderdetailVO.setDetail_qty(rs.getInt("detail_qty"));

				list.add(productorderdetailVO); // Store the row in the list
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
	
	
	 // -------------------------------------Android Start-------------------------------------
	 @Override
	 public void addWithProductOrder(ProductOrderDetailVO productOrderDetail, Connection con) {
	  PreparedStatement pstmt = null;

	  try {
	   pstmt = con.prepareStatement(INSERT_STMT_ANDROID);

	   pstmt.setString(1, productOrderDetail.getOrder_no());
	   pstmt.setString(2, productOrderDetail.getProduct_no());
	   pstmt.setInt(3, productOrderDetail.getDetail_unit_price());
	   pstmt.setInt(4, productOrderDetail.getDetail_qty());

	   pstmt.executeUpdate();

	   // Handle any SQL errors
	  } catch (SQLException se) {
	   if (con != null) {
	    try {
	     // 3.設定於當有exception發生時之catch區塊內
	     System.err.print("Transaction is being ");
	     System.err.println("rolled back-由-ProductOrder");
	     con.rollback();
	    } catch (SQLException excep) {
	     throw new RuntimeException("rollback error occured. " + excep.getMessage());
	    }
	   }
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
	  }
	 }
	 
	 
	 @Override
	 public List<ProductOrderDetailVO> findByOrderId(String order_no) {
	  List<ProductOrderDetailVO> podList = new ArrayList<>();
	  ProductOrderDetailVO pod = null;
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;

	  try {

	   con = ds.getConnection();
	   pstmt = con.prepareStatement(FIND_BY_ORDER_ANDROID);
	   pstmt.setString(1, order_no);
	   rs = pstmt.executeQuery();

	   while (rs.next()) {
	    pod = new ProductOrderDetailVO();
	    pod.setOrder_no(order_no);
	    pod.setProduct_no(rs.getNString(2));
	    pod.setDetail_unit_price(3);
	    pod.setDetail_qty(4);
	    podList.add(pod);
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
	  return podList;
	 }
	 
	 
	 // -------------------------------------Android End -------------------------------------
	
}
