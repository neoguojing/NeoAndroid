package com.neo.neoapp;

import android.net.Uri;

public class NeoAppSetings {
	
	public static final String IpServerUrl = "http://mainapp.applinzi.com/nat/obtain/neo/";
	
	private static String prefix = "http://";
	
	public static final String ConfigFile = "config.json";
	
	public static Uri getServerUrl(String ip,String port)
	{
		return Uri.parse(prefix+ip+port);
	}
	
	public static String getServerUrlString(String ip,String port)
	{
		return (prefix+ip+port);
	}
}
