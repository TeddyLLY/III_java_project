package com.product_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


import com.product_order_detail.model.ProductOrderDetailJDBCDAO;
import com.product_order_detail.model.ProductOrderDetailVO;



public class ProductOrderJDBCDAO implements ProductOrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = " INSERT INTO PRODUCT_ORDER (ORDER_NO,MEMBER_NO,ORDER_RECIPIENT,ORDER_ADDRESS,ORDER_DATE,ORDER_STATUS) VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-P'||LPAD(TO_CHAR(PRODUCT_ORDER_NO_SEQ.NEXTVAL),3,'0'),?,?,?,SYSDATE,0)";
//	private static final String INSERT_STMT = " INSERT INTO PRODUCT_ORDER (ORDER_NO,MEMBER_NO,ORDER_RECIPIENT,ORDER_ADDRESS,ORDER_DATE,ORDER_STATUS) VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-P'||LPAD(TO_CHAR(PRODUCT_ORDER_NO_SEQ.NEXTVAL),3,'0'),'M010',?,?,?,0)";
	
	private static final String DELETE = "DELETE FROM PRODUCT_ORDER WHERE ORDER_NO=?";
	private static final String UPDATE = "UPDATE PRODUCT_ORDER SET ORDER_RECIPIENT=?,ORDER_ADDRESS=? WHERE ORDER_NO=?";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_ORDER WHERE ORDER_NO=?";
	
	@Override
	public ProductOrderVO findById(String order_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrderVO> getAll(String member_no, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addWithProductOrderDetail(ProductOrderVO productOrder,
			List<ProductOrderDetailVO> productOrderDetailList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithProductOrderDetail(ProductOrderVO productorderVO, List<ProductOrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"ORDER_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			pstmt.setString(1, productorderVO.getMember_no());
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productorderVO.getOrder_recipient());
			pstmt.setString(2, productorderVO.getOrder_address());
//			pstmt.setDate(3, productorderVO.getOrder_date());
//			pstmt.setInt(4, productorderVO.getOrder_status());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(ProductOrderVO productorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productorderVO.getOrder_recipient());
			pstmt.setString(2, productorderVO.getOrder_address());
			pstmt.setString(3, productorderVO.getOrder_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + order_no + ":" + updateCount_record + "筆");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		ProductOrderJDBCDAO productorderjdbcdao = new ProductOrderJDBCDAO();
		ProductOrderVO productOrderVoInsertBoth = new ProductOrderVO();
		
//		productOrderVoInsertBoth.setOrder_recipient("許凱鈞");
//		productOrderVoInsertBoth.setOrder_address("霧峰區");
//		productOrderVoInsertBoth.setOrder_date(new java.sql.Date(new GregorianCalendar(2020, 01, 26).getTime().getTime()));
//		productOrderVoInsertBoth.setOrder_status(3);
//		
//		List<ProductOrderDetailVO> testDetailList = new ArrayList<ProductOrderDetailVO>(); // 準備置入員工數人
//		ProductOrderDetailVO orderDetail = new ProductOrderDetailVO();   // 員工POJO1
////		orderDetail.setOrder_no("20200125-P001");
//		orderDetail.setProduct_no("P001");
//		orderDetail.setDetail_unit_price(1000);
//		orderDetail.setDetail_qty(101);
//
//		ProductOrderDetailVO orderDetail2 = new ProductOrderDetailVO();   // 員工POJO1
////		orderDetail.setOrder_no("20200125-P001");
//		orderDetail2.setProduct_no("P010");
//		orderDetail2.setDetail_unit_price(1000);
//		orderDetail2.setDetail_qty(111);
//
//		testDetailList.add(orderDetail);
//		testDetailList.add(orderDetail2);
//		productorderjdbcdao.insertWithProductOrderDetail(productOrderVoInsertBoth, testDetailList);
		
		
		// insert
		ProductOrderVO productOrderVoInsert = new ProductOrderVO();
		productOrderVoInsert.setOrder_recipient("王曉明");
		productOrderVoInsert.setOrder_address("霧峰區");
//		productOrderVoInsert.setOrder_date(new java.sql.Date(new GregorianCalendar(2020, 01, 26).getTime().getTime()));
	
//
		productorderjdbcdao.insert(productOrderVoInsert);

		// update
//		ProductOrderVO productOrderVoUpdate = new ProductOrderVO();
//		productOrderVoUpdate.setOrder_recipient("王小美");
//		productOrderVoUpdate.setOrder_address("大里區");
//		productOrderVoUpdate.setOrder_no("20200125-P002");
//		productorderjdbcdao.update(productOrderVoUpdate);

		// delete
//		ProductOrderVO productOrderVoDelete = new ProductOrderVO();
//		productOrderVoDelete.setOrder_no("20200125-P001");
//
//		productorderjdbcdao.delete(productOrderVoDelete);

		//getOne
//		ProductOrderVO productVOgetone = productorderjdbcdao.findByPrimaryKey("20200126-P006");
//		System.out.print(productVOgetone.getMember_no() + ",");
//		System.out.print(productVOgetone.getOrder_recipient() + ",");
//		System.out.print(productVOgetone.getOrder_address() + ",");
//		System.out.print(productVOgetone.getOrder_date() + ",");
//		System.out.print(productVOgetone.getOrder_status());
//		System.out.println("---------------------");
		
		// getall
//		List<ProductOrderVO> list = productorderjdbcdao.getAll();
//		for (ProductOrderVO aProductOrder : list) {
//			System.out.print(aProductOrder.getOrder_no() + ",");
//			System.out.print(aProductOrder.getMember_no() + ",");
//			System.out.print(aProductOrder.getOrder_recipient() + ",");
//			System.out.print(aProductOrder.getOrder_address() + ",");
//			System.out.print(aProductOrder.getOrder_date() + ",");
//			System.out.print(aProductOrder.getOrder_status());
//
//			System.out.println();
//		}
	}

	@Override
	public List<ProductOrderVO> findByMemberKey(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductOrderVO findByMemberOrderComplete(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
