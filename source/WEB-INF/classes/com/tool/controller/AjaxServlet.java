package com.tool.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import com.tool.HttpsUtil;


@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res); 
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();		
			
			try {
				String httpsURL = "https://xysc.cyc.org.tw/api"; 
				// 以HttpsUtil工具類別建立URLConnection物件
				URLConnection urlConn = HttpsUtil.getURLConnection(httpsURL);
			    // 以下模擬瀏覽器的user-agent請求標頭(Servlet講義p79-範例HeaderSnoop.java ; 或講義p185-範例EL10.jsp)
				urlConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
				// 以URLConnection物件取得輸入資料流
				urlConn.connect();
				InputStream ins = urlConn.getInputStream();
	
				// 建立URL資料流
				BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
	
				String data;
				JSONObject jsonObj = null;
				
				while ((data = br.readLine()) != null) {
					  	
					jsonObj = new JSONObject(data);	
				}						
					  
				out.println(jsonObj);
						
				br.close();
				ins.close();
	
			} catch (Exception e) {
					e.printStackTrace();
			}
		
	}
}
