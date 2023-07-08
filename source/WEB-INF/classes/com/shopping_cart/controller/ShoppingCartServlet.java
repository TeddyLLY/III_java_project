package com.shopping_cart.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;
import com.product.model.*;
import com.product_order_detail.model.*;
import com.product_order.model.*;


@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Vector<ProductOrderDetailVO> buylist = (Vector<ProductOrderDetailVO>)session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		System.out.println(action);
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		
		if (!action.equals("Checkout")) {
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				float total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					ProductOrderDetailVO order = buylist.get(i);
					float price = order.getDetail_unit_price();
					int quantity = order.getDetail_qty();
					total += (price * quantity);
				}
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
				session.setAttribute("shoppingcart", buylist);
			}
		
		else if(action.equals("Add")) {
			boolean match = false;
			
			//取得後來新增的商品
			ProductOrderDetailVO aproduct = getProduct(req);
			if(buylist==null) {
			
				buylist = new Vector<ProductOrderDetailVO>();
				buylist.add(aproduct);
			}else {
				for(int i=0; i<buylist.size();i++) {
					ProductOrderDetailVO product =buylist.get(i); 
					if(aproduct.getProduct_no().equals(product.getProduct_no())) {
						product.setDetail_qty(product.getDetail_qty()+aproduct.getDetail_qty());
						buylist.setElementAt(product, i);
						match=true;
					}
				}
				if(!match) {
					buylist.add(aproduct);
				}
			}
			Integer total;
			total = buylist.stream()
					.mapToInt(e -> e.getDetail_qty()*e.getDetail_unit_price())
					.sum();
			
//			for (int i = 0; i < buylist.size(); i++) {
//				ProductOrderDetailVO order = buylist.get(i);
//				float price = order.getDetail_unit_price();
//				int quantity = order.getDetail_qty();
//				total += (price * quantity);
//			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
		}
			
			
			
			session.setAttribute("shoppingcart", buylist);
			String url="/front-end/shop/shop.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
			
			if(action.equals("Plus")) {
				ProductOrderDetailVO aproduct = getProduct(req);
				ProductOrderDetailVO innerOrder = buylist.get(buylist.indexOf(aproduct));
				innerOrder.setDetail_qty(aproduct.getDetail_qty());
				 session.setAttribute("shoppingcart", buylist);
				 Integer total;
					total = buylist.stream()
							.mapToInt(e -> e.getDetail_qty()*e.getDetail_unit_price())
							.sum();
					String amount = String.valueOf(total);
					session.setAttribute("amount", amount);
				
			}
		else if(action.equals("Minus")) {
				ProductOrderDetailVO aproduct = getProduct(req);
				ProductOrderDetailVO innerOrder =buylist.get(buylist.indexOf(aproduct));
				innerOrder.setDetail_qty(aproduct.getDetail_qty());
				session.setAttribute("shoppingcart", buylist);
				 Integer total;
					total = buylist.stream()
							.mapToInt(e -> e.getDetail_qty()*e.getDetail_unit_price())
							.sum();
					String amount = String.valueOf(total);
					session.setAttribute("amount", amount);
			}
			else if(action.equals("Checkout")){
				String order_address = req.getParameter("order_address");
				String order_recipient = req.getParameter("order_recipient");
				ProductOrderService productOrderSVC = new ProductOrderService();
				ProductOrderVO productorderVO = new ProductOrderVO();
				productorderVO.setMember_no(memberVO.getMember_no());
				productorderVO.setOrder_address(order_address);
				productorderVO.setOrder_recipient(order_recipient);
				
				
				List<ProductOrderDetailVO> productOrderDetailVO = new ArrayList<ProductOrderDetailVO>();
				ProductOrderDetailVO ProdList= null;
				productOrderSVC.insertWithProductOrderDetail(productorderVO, buylist);
				 System.out.println("訂單新增成功");
				 session.removeAttribute("shoppingcart");
				 session.removeAttribute("amount");
			}
			

		   
	}
	
	
	
	


	private ProductOrderDetailVO getProduct(HttpServletRequest req) {
		
		String product_no = req.getParameter("product_no");
		Integer detail_unit_price = new Integer(req.getParameter("product_price")).intValue();
		Integer detail_qty = new Integer(req.getParameter("quantity")).intValue();
		
		
		
		ProductOrderDetailVO pd = new ProductOrderDetailVO();
	
		pd.setProduct_no(product_no);
		pd.setDetail_unit_price(detail_unit_price);
		pd.setDetail_qty(detail_qty);
		return pd;
	}

}