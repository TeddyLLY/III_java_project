package com.toolclass;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class StatusListener
 */
@WebServlet("/StatusListener")
public class StatusListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
//		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 用不到
		Map<String,String> member_gender = new HashMap<>();
		member_gender.put("0", "女");
		member_gender.put("1", "男");
		
		Map<String,String> member_review = new HashMap<>();
		member_review.put("0", "驗證中");
		member_review.put("1", "驗證成功");
		member_review.put("2", "驗證失敗");
//		會員審核狀態: 0:驗證中 1: 驗證成功2:驗證失敗
//		會員權限狀態: 0:停權1:正常權限 
		
		Map<String,String> member_auth = new HashMap<>();
		member_auth.put("0", "停權");
		member_auth.put("1", "good member");
		
		Map<String,String> coach_gender = new HashMap<>();
		coach_gender.put("0", "女");
		coach_gender.put("1", "男");
		
		Map<String,String> coach_review = new HashMap<>();
		coach_review.put("0", "驗證中");
		coach_review.put("1", "未驗證");
		coach_review.put("2", "待審核");
		coach_review.put("3", "檢舉中");
		coach_review.put("4", "good coach");
		coach_review.put("5", "驗證身分失敗");
		
		Map<String,String> coach_auth = new HashMap<>();
		coach_auth.put("0", "停權");
		coach_auth.put("1", "正常權限");
		coach_auth.put("2", "記點");
		
		Map<String,String> friend_status = new HashMap<>();
		coach_auth.put("0", "停權");
		coach_auth.put("1", "正常權限");
		coach_auth.put("2", "記點");
		
		Map<String,String> report_coach_status = new HashMap<>();
		report_coach_status.put("0", "審核中");
		report_coach_status.put("1", "審核成功且停權");
		report_coach_status.put("2", "審核未通過");

		Map<Integer,String> lesson_class = new HashMap<>();
		lesson_class.put(0, "其他");
		lesson_class.put(1, "重量訓練");
		lesson_class.put(2, "有氧訓練");
		lesson_class.put(3, "減重訓練");
		lesson_class.put(4, "綜合訓練"); 
		
		Map<Integer,String> lesson_status = new HashMap<>();
		lesson_status.put(0, "已關閉");
		lesson_status.put(1, "開放中");

		Map<Integer,String> lesson_order_status = new HashMap<>();
		lesson_order_status.put(0, "已完成");
		lesson_order_status.put(1, "進行中");
		lesson_order_status.put(2, "退課(報名截止日前可退)");
		
		Map<Integer,String> appointment_status = new HashMap<>();
		appointment_status.put(0, "已完成(已上課)");
		appointment_status.put(1, "進行中(已付款，未上課)");
		appointment_status.put(2, "確認中");
		appointment_status.put(3, "等待教練報價");
		appointment_status.put(4, "申請調課");
		appointment_status.put(5, "退課");

		Map<Integer,String> student_status = new HashMap<>();
		student_status.put(0, "未報到");
		student_status.put(1, "已報到");
		
		Map<String,String> article_status = new HashMap<>();
		article_status.put("0", "顯示");
		article_status.put("1", "隱藏");
		
		Map<String,String> report_article_status = new HashMap<>();
		report_article_status.put("0", "審核中");
		report_article_status.put("1", "審核成功且刪除");
		report_article_status.put("2", "審核未通過");
		
		Map<String,String> report_lvie_status = new HashMap<>();
		report_lvie_status.put("0", "審核中");
		report_lvie_status.put("1", "審核成功且刪除");
		report_lvie_status.put("2", "審核未通過");
		
		Map<String,String> product = new HashMap<>();
		product.put("0", "下架");
		product.put("1", "上架");
		
		Map<String,String> product_order = new HashMap<>();
		product_order.put("0", "處理中");
		product_order.put("1", "未出貨");
		product_order.put("2", "配送中");
		product_order.put("3", "已完成");
		
		Map<String,String> report_product = new HashMap<>();
		report_product.put("0", "審核中");
		report_product.put("1", "審核成功且下架");
		report_product.put("2", "審核未通過");
		
		ServletContext context = sce.getServletContext();
		context.setAttribute("member_gender", member_gender);
		context.setAttribute("member_review", member_review);
		context.setAttribute("member_auth", member_auth);
		context.setAttribute("coach_gender", coach_gender);
		context.setAttribute("coach_review", coach_review);
		context.setAttribute("coach_auth", coach_auth);
		context.setAttribute("friend_status", friend_status);
		context.setAttribute("report_coach_status", report_coach_status);
		context.setAttribute("lesson_class", lesson_class);
		context.setAttribute("lesson_status", lesson_status);
		context.setAttribute("lesson_order_status", lesson_order_status);
		context.setAttribute("appointment_status", appointment_status);
		context.setAttribute("student_status", student_status);
		context.setAttribute("article_status", article_status);
		context.setAttribute("report_article_status", report_article_status);		
		context.setAttribute("report_lvie_status", report_lvie_status);
		context.setAttribute("product", product);
		context.setAttribute("product_order", product_order);
		context.setAttribute("report_product", report_product);
		System.out.println("listener載入成功");
	}

}
