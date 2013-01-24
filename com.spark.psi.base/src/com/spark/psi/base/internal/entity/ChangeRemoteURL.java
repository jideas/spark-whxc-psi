package com.spark.psi.base.internal.entity;

import java.net.URL;

public class ChangeRemoteURL {
	
	private static URL url;	

	public static void setURL(URL url){
		ChangeRemoteURL.url = url;
	}
	
	protected static URL getUrl(){
		return url;
	}
	
}