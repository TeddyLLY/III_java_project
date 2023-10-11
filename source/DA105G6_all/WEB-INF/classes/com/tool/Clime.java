package com.tool;
import java.net.*;
import java.io.*;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Clime {
	
	
	
	public static void main(String args[])throws Exception {
		
		
		
	
				
	
		
//============================================騙=============================================================		
			
//			String httpsURL = "https://xysc.cyc.org.tw/"; 
//			// 以HttpsUtil工具類別建立URLConnection物件
//			URLConnection urlConn = HttpsUtil.getURLConnection(httpsURL);
//		    // 以下模擬瀏覽器的user-agent請求標頭(Servlet講義p79-範例HeaderSnoop.java ; 或講義p185-範例EL10.jsp)
//			urlConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
//			// 以URLConnection物件取得輸入資料流
//
////============================================騙=============================================================							
//		
//			
////============================================抓資料-start===================================================			
//	
//			
////=======================================台北市大同區國民運動中心-start======================================				
//			
			String htmlPage1 = Jsoup.connect("http://www.dtsc-wdyg.com.tw/web/index/index.jsp").get().toString();
			
			Document doc1 = Jsoup.parse(htmlPage1);
			
			Elements titleAndAddress = doc1.select("div.footerIL_inArea span");				
			String sb = titleAndAddress.get(0).text();			
			System.out.println(sb.substring(0, 9));								//店名
			System.out.println(sb.substring(11, 23));							//地址
								
			Elements time1 = doc1.select("div.footerIL_inArea span");			//營業時間
			String sb1 = time1.get(4).text();
			System.out.println(sb1.substring(5,22));	
	
			Elements people1 = doc1.select("div.VisitList"); 					//人數
			System.out.println(people1.get(3).text());
				
			System.out.println("================================");
//==========================================台北市大同區國民運動中心-end=====================================	

//=======================================台中北區國民運動中心-start==========================================	
			String htmlPage = Jsoup.connect("http://tndcsc.com.tw/").get().toString();
			Document doc = Jsoup.parse(htmlPage);

			Elements people = doc.select("div.agile_header_grid p"); 			//人數
			System.out.println(people.get(1).text());
			
			Elements address = doc.select("div.footer-info-agile li");			//地址
			System.out.println(address.get(0).text());
	
			Elements title = doc.select("div.agile_header_grid a");				//店名
			System.out.println(title.get(0).text());	
			
			System.out.println("================================");
//==========================================台中北區國民運動中心-end=========================================		

			
//=======================================桃園國民運動中心-start==============================================				
			
			String htmlPage2 = Jsoup.connect("https://tycsc.cyc.org.tw/api").get().toString();	
			Document doc2 = Jsoup.parse(htmlPage2);
			
			Elements title2 = doc2.select("title");				//店名
			System.out.println(title2.text());
			
			Elements address2 = doc2.select("div.tab ~p");		//地址
			String sb2 = address2.get(0).text();
			System.out.println(sb2.substring(3,14));
				
			Elements time2 = doc2.select("div.tab ~p");			//營業時間
			System.out.println(time2.get(4).text());	
	
			Elements people2 = doc2.select("li#gym"); 			//人數
			

			
			System.out.println(people2.text());
			
			System.out.println("================================");
//==========================================桃園國民運動中心-end=============================================

//========================================台北市大安運動中心-start===========================================		
			String htmlPage3 = Jsoup.connect("https://dasc.cyc.org.tw/").get().toString();	 //台北市大安運動中心
			Document doc3 = Jsoup.parse(htmlPage3);
			
			Elements title3 = doc3.select("title");				//店名
			System.out.println(title3.text());
			
			Elements address3 = doc3.select("div.tab ~p");		//地址
			String sb3 = address3.get(0).text();
			System.out.println(sb3.substring(3,14));
				
			Elements time3 = doc3.select("div.tab ~p");			//營業時間
			System.out.println(time3.get(4).text());	
	
			Elements people3 = doc3.select("li#gym"); 			//人數
			System.out.println(people3.text());

			System.out.println("================================");
//========================================台北市大安運動中心-end=============================================			

//========================================台北市南港運動中心-start===========================================
			String htmlPage4 = Jsoup.connect("https://ngsc.cyc.org.tw/").get().toString();	 //台北市南港運動中心
			Document doc4 = Jsoup.parse(htmlPage4);
			
			Elements title4 = doc4.select("title");				//店名
			System.out.println(title4.text());
			
			Elements address4 = doc4.select("div.tab ~p");		//地址
			String sb4 = address4.get(0).text();
			System.out.println(sb4.substring(3,14));
				
			Elements time4 = doc4.select("div.tab ~p");			//營業時間
			System.out.println(time4.get(4).text());	
	
			Elements people4 = doc4.select("li#gym"); 			//人數
			System.out.println(people4.text());
			
			System.out.println("================================");
//========================================台北市南港運動中心-end=============================================				
			
			
//============================================抓資料-end=====================================================					
								
	}
}
