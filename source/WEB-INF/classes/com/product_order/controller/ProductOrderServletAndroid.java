package com.product_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductJDBCDAO;
import com.product.model.ProductVO;
import com.product_order.model.OrderProduct;
import com.product_order.model.ProductOrderDAO;
import com.product_order.model.ProductOrderDAO_interface;
import com.product_order.model.ProductOrderJDBCDAO;
import com.product_order.model.ProductOrderVO;
import com.product_order_detail.model.ProductOrderDetailDAO;
import com.product_order_detail.model.ProductOrderDetailDAO_interface;
import com.product_order_detail.model.ProductOrderDetailJDBCDAO;
import com.product_order_detail.model.ProductOrderDetailVO;



public class ProductOrderServletAndroid extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line=br.readLine()) != null) {
			jsonIn.append(line);
		}
		
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		ProductOrderDAO_interface orderDAO = new ProductOrderDAO();
		String action = jsonObject.get("action").getAsString();
		String userId = jsonObject.get("userId").getAsString();
		

		if ("add".equals(action) && userId != null) {
			String orderJson = jsonObject.get("order").getAsString();
			ProductOrderVO order = gson.fromJson(orderJson, ProductOrderVO.class);
			List<ProductOrderDetailVO> orderProductOrderDetailList = new ArrayList<>();
			List<OrderProduct> productList = order.getOrderProductList();
			for (OrderProduct product : productList) {
				ProductOrderDetailVO pod = new ProductOrderDetailVO();
				pod.setProduct_no(product.getProduct_no());
				pod.setDetail_unit_price(product.getProduct_price());
				pod.setDetail_qty(product.getProduct_quantity());
				orderProductOrderDetailList.add(pod);
			}
			ProductOrderVO productOrder = null;
			String order_no = orderDAO.addWithProductOrderDetail(order, orderProductOrderDetailList);
			if (order_no != null) {
				productOrder = orderDAO.findById(order_no);
				productOrder.setOrderProductList(productList);
			}
			writeText(res, gson.toJson(productOrder));
		} else if ("getAll".equals(action) && userId != null) {
			String start = jsonObject.get("start").getAsString();
			String end = jsonObject.get("end").getAsString();
			List<ProductOrderVO> orderList = orderDAO.getAll(userId, start, end);
			if (orderList != null && !orderList.isEmpty()) {
				// JOIN Book data from OrderItem
				ProductOrderDetailDAO_interface podDao = new ProductOrderDetailDAO();
				ProductDAO_interface productDao = new ProductDAO();
				List<OrderProduct> orderProductList = null;
				for (ProductOrderVO pOrder : orderList) {
					String order_no = pOrder.getOrder_no();
					orderProductList = new ArrayList<>();
					List<ProductOrderDetailVO> podList = podDao.findByOrderId(order_no);
					for (ProductOrderDetailVO pod : podList) {
						ProductVO product = productDao.findByProNo(pod.getProduct_no());
						OrderProduct orderProduct = new OrderProduct(product, pod.getDetail_qty());
						orderProductList.add(orderProduct);
					}
					pOrder.setOrderProductList(orderProductList);
				}
				writeText(res, gson.toJson(orderList));
			}
		}
		
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
