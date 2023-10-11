package com.product_order.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProductOrderJNDIDAO
 */
@WebServlet("/TestProductOrderJNDIDAO")
public class TestProductOrderJNDIDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProductOrderJNDIDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductOrderJNDIDAO productorderjndidao = new ProductOrderJNDIDAO();
		response.setContentType("text/olain; charset=UTF-8");
		PrintWriter out =response.getWriter();
		// insert
		ProductOrderVO productOrderVoInsert = new ProductOrderVO();
		productOrderVoInsert.setOrder_recipient("王曉明");
		productOrderVoInsert.setOrder_address("霧峰區");
		productOrderVoInsert.setOrder_date(new java.sql.Date(new GregorianCalendar(2020, 01, 26).getTime().getTime()));
		productOrderVoInsert.setOrder_status(3);

		productorderjndidao.insert(productOrderVoInsert);

		// update
		ProductOrderVO productOrderVoUpdate = new ProductOrderVO();
		productOrderVoUpdate.setOrder_recipient("王小美");
		productOrderVoUpdate.setOrder_address("大里區");
		productOrderVoUpdate.setOrder_no("20200202-P002");
		productorderjndidao.update(productOrderVoUpdate);

		// delete
		
		productorderjndidao.delete("20200202-P001");
		

		//getOne
		ProductOrderVO productVOgetone = productorderjndidao.findByPrimaryKey("20200202-P006");
		out.print(productVOgetone.getMember_no() + ",");
		out.print(productVOgetone.getOrder_recipient() + ",");
		out.print(productVOgetone.getOrder_address() + ",");
		out.print(productVOgetone.getOrder_date() + ",");
		out.print(productVOgetone.getOrder_status());
		out.println("---------------------");
		
		// getall
		List<ProductOrderVO> list = productorderjndidao.getAll();
		for (ProductOrderVO aProductOrder : list) {
			out.print(aProductOrder.getOrder_no() + ",");
			out.print(aProductOrder.getMember_no() + ",");
			out.print(aProductOrder.getOrder_recipient() + ",");
			out.print(aProductOrder.getOrder_address() + ",");
			out.print(aProductOrder.getOrder_date() + ",");
			out.print(aProductOrder.getOrder_status());

			out.println();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
