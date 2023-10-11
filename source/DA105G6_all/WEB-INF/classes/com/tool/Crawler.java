package com.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Crawler {

	ArrayList<String> allurlSet = new ArrayList<String>();
	ArrayList<String> notCrawlurlSet = new ArrayList<String>();
	int count = 15;	
    JSONArray jsonArray = new JSONArray();
//	boolean state = false;
//	int threadCount = 0;
//	public static final Object signal = new Object();
	
	public static void main(String args[]) {
			
		Crawler crawler = new Crawler();
//		crawler.gymPeople();
		System.out.println("jsonArray1：" +  crawler.gymPeople());	
	}
		public JSONArray gymPeople() {
			
			Crawler wc = new Crawler();
			wc.addUrl("https://cssc.cyc.org.tw/api");		//中山運動中心1
			wc.addUrl("https://ngsc.cyc.org.tw/api");		//南港運動中心2
			wc.addUrl("https://xysc.cyc.org.tw/api");		//信義運動中心3
			wc.addUrl("https://dasc.cyc.org.tw/api");		//大安運動中心4		官網問題偶爾擷取不到來源
			wc.addUrl("https://xysc.cyc.org.tw/api");		//文山運動中心5	
			wc.addUrl("https://nhsc.cyc.org.tw/api");		//內湖運動中心6 	 官網問題偶爾擷取不到來源
			wc.addUrl("https://lzcsc.cyc.org.tw/api");		//蘆洲運動中心7
			wc.addUrl("https://tccsc.cyc.org.tw/api");		//土城運動中心8
			wc.addUrl("https://xzcsc.cyc.org.tw/api");		//汐止運動中心9
			wc.addUrl("https://yhcsc.cyc.org.tw/api");		//永和運動中心10
			wc.addUrl("https://cmcsc.cyc.org.tw/api");		//朝馬運動中心11
//wc.addUrl("https://styac.cyc.org.tw/api");		//南區青少年運動中心12	官網問題暫時擷取不到來源
			wc.addUrl("https://zlcsc.cyc.org.tw/api");		//中壢運動中心13
			wc.addUrl("https://tycsc.cyc.org.tw/api");		//桃園運動中心14
			wc.addUrl("https://zgcsc.cyc.org.tw/api");		//竹光運動中心15
			wc.addUrl("https://lkcsc.cyc.org.tw/api");		//林口運動中心16	
	
			System.out.println("======爬蟲開始========");
			
			JSONArray testArray2 = wc.begin();
			
			System.out.println("======爬蟲結束========");
						
			return testArray2;

//			while(true){  
//	            if(wc.notCrawlurlSet.isEmpty() && Thread.activeCount() == 1){  
//	
//	                System.out.println("總共爬了"+wc.allurlSet.size()+"個網頁");  
//	                System.exit(1);  
////	                break;  
//	            }
//	       }
		}
	
	public JSONArray begin() {
		
		for (int i = 0; i < count; i++) {
				
				String data = getAUrl();
				if (data != null) {
					crawler(data);					
				}						
		}
		System.out.println("jsonArray1：" +  jsonArray);	
        JSONArray testArray = jsonArray;
		return testArray;
	}
	
	public void crawler(String sUrl) {
		
		try {
			URLConnection urlConn = HttpsUtil.getURLConnection(sUrl);
		    // 以下模擬瀏覽器的user-agent請求標頭(Servlet講義p79-範例HeaderSnoop.java ; 或講義p185-範例EL10.jsp)
			urlConn.setRequestProperty("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Mobile Safari/537.36");
			// 以URLConnection物件取得輸入資料流
			urlConn.connect();
			InputStream ins = urlConn.getInputStream();		
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
			
			String data;	
			JSONObject jsonObj = new JSONObject();
			
			if ((data = br.readLine()) != null) {				  	
				jsonObj = new JSONObject(data);			
			}		
					
				br.close();	
				ins.close();		
												
				jsonArray.put(jsonObj);

//				System.out.println(jsonObj);			
				
				
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getAUrl() {
		if (notCrawlurlSet.isEmpty())
				return null;
		String tmpAUrl;
		tmpAUrl = notCrawlurlSet.get(0);
		notCrawlurlSet.remove(0);
		return tmpAUrl;
	}
	
	public void addUrl(String url) {
		notCrawlurlSet.add(url);
		allurlSet.add(url);
		
	}
}	
