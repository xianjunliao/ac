package com.ac.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;

import org.json.JSONObject;

public class Sample {

	private static final String serverURL = "http://vop.baidu.com/server_api";
	private static String token = "";
	private static final String testFileName = "test.pcm";
	// put your own params here
	private static final String apiKey = "Ta2BDMKh7DklyUM14qWIKcAB";
	private static final String secretKey = "7840f109a58e9b805008a555799ae90f";

	public static void main(String[] args) throws Exception {
		getToken("hahahahahah");
	}


	public static void getToken(String text) throws Exception {
		String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials"
				+ "&client_id=" + apiKey + "&client_secret=" + secretKey;
		HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL)
				.openConnection();
		token = new JSONObject(printResponse(conn)).getString("access_token");
		InetAddress ia = InetAddress.getLocalHost();
		String string = "http://tsn.baidu.com/text2audio?tex="+text+"&lan=zh&cuid="+getLocalMac(ia)+"&ctp=1&tok="
				+ token;
		Runtime.getRuntime().exec(
				"rundll32 url.dll,FileProtocolHandler " + string + "");
	}


	private static String printResponse(HttpURLConnection conn)
			throws Exception {
		if (conn.getResponseCode() != 200) {
			// request error
			return "";
		}
		InputStream is = conn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();
		System.out.println(new JSONObject(response.toString()).toString(4));
		return response.toString();
	}


	private static String getLocalMac(InetAddress ia) throws SocketException {
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.out.println("mac数组长度：" + mac.length);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			System.out.println("每8位:" + str);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
	}
}
