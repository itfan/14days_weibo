package com.lxt008.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;

public class NetworkUtil {
	public static void main(String[] args) {
		getIpAddress();
	}
	
	/**
	 * 给定网络路径获取输入流
	 * @param netPath
	 * @return
	 */
	public InputStream openNetInStream(String netPath)
	{
		InputStream is=null;
		List<String> list=null;
		try {
			URL url=new URL(netPath);
			URLConnection conn=url.openConnection();
			is=conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	public static String getIpAddress()
	{
		String ipAddress=null;
		try {
			InetAddress netAddress=InetAddress.getLocalHost();
			System.out.println(netAddress.getHostAddress());
		} catch (UnknownHostException e) {		
			e.printStackTrace();
		}
		
		return ipAddress;
	}
	
	
}
