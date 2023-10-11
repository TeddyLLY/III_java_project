package com.report_product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.favorite_product.model.FavoriteProductService;
import com.favorite_product.model.FavoriteProductVO;
import com.product_order.model.ProductOrderService;
import com.product_order.model.ProductOrderVO;
import com.report_product.model.ReportProductService;
import com.report_product.model.ReportProductVO;

/**
 * Servlet implementation class ReportProductServlet
 */
@WebServlet("/ReportProductServlet")
public class ReportProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("get_One_For_Update_Status".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			printWriter out =

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String reportProductNo = req.getParameter("report_product_no");
				System.out.println(reportProductNo);
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入會員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_report/product_report_basic.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ReportProductService reportProductSvc = new ReportProductService();
				ReportProductVO reportProductVO = reportProductSvc.getOneReportProduct(reportProductNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req 
							.getRequestDispatcher("/back-end/product_report/product_report_basic.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				HttpSession session = req.getSession();
				req.setAttribute("ReportProductVO", reportProductVO);
				String url = "/back-end/product_report/product_report_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
//				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_report_basic.jsp");
				failureView.forward(req, res);
			}
		}
		
		


        if ("Insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memberNo = req.getParameter("member_no");		
				System.out.println(memberNo);
				String productNo = req.getParameter("product_no").trim();
				System.out.println(productNo);
				String reportProductContent = req.getParameter("report_product_content").trim();
				System.out.println(reportProductContent);
				ReportProductVO reportProductVO = new ReportProductVO();
				reportProductVO.setMember_no(memberNo);
				reportProductVO.setProduct_no(productNo);
				reportProductVO.setReport_product_content(reportProductContent);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ReportProductVO", reportProductVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ReportProductService reportProductSvc = new ReportProductService();
				reportProductVO = reportProductSvc.addReportProduct(memberNo, productNo, reportProductContent);
				System.out.println("轉交");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				//新增後直接查詢該會員全部收藏	
//				List<FavoriteLessonVO> listFavoriteLessonVO = favoriteLessonSvc.getMemberFavoriteLesson(memberNo);				
//				HttpSession session = req.getSession();
//				session.setAttribute("onlyForMEMfavoriteLesson", listFavoriteLessonVO);				
//				String url = "/back-end/favorite_lesson/mem_favorite_lesson.jsp";
				
				String url = "/back-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
        if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String report_product_no = req.getParameter("report_product_no");
				System.out.println(report_product_no);
				Integer report_product_status = new Integer(req.getParameter("report_product_status"));
				System.out.println(report_product_status);
				/**************************** 2.開始查詢資料，永續層存取 *****************************************/
				ProductOrderService productOrderSvc = new ProductOrderService();
				ReportProductService reportProductSvc = new ReportProductService();
				ReportProductVO reportProductVO = reportProductSvc.updateReportProduct(report_product_status, report_product_no);
				/**************************** 3.查詢完成後判斷都沒有錯誤的時候就會將正確的資料,準備轉交給view---->JSP(Send the Success view) *************/
				req.setAttribute("ReportProductVO", reportProductVO);
				String url = "/back-end/product_report/product_report_basic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product_report/product_report_basic.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("Delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String memberNo = new String(req.getParameter("member_no"));
//				String productNo = new String(req.getParameter("product_no"));
//				
//				
//				/***************************2.開始刪除資料***************************************/
//				FavoriteProductService favoriteProductSvc = new FavoriteProductService();
//				favoriteProductSvc.deleteFavoriteProduct(memberNo, productNo);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-end/gym_index/shop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}


}
