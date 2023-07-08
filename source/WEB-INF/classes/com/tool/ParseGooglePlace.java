package com.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseGooglePlace {
	private static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
			+ "location=24.95375,121.22575&"
			+ "radius=500&"
			+ "types=food&"
			+ "name=吃到飽&"
			+ "language=zh-TW&"
			+ "key=AIzaSyBZGVKhad8IGzpiKosnZFKHvG_hEy79v80";
	
	public static void main(String[] args) throws IOException {
		URL url = new URL(GOOGLE_URL);
		StringBuilder sb = new StringBuilder();
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setUseCaches(false);
		con.setDoInput(true);
		
		int statusCode = con.getResponseCode();
		if (statusCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String data;
			while ((data = br.readLine()) != null)
				sb.append(data);
			
			br.close();
			
		} else {
			System.out.println("Status Code = " + statusCode);
		}
		
		con.disconnect();
		
		System.out.println(sb.toString());
		
	}

}
