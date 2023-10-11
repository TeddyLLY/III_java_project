package com.tool;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

// 參考 https://blog.csdn.net/shumeng_xiaoyan/article/details/76503601
public class CWBJSONCrawlerTest {
	private static final String MY_URL = "https://opendata.cwb.gov.tw/fileapi/v1/opendataapi/F-A0012-001?Authorization=CWB-092F6DFC-B940-44A7-A9E9-35F1A475B5E0&format=JSON";

	public static void main(String[] args) throws Exception {
		SSLContext sslcontext = SSLContext.getInstance("SSL","SunJSSE");
        sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
       
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                System.out.println("WARNING: Hostname is not matched for cert.");
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
		
        URL url = new URL(MY_URL);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoInput(true);
		con.setUseCaches(false);
		con.setInstanceFollowRedirects(false);

		int statusCode = con.getResponseCode();
		System.out.println("Status Code = " + statusCode);
		
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
		FileOutputStream fos = new FileOutputStream("D:\\javawork\\weather.json");
		byte[] buffer = new byte[4096];
		int length = 0;
		
		while ((length = bis.read(buffer)) != -1) {
			fos.write(buffer, 0, length);
			fos.flush();
		}
		
		fos.close();
		bis.close();
		con.disconnect();
		
		System.out.println("Download finished!");
		
	}

}

class MyX509TrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate certificates[], String authType) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
}
