package com.favorite_product.controller;

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
import javax.servlet.http.HttpSession;

import com.favorite_lesson.model.FavoriteLessonService;
import com.favorite_lesson.model.FavoriteLessonVO;
import com.favorite_product.model.FavoriteProductService;
import com.favorite_product.model.FavoriteProductVO;



/**
 * Servlet implementation class FavoriteProduct
 */
@WebServlet("/FavoriteProduct")
public class FavoriteProductServlet extends HttpServlet {
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
		if ("get_mem_favoriteProduct_ajax".equals(action)) { // 來自select_page.jsp的請求

			PrintWriter out = res.getWriter();
			try {			
				String memberNo = req.getParameter("memberNo");
				FavoriteProductService flSvc = new FavoriteProductService();
				List<FavoriteProductVO> FavoriteProductVOlist = flSvc.findByPrimaryKey(memberNo);
if (FavoriteProductVOlist.isEmpty()) {
					return;
				}
				for(FavoriteProductVO flVO:FavoriteProductVOlist) {
					out.print((flVO.getProduct_no())+",");
				}



				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				return;	
			}
		}
		if ("get_Mem_Favorite_Product".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memberNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String memberNo = null;
				try {
					memberNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FavoriteProductService favoriteProductSvc = new FavoriteProductService();
				List<FavoriteProductVO> favoriteLessonVO = favoriteProductSvc.findByPrimaryKey(memberNo);
				if (favoriteLessonVO.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("onlyForMEMfavoriteLesson", favoriteLessonVO);
				String url = "/back-end/favorite_lesson/listFavoriteLesson.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
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
				String memberNoReg = "^[(M)(0-9)]{4}$";
				if (memberNo == null || memberNo.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				else if(!memberNo.trim().matches(memberNoReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號ex:M001");
	            }
			
				String productNo = req.getParameter("product_no").trim();

				FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
				System.out.println(memberNo);
				System.out.println(productNo);
				favoriteProductVO.setMember_no(memberNo);
				favoriteProductVO.setProduct_no(productNo);



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("FavoriteProductVO", favoriteProductVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/favorite_lesson/addFavoriteLesson.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FavoriteProductService favoriteProductSvc = new FavoriteProductService();
				System.out.println(111);
				favoriteProductVO = favoriteProductSvc.addFavoriteProduct(memberNo, productNo);
				System.out.println(333);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				//新增後直接查詢該會員全部收藏	
//				List<FavoriteLessonVO> listFavoriteLessonVO = favoriteLessonSvc.getMemberFavoriteLesson(memberNo);				
//				HttpSession session = req.getSession();
//				session.setAttribute("onlyForMEMfavoriteLesson", listFavoriteLessonVO);				
//				String url = "/back-end/favorite_lesson/mem_favorite_lesson.jsp";
				
//				String url = "/front-end/gym_index/shop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/favorite_lesson/addFavoriteLesson.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("Delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String memberNo = new String(req.getParameter("member_no"));
				String productNo = new String(req.getParameter("product_no"));
				
				
				/***************************2.開始刪除資料***************************************/
				FavoriteProductService favoriteProductSvc = new FavoriteProductService();
				favoriteProductSvc.deleteFavoriteProduct(memberNo, productNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-end/gym_index/shop.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/favorite_lesson/listFavoriteLessonM.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
