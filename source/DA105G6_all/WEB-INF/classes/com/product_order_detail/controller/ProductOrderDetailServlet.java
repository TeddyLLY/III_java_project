package com.product_order_detail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.product_order_detail.model.ProductOrderDetailService;
import com.product_order_detail.model.ProductOrderDetailVO;


@WebServlet("/ProductOrderDetailServlet")
public class ProductOrderDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		// 取得請求參數，從前端的select_page.jsp裡面的"action"--> <input type="hidden" name="action"
		// value="getOne_For_Display">
		String action = req.getParameter("action");
		// 判斷當請求參數所傳過來的值是不是跟"getOne_For_Display"相同，如果相同才會做下面的動作
		if ("getOne_For_Product_Order_Detail".contentEquals(action)) { // 才自前端畫面select_page.jsp的請求
			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
			List<String> errorMsgs = new LinkedList<String>();
			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("order_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String order_no = null;
				try {
					order_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// 跟50行的錯誤訊息判斷一樣
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 2.開始查詢資料，永續層存取
				 *****************************************/
				ProductOrderDetailService productOrderDetailSvc = new ProductOrderDetailService();
				
				List<ProductOrderDetailVO> productOrderDetailVO = productOrderDetailSvc.getOneProductOrderDetail(order_no);
				
//						productSvc.getOneProduct(product_no);
				if (productOrderDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// 跟50行的錯誤訊息判斷一樣
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成後判斷都沒有錯誤的時候就會將正確的資料,準備轉交給view---->JSP(Send the Success view)
				 *************/
				req.setAttribute("ProductOrderDetailVO", productOrderDetailVO); // 資料庫取出的empVO物件,存入request

				
				String url = "/back-end/product_Order_Detail/listOneProductOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
