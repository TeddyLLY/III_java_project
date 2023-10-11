package com.product_order_detail.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProductOrderDetailJNDIDAO
 */
@WebServlet("/TestProductOrderDetailJNDIDAO")
public class TestProductOrderDetailJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProductOrderDetailJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert
				ProductOrderDetailJNDIDAO productorderdetailjndidao = new ProductOrderDetailJNDIDAO();
				response.setContentType("text/olain; charset=UTF-8");
				PrintWriter out =response.getWriter();

				ProductOrderDetailVO productorderdetailinsert = new ProductOrderDetailVO();
//				productorderdetailinsert.setOrder_no("20200202-P010");
//				productorderdetailinsert.setProduct_no("P003");
//				productorderdetailinsert.setDetail_unit_price(1000);
//				productorderdetailinsert.setDetail_qty(101);
//		
//				productorderdetailjndidao.insert(productorderdetailinsert);
//
//				// update
//				 ProductOrderDetailVO productorderdetailupdate = new ProductOrderDetailVO();
//				 productorderdetailupdate.setDetail_unit_price(100);
//				 productorderdetailupdate.setDetail_qty(101);
//				 productorderdetailupdate.setOrder_no("20200202-P010");
//				 productorderdetailupdate.setProduct_no("P003");
//				 
//				 productorderdetailjndidao.update(productorderdetailupdate);
//
//				// delete
//				 productorderdetailjndidao.delete("20200202-P006");
				
				// getOne
				List<ProductOrderDetailVO> list = productorderdetailjndidao.findByPrimaryKey("20200209-P011");
				for (ProductOrderDetailVO aproductorderdetailvo : list) {
				out.print(aproductorderdetailvo.getOrder_no() + ",");
				out.print(aproductorderdetailvo.getProduct_no() + ",");
				out.print(aproductorderdetailvo.getDetail_unit_price() + ",");
				out.print(aproductorderdetailvo.getDetail_qty());
				}
				out.println("---------------------");
//
////				 getAll
//				List<ProductOrderDetailVO> list = productorderdetailjndidao.getAll();
//				for (ProductOrderDetailVO aproductorderdetailvo : list) {
//					out.print(aproductorderdetailvo.getOrder_no() + ",");
//					out.print(aproductorderdetailvo.getProduct_no() + ",");
//					out.print(aproductorderdetailvo.getDetail_unit_price() + ",");
//					out.print(aproductorderdetailvo.getDetail_qty() );
//					out.println();
//				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
