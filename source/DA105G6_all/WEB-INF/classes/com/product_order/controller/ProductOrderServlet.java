package com.product_order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_order.model.ProductOrderService;
import com.product_order.model.ProductOrderVO;

/**
 * Servlet implementation class ProductOrderServlet
 */
@WebServlet("/ProductOrderServlet")
public class ProductOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("showWindow".equals(action)) {

			try {
				// Retrieve form parameters.
				
				List<String> list = new ArrayList<String>();
				list.add(req.getParameter("order_no"));
				list.add(req.getParameter("product_no"));
				
				req.getSession().setAttribute("commentList", list);
				
				// Bootstrap_modal
				boolean openModal = true;
				req.setAttribute("openModal", openModal);

				// 取出的empVO送給listOneEmp.jsp
//				String url = "/back-end/product_Order/addProduct_star.jsp";
				String url = "/front-end/member/member_shoppinglist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("get_One_For_Update_Status".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = req.getParameter("order_no");
				/**************************** 2.開始查詢資料，永續層存取 *****************************************/
				ProductOrderService productOrderSvc = new ProductOrderService();
				ProductOrderVO productOrderVO = productOrderSvc.getOneProductOrder(order_no);
				/**************************** 3.查詢完成後判斷都沒有錯誤的時候就會將正確的資料,準備轉交給view---->JSP(Send the Success view) *************/
				req.setAttribute("ProductOrderVO", productOrderVO);
				String url = "/back-end/product_Order/product_order_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);
				}catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_Order/product_order_basic.jsp");
					failureView.forward(req, res);
				}
			}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = req.getParameter("order_no");
				System.out.println(order_no);
				Integer order_status = new Integer(req.getParameter("order_status"));
				System.out.println(order_status);
				/**************************** 2.開始查詢資料，永續層存取 *****************************************/
				ProductOrderService productOrderSvc = new ProductOrderService();
				ProductOrderVO productOrderVO = productOrderSvc.updateProductOrder(order_status,order_no);
				/**************************** 3.查詢完成後判斷都沒有錯誤的時候就會將正確的資料,準備轉交給view---->JSP(Send the Success view) *************/
				req.setAttribute("ProductOrderVO", productOrderVO);
				String url = "/back-end/product_Order/product_order_basic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_Order/product_order_basic.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
