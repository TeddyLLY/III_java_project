package com.product.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.comment_product.model.CommentProductService;
import com.comment_product.model.CommentProductVO;
import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.member.model.MemberVO;
import com.product.model.*;
@MultipartConfig

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
			
//		String realPath = getServletContext().getRealPath(saveDirectory);
		// 取得請求參數，從前端的select_page.jsp裡面的"action"--> <input type="hidden" name="action"
		// value="getOne_For_Display">
		String action = req.getParameter("action");
		System.out.println(action);
		// 判斷當請求參數所傳過來的值是不是跟"getOne_For_Display"相同，如果相同才會做下面的動作
		if ("getOne_For_Display".contentEquals(action)) { // 才自前端畫面select_page.jsp的請求
			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
			List<String> errorMsgs = new LinkedList<String>();
			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 從前端取得"productno"的資料
				String str = req.getParameter("product_no");
				// 判斷傳進來的值是否正確，前面的str == null是為了方便除錯用的，縮短錯誤的範圍，老師建議是要寫
				if (str == null || (str.trim()).length() == 0) {
					// 如果有上述的沒有填值或是填入空字串就會將錯誤訊息errorMsgs加入一個錯誤提示
					errorMsgs.add("請輸入商品編號");
				}
				// 下面程式碼是判斷當errorMsgs不為空的的時候，此時代表有錯誤訊息，將會執行裡面的程式碼
				if (!errorMsgs.isEmpty()) {
					// 當有錯誤訊息產生就會將你的req跟res一起轉交(forward)回去select_page.jsp
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String product_no = null;
				try {
					product_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// 跟50行的錯誤訊息判斷一樣
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/**************************** 2.開始查詢資料，永續層存取 *****************************************/
				// 這時候要將寫好的Service new進來裡面，記得要import
				ProductService productSvc = new ProductService();
				// 然後用VO去接你在Service裡面寫好的查詢單一資料的方法getOneProduct
				ProductVO ProductVO = productSvc.getOneProduct(product_no);
				if (ProductVO == null) {
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
				req.setAttribute("ProductVO", ProductVO); // 資料庫取出的empVO物件,存入request
				
				
				String url = "/front-end/shop/shop-info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String product_no = new String(req.getParameter("product_no"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO ProductVO = productSvc.getOneProduct(product_no);
				System.out.println("eeee"+ProductVO.getProduct_photo());
				System.out.println("de"+ProductVO.getProduct_no());
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ProductVO", ProductVO);      // 資料庫取出的empVO物件,存入req
				String url = "/back-end/product/product_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/product_basic.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_product_input.jsp的請求

			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
			List<String> errorMsgs = new LinkedList<String>();
			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);
//			ProductVO ProductVO1 = (ProductVO) req.getAttribute("ProductVO");
//			System.out.println(ProductVO1.getProduct_no());
//			System.out.println(ProductVO1.getProduct_photo());
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 有多少欄位要更新這邊就要傳多少資料進去，跟在寫JDBC裡面的UPDATE去對照會比較準確
				String product_no = new String(req.getParameter("product_no").trim());System.out.println(product_no);
				String product_name = req.getParameter("product_name");System.out.println(product_name);
				String product_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!product_name.trim().matches(product_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				
				byte[] product_photo =null;
				ProductService prdSvc = new ProductService();
				ProductVO pdVO = prdSvc.getOneProduct(product_no);
				InputStream input = req.getPart("product_photo").getInputStream();
				if (input.available()!=0) {
					product_photo = new byte[input.available()];
					input.read(product_photo);
					input.close();
				}else {
					product_photo = pdVO.getProduct_photo();
				}
				
//				EmployeeService employeeSvc2 = new EmployeeService();
//				EmployeeVO employeeVO2 = employeeSvc2.getOneEmployee(employee_no);
//				
//				byte[] employee_photo = null;
//				InputStream in = req.getPart("employee_photo").getInputStream();
//				
//				if (in.available() != 0) {
//					employee_photo = new byte[in.available()];
//					in.read(employee_photo);
//					in.close();
//				} else {
//			       employee_photo=employeeVO2.getEmployee_photo();
//				}
				System.out.println(product_photo);
				Integer product_quantity = null;
				try {
					product_quantity = new Integer(req.getParameter("product_quantity").trim());
				} catch (NumberFormatException e) {
					product_quantity = 0;
					errorMsgs.add("請在商品數量填寫數字.");
				}
				System.out.println(product_quantity);
				Integer product_price = null;
				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("請在商品價格填寫數字.");
				}
				System.out.println(product_price);
				Integer product_sales = null;
				try {
					product_sales = new Integer(req.getParameter("product_sales").trim());
				} catch (NumberFormatException e) {
					product_sales = 0;
					errorMsgs.add("請在商品銷量填寫數字.");
				}
				System.out.println(product_sales);
				String product_content = req.getParameter("product_content");
				if (product_content == null || product_content.trim().length() == 0) {
					errorMsgs.add("商品評價: 請勿空白");
				}
				System.out.println(product_content);
				Integer product_total_evaluation = null;
				try {
					product_total_evaluation = new Integer(req.getParameter("product_total_evaluation").trim());
				} catch (NumberFormatException e) {
					product_total_evaluation = 0;
					errorMsgs.add("請在商品總評價分數填寫數字.");
				}
				System.out.println(product_total_evaluation);
				Integer product_people_of_evaluation = null;
				try {
					product_people_of_evaluation = new Integer(req.getParameter("product_people_of_evaluation").trim());
				} catch (NumberFormatException e) {
					product_people_of_evaluation = 0;
					errorMsgs.add("請在商品總評價人數填寫數字.");
				}
				System.out.println(product_people_of_evaluation);
				Integer product_average_evaluation = null;
				try {
					product_average_evaluation = new Integer(req.getParameter("product_average_evaluation").trim());
				} catch (NumberFormatException e) {
					product_average_evaluation = 0;
					errorMsgs.add("請在商品評價平均分數填寫數字.");
				}
				System.out.println(product_average_evaluation);
				// 由於我這邊想要製作的畫面是要用下拉式選單來選我的商品的狀態所以傳進來的值不用做防呆處理去避免使用者輸入錯資料
				Integer product_status = new Integer(req.getParameter("product_status").trim());
				
				ProductVO ProductVO = new ProductVO();
				ProductVO.setProduct_name(product_name);
				ProductVO.setProduct_photo(product_photo);  
				ProductVO.setProduct_quantity(product_quantity);
				ProductVO.setProduct_price(product_price);
				ProductVO.setProduct_sales(product_sales);
				ProductVO.setProduct_content(product_content);
				ProductVO.setProduct_total_evaluation(product_total_evaluation);
				ProductVO.setProduct_people_of_evaluation(product_people_of_evaluation);
				ProductVO.setProduct_average_evaluation(product_average_evaluation);
				ProductVO.setProduct_status(product_status);
				ProductVO.setProduct_no(product_no);
				// 跟50行的錯誤訊息判斷一樣
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ProductVO", ProductVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProductService productSvc = new ProductService();
				// 將上面無誤的資料傳進來使用
				ProductVO = productSvc.updateProduct(product_name, product_photo, product_quantity, product_price, product_sales, product_content, product_total_evaluation, product_people_of_evaluation, product_status, product_average_evaluation, product_no);
				System.out.println("更新商品資料");
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ProductVO", ProductVO); // 資料庫update成功後,正確的的productVO物件,存入req
				String url = "/back-end/product/product_basic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_update.jsp");
				failureView.forward(req, res);
			}
		}
		// 要寫insert的時候也是去把JDBC的INSERT打開然後確認裡面的值都有正確傳入並且確認有做防呆處理
		if ("insert".equals(action)) { // 來自addProduct.jsp的請求

			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
			List<String> errorMsgs = new LinkedList<String>();
			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String product_name = req.getParameter("product_name");
				String product_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!product_name.trim().matches(product_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				byte[] product_photo =null;
				InputStream input = req.getPart("product_photo").getInputStream();
				if (input.available()!=0) {
					product_photo = new byte[input.available()];
					input.read(product_photo);
					input.close();
				}else {
					errorMsgs.add("請放圖片.");
				}
				
				
				Integer product_quantity = null;
				try {
					product_quantity = new Integer(req.getParameter("product_quantity").trim());
				} catch (NumberFormatException e) {
					product_quantity = 0;
					errorMsgs.add("請在商品數量填寫數字.");
				}

				Integer product_price = null;
				try {
					product_price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					product_price = 0;
					errorMsgs.add("請在商品價格填寫數字.");
				}

				Integer product_sales = null;
				try {
					product_sales = new Integer(req.getParameter("product_sales").trim());
				} catch (NumberFormatException e) {
					product_sales = 0;
					errorMsgs.add("請在商品銷量填寫數字.");
				}

				String product_content = req.getParameter("product_content");
				if (product_content == null || product_content.trim().length() == 0) {
					errorMsgs.add("商品評價: 請勿空白");
				}

				Integer product_total_evaluation = null;
				try {
					product_total_evaluation = new Integer(req.getParameter("product_total_evaluation").trim());
				} catch (NumberFormatException e) {
					product_total_evaluation = 0;
					errorMsgs.add("請在商品總評價分數填寫數字.");
				}

				Integer product_people_of_evaluation = null;
				try {
					product_people_of_evaluation = new Integer(req.getParameter("product_people_of_evaluation").trim());
				} catch (NumberFormatException e) {
					product_people_of_evaluation = 0;
					errorMsgs.add("請在商品總評價人數填寫數字.");
				}

				Integer product_average_evaluation = null;
				try {
					product_average_evaluation = new Integer(req.getParameter("product_average_evaluation").trim());
				} catch (NumberFormatException e) {
					product_average_evaluation = 0;
					errorMsgs.add("請在商品評價平均分數填寫數字.");
				}
				// 由於我這邊想要製作的畫面是要用下拉式選單來選我的商品的狀態所以傳進來的值不用做防呆處理去避免使用者輸入錯資料
				Integer product_status = new Integer(req.getParameter("product_status").trim());

				ProductVO ProductVO = new ProductVO();
				ProductVO.setProduct_name(product_name);
				ProductVO.setProduct_photo(product_photo);  
				
				ProductVO.setProduct_quantity(product_quantity);
				ProductVO.setProduct_price(product_price);
				ProductVO.setProduct_sales(product_sales);
				ProductVO.setProduct_content(product_content);
				ProductVO.setProduct_total_evaluation(product_total_evaluation);
				ProductVO.setProduct_people_of_evaluation(product_people_of_evaluation);
				ProductVO.setProduct_average_evaluation(product_average_evaluation);
				ProductVO.setProduct_status(product_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ProductVO", ProductVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productSvc = new ProductService();
				ProductVO = productSvc.addProduct(product_name, product_photo, product_quantity, product_price,
						product_sales, product_content, product_total_evaluation, product_people_of_evaluation,
						product_status, product_average_evaluation);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/product/product_basic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllProduct.jsp

			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
			List<String> errorMsgs = new LinkedList<String>();
			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String product_no = new String(req.getParameter("product_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(product_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/product/product_basic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				System.out.println("come in");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/product_basic.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("updatestar".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String product_no = req.getParameter("product_no");
				System.out.println(product_no);
				String member_no = req.getParameter("member_no");
				System.out.println(member_no);
				Integer product_total_evaluation =new Integer(req.getParameter("product_total_evaluation"));
				System.out.println(product_total_evaluation);
				String comment_product_content = req.getParameter("comment_product_content");
				System.out.println(comment_product_content);
				ProductVO productVO =new ProductVO();
				CommentProductService commentProductSvc = new CommentProductService();
				/*************************** 2.開始新增資料 ***************************************/
				ProductService productSvc = new ProductService();
				System.out.println(11);
				productVO = productSvc.updateRate(product_total_evaluation, product_no);
				System.out.println(22);
				CommentProductVO commentProductVO = new CommentProductVO();
				commentProductVO = commentProductSvc.addCommentProduct(member_no, product_no, product_total_evaluation, comment_product_content);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/product/listAllProduct.jsp";
				System.out.println(33);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
				System.out.println(44);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("delete".equals(action)) { // 來自listAllProduct.jsp
//
//			// 將錯誤訊息用List裝起來，這樣寫的原因是之後可能會有許多錯誤訊息可以一次秀出來
//			List<String> errorMsgs = new LinkedList<String>();
//			// 下面這行是為了準備將錯誤訊息放入到裡面然後用 request scope回傳回去到ErrorPage view
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String product_no = new String(req.getParameter("product_no"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				ProductService productSvc = new ProductService();
//				productSvc.deleteProduct(product_no);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/back-end/product/listAllProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllProduct.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

		// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			System.out.println("header=" + header); // 測試用
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			System.out.println("filename=" + filename); // 測試用
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}
}
