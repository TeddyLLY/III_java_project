package com.product.model;

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

public class ProductJNDIDAO implements ProductDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA105G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = " INSERT INTO PRODUCT (PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION) VALUES ('P'||LPAD(TO_CHAR(PRODUCT_NO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_PHOTO=?, PRODUCT_QUANTITY=?, PRODUCT_PRICE=?, PRODUCT_SALES=?, PRODUCT_CONTENT=?, PRODUCT_TOTAL_EVALUATION=?, PRODUCT_PEOPLE_OF_EVALUATION=?, PRODUCT_STATUS=?, PRODUCT_AVERAGE_EVALUATION=? WHERE PRODUCT_NO = ?";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_NO =?";
	private static final String GET_ALL_STMT = "SELECT PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION FROM PRODUCT";
	private static final String GET_ONE_STMT = "SELECT PRODUCT_NO,PRODUCT_NAME,PRODUCT_PHOTO,PRODUCT_QUANTITY,PRODUCT_PRICE,PRODUCT_SALES,PRODUCT_CONTENT,PRODUCT_TOTAL_EVALUATION,PRODUCT_PEOPLE_OF_EVALUATION,PRODUCT_STATUS,PRODUCT_AVERAGE_EVALUATION FROM PRODUCT WHERE PRODUCT_NO=?";
	private static final String GET_PEOPLE = "SELECT PRODUCT_PEOPLE_OF_EVALUATION FROM PRODUCT WHERE PRODUCT_NO=?";

	
	
	//Android
	 private static final String FIND_BY_PRODUCT_NO = "SELECT * FROM PRODUCT WHERE PRODUCT_NO = ?";
	 private static final String GET_ALL = "SELECT * FROM PRODUCT WHERE PRODUCT_STATUS = 1";
	 private static final String FIND_IMG_BY_PRODUCT_NO = "SELECT PRODUCT_PHOTO FROM PRODUCT WHERE PRODUCT_NO = ?";
	 private static final String FIND_FIVE_BY_PRODUCT_SALES = "SELECT * FROM PRODUCT WHERE PRODUCT_STATUS = 1 AND ROWNUM <= 5 ORDER BY PRODUCT_SALES";
	@Override
	public void updatepic(ProductVO productVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRate(ProductVO productVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductVO> getAllStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findPeopleTotal(String product_no) {
		Integer peopleTotal=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, product_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects

				peopleTotal=rs.getInt("PRODUCT_PEOPLE_OF_EVALUATION");
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
		return peopleTotal;
	}

	@Override
	public String findByProductNo(String product_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
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

			
			con = ds.getConnection();
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
	public void delete(String product_no) {
		int updateCount_record = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, product_no);

			updateCount_record = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除紀錄編號:" + product_no + ":" + updateCount_record + "筆");
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
	public ProductVO findByPrimaryKey(String product_no) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	
	
	//------------------------------------------------Android Start------------------------------------------------
	  @Override
	  public ProductVO findByProNo(String product_no) {
	   
	   ProductVO productVO = null;
	   Connection con = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;

	   try {

	    con = ds.getConnection();
	    pstmt = con.prepareStatement(FIND_BY_PRODUCT_NO);

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
	  public List<ProductVO> getAllFromAndroid() {
	   List<ProductVO> list = new ArrayList<ProductVO>();
	   ProductVO productVO = null;

	   Connection con = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   try {

	    con = ds.getConnection();
	    pstmt = con.prepareStatement(GET_ALL);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {
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
	  public byte[] getImage(String product_no) {
	   byte[] productPic = null;
	   Connection con = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   try {  
	    con = ds.getConnection();
	    pstmt = con.prepareStatement(FIND_IMG_BY_PRODUCT_NO);
	    
	    pstmt.setString(1, product_no);
	    
	    rs = pstmt.executeQuery();
	    
	    if (rs.next()) {
	     productPic = rs.getBytes(1);
	    }
	   
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
	   
	   return productPic;
	  }
	  
	  
	  @Override
	  public List<ProductVO> getfiveFromAndroid() {
	   List<ProductVO> productList = new ArrayList<>();
	   ProductVO product = null;
	   Connection con = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   try {
	    con = ds.getConnection();
	    pstmt = con.prepareStatement(FIND_FIVE_BY_PRODUCT_SALES);
	    
	    rs = pstmt.executeQuery();
	    
	    while (rs.next()) {
	     product = new ProductVO();
	     product.setProduct_no(rs.getString("product_no"));
	     product.setProduct_name(rs.getString("product_name"));
	     product.setProduct_photo(rs.getBytes("product_photo"));
	     product.setProduct_quantity(rs.getInt("product_quantity"));
	     product.setProduct_price(rs.getInt("product_price"));
	     product.setProduct_sales(rs.getInt("product_sales"));
	     product.setProduct_content(rs.getString("product_content"));
	     product.setProduct_total_evaluation(rs.getInt("product_total_evaluation"));
	     product.setProduct_people_of_evaluation(rs.getInt("product_people_of_evaluation"));
	     product.setProduct_status(rs.getInt("product_status"));
	     product.setProduct_average_evaluation(rs.getInt("product_average_evaluation"));
	        
	     productList.add(product);
	    }
	   
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
	   
	   return productList;
	  }
	  //------------------------------------------------Android End  ------------------------------------------------

	@Override
	public List<ProductVO> getAllDown() {
		// TODO Auto-generated method stub
		return null;
	}
}
