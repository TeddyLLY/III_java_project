package com.tool;

import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/* 1. 透過javax.net.ssl.X509TrustManager介面以繞過https認證
 * 2. 這是解決因為PKIX路徑創建失敗時，產生以下三種Exception時的解決方式  
 *      - javax.net.ssl.SSLHandshakeException
 *   	  - sun.security.validator.ValidatorException
 *   	  - sun.security.provider.certpath.SunCertPathBuilderException
 * */

public class HttpsUtil {
	
	public static URLConnection getURLConnection (String httpsURL) throws Exception {
		
		// 透過javax.net.ssl.X509TrustManager介面以繞過https認證
		TrustManager[] trustManagers = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			@Override
			public X509Certificate[] getAcceptedIssuers() {return null;}
		} };
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustManagers, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		// 創建全信任的主機名稱驗證器 Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {return true;}
		};
		// 安裝全信任的主機驗證器 Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		URL url = new URL(httpsURL);
		URLConnection urlConn = url.openConnection();
		return urlConn;
		
	}
}