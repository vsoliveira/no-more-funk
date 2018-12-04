package com.victor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	
	protected HttpUtil() {
		
	}
	
	public static void closeHttpEntity(CloseableHttpResponse entity) {
		try {
			if (entity != null) {
				EntityUtils.consume(entity.getEntity());
				entity.close();
			}
		} catch (IOException e) {
		}
	}

	public static String convertToURLEscapingIllegalCharacters(String string){
		try {
			String decodedURL = URLDecoder.decode(string, "UTF-8");
			URL url = new URL(decodedURL);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()); 
			return uri.toURL().toString(); 
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
