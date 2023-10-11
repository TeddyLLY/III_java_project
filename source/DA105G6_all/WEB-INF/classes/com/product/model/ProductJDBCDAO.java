package com.product.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ProductJDBCDAO implements ProductDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA105G6";
	String passwd = "123456";

	private static final String INSERT_STMT = " INSERT INTO PRODUCT (PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION) VALUES ('P'||LPAD(TO_CHAR(PRODUCT_NO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_PHOTO=?, PRODUCT_QUANTITY=?, PRODUCT_PRICE=?, PRODUCT_SALES=?, PRODUCT_CONTENT=?, PRODUCT_TOTAL_EVALUATION=?, PRODUCT_PEOPLE_OF_EVALUATION=?, PRODUCT_STATUS=?, PRODUCT_AVERAGE_EVALUATION=? WHERE PRODUCT_NO = ?";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_NO =?";
	private static final String GET_ALL_STMT = "SELECT PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION FROM PRODUCT";
	private static final String GET_ONE_STMT = "SELECT PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String GET_ONE_PRODNAME = "SELECT PRODUCT_NAME FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String GET_PEOPLE = "SELECT PRODUCT_PEOPLE_OF_EVALUATION FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String UPDATERATE = "UPDATE PRODUCT SET PRODUCT_TOTAL_EVALUATION=PRODUCT_TOTAL_EVALUATION+?, PRODUCT_PEOPLE_OF_EVALUATION=PRODUCT_PEOPLE_OF_EVALUATION+1 WHERE PRODUCT_NO = ?";
	private static final String UPDATEPIC = "UPDATE PRODUCT SET PRODUCT_PHOTO=? WHERE PRODUCT_NO = ?";
	
	
	@Override
	public ProductVO findByProNo(String product_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllFromAndroid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getImage(String product_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getfiveFromAndroid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatepic(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEPIC);

			pstmt.setBytes(1, productVO.getProduct_photo());

			pstmt.setString(2, productVO.getProduct_no());

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
	public List<ProductVO> getAllStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRate(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATERATE);

			pstmt.setInt(1, productVO.getProduct_total_evaluation());
			System.out.println(productVO.getProduct_total_evaluation());
			pstmt.setString(2, productVO.getProduct_no());
			System.out.println(productVO.getProduct_no());
			System.out.println("e04us3");
			System.out.println(pstmt);
			pstmt.executeUpdate();
			System.out.println(pstmt);
			System.out.println(12121);
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}catch (SQLException se) {
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
	public Integer findPeopleTotal(String product_no) {
		Integer peopleTotal = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PEOPLE);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				peopleTotal=rs.getInt("PRODUCT_PEOPLE_OF_EVALUATION");
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
		return peopleTotal;
	}

	@Override
	public String findByProductNo(String product_no) {
		String productName=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PRODNAME);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				productName=rs.getString("product_name");
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
		return productName;
	}

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setBytes(2, productVO.getProduct_photo());
			pstmt.setInt(3, productVO.getProduct_quantity());
			pstmt.setInt(4, productVO.getProduct_price());
			pstmt.setInt(5, productVO.getProduct_sales());
			pstmt.setString(6, productVO.getProduct_content());
			pstmt.setInt(7, productVO.getProduct_total_evaluation());
			pstmt.setInt(8, productVO.getProduct_people_of_evaluation());
			pstmt.setInt(9, productVO.getProduct_status());
			pstmt.setInt(10, productVO.getProduct_average_evaluation());

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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setBytes(2, productVO.getProduct_photo());
			pstmt.setInt(3, productVO.getProduct_quantity());
			pstmt.setInt(4, productVO.getProduct_price());
			pstmt.setInt(5, productVO.getProduct_sales());
			pstmt.setString(6, productVO.getProduct_content());
			pstmt.setInt(7, productVO.getProduct_total_evaluation());
			pstmt.setInt(8, productVO.getProduct_people_of_evaluation());
			pstmt.setInt(9, productVO.getProduct_status());
			pstmt.setInt(10, productVO.getProduct_average_evaluation());
			pstmt.setString(11, productVO.getProduct_no());

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
	public void delete(String product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, product_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + product_no + ":" + updateCount_record + "筆");
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
	public ProductVO findByPrimaryKey(String product_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_quantity(rs.getInt("product_quantity"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_sales(rs.getInt("product_sales"));
				productVO.setProduct_content(rs.getString("product_content"));
				productVO.setProduct_total_evaluation(rs.getInt("product_total_evaluation"));
				productVO.setProduct_people_of_evaluation(rs.getInt("product_people_of_evaluation"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setProduct_average_evaluation(rs.getInt("product_average_evaluation"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

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
				productVO = new ProductVO();

				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setProduct_photo(rs.getBytes("product_photo"));
				productVO.setProduct_quantity(rs.getInt("product_quantity"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_sales(rs.getInt("product_sales"));
				productVO.setProduct_content(rs.getString("product_content"));
				productVO.setProduct_total_evaluation(rs.getInt("product_total_evaluation"));
				productVO.setProduct_people_of_evaluation(rs.getInt("product_people_of_evaluation"));
				productVO.setProduct_status(rs.getInt("product_status"));
				productVO.setProduct_average_evaluation(rs.getInt("product_average_evaluation"));

				list.add(productVO); // Store the row in the list
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
		return list;
	}

	public static void main(String[] args) throws IOException {

		ProductJDBCDAO productdao = new ProductJDBCDAO();
//		productdao.findPeopleTotal("P002");
//		System.out.println(productdao.findPeopleTotal("P002"));
		// insert
//		ProductVO productvoinsert = new ProductVO();
//		productvoinsert.setProduct_name("補碳健身餐");
//		byte[] pic = getPictureByteArray("pic/p11.jpg");
//		productvoinsert.setProduct_photo(pic);
//		productvoinsert.setProduct_quantity(100);
//		productvoinsert.setProduct_price(170);
//		productvoinsert.setProduct_sales(100);
//		productvoinsert.setProduct_content("世界");
//		productvoinsert.setProduct_total_evaluation(800);
//		productvoinsert.setProduct_people_of_evaluation(20);
//		productvoinsert.setProduct_status(1);
//		productvoinsert.setProduct_average_evaluation(4);
//
//		productdao.insert(productvoinsert);
//
//		// update
		
		
		byte[] test1 = null;
		byte[] test2 = null;
		byte[] test3 = null;
		byte[] test4 = null;
		byte[] test5 = null;
		byte[] test6 = null;
		byte[] test7 = null;
		byte[] test8 = null;
		byte[] test9 = null;
		byte[] test10 = null;
		
		try {
			test1 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd1.jpg");
			test2 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd2.jpg");
			test3 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd3.jpg");
			test4 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd4.jpg");
			test5 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd5.jpg");
			test6 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd6.jpg");
			test7 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd7.jpg");
			test8 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd8.jpg");
			test9 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd9.jpg");
			test10 = getPictureByteArray(
					"C:/eclipse-wworkspace1/DA105G6/WebContent/front-end/gym_index/images/pd10.jpg");
		} catch (IOException ie) {
			System.out.println(ie);
		}
		ProductVO productvoupdatepic = new ProductVO();
		
		productvoupdatepic.setProduct_no("P001");
		productvoupdatepic.setProduct_photo(test1);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P002");
		productvoupdatepic.setProduct_photo(test2);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P003");
		productvoupdatepic.setProduct_photo(test3);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P004");
		productvoupdatepic.setProduct_photo(test4);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P005");
		productvoupdatepic.setProduct_photo(test5);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P006");
		productvoupdatepic.setProduct_photo(test6);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P007");
		productvoupdatepic.setProduct_photo(test7);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P008");
		productvoupdatepic.setProduct_photo(test8);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P009");
		productvoupdatepic.setProduct_photo(test9);
		productdao.updatepic(productvoupdatepic);
		
		productvoupdatepic.setProduct_no("P010");
		productvoupdatepic.setProduct_photo(test10);
		productdao.updatepic(productvoupdatepic);
		
//		ProductVO productvoupdate = new ProductVO();
//		productvoupdate.setProduct_total_evaluation(500);
//		productvoupdate.setProduct_no("P002");
//		productdao.updateRate(productvoupdate);
//		productvoinsert.setProduct_name("吃飯");
//		productvoinsert.setProduct_quantity(1);
//		productvoinsert.setProduct_no("P008");
//
//		productdao.update(productvoinsert);
//
//		// delete
//		productdao.delete("P004");

		// getOne
//		ProductVO productVOgetone = productdao.findByPrimaryKey("P001");
//		System.out.print(productVOgetone.getProduct_name() + ",");
//		System.out.print(productVOgetone.getProduct_photo() + ",");
//		System.out.print(productVOgetone.getProduct_quantity() + ",");
//		System.out.print(productVOgetone.getProduct_price() + ",");
//		System.out.print(productVOgetone.getProduct_sales() + ",");
//		System.out.print(productVOgetone.getProduct_content() + ",");
//		System.out.print(productVOgetone.getProduct_total_evaluation() + ",");
//		System.out.print(productVOgetone.getProduct_people_of_evaluation() + ",");
//		System.out.print(productVOgetone.getProduct_status() + ",");
//		System.out.print(productVOgetone.getProduct_average_evaluation());
//		System.out.println("---------------------");

		// getAll
//		List<ProductVO> list = productdao.getAll();
//		for (ProductVO aProduct : list) {
//			System.out.print(aProduct.getProduct_no() + ",");
//			System.out.print(aProduct.getProduct_name() + ",");
//			System.out.print(aProduct.getProduct_photo() + ",");
//			System.out.print(aProduct.getProduct_quantity() + ",");
//			System.out.print(aProduct.getProduct_price() + ",");
//			System.out.print(aProduct.getProduct_sales() + ",");
//			System.out.print(aProduct.getProduct_content() + ",");
//			System.out.print(aProduct.getProduct_total_evaluation() + ",");
//			System.out.print(aProduct.getProduct_people_of_evaluation() + ",");
//			System.out.print(aProduct.getProduct_status() + ",");
//			System.out.print(aProduct.getProduct_average_evaluation());
//
//			System.out.println();
//		}
//		productdao.findByProductNo("P001");
//		System.out.println(productdao.findByProductNo("P001"));
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
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

	@Override
	public List<ProductVO> getAllDown() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
