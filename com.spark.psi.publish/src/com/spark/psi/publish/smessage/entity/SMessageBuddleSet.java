package com.spark.psi.publish.smessage.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spark.psi.publish.Auth;
import com.spark.psi.publish.SMessageType;

public class SMessageBuddleSet{

	private Map<SMessageType, Boolean> bubbling;
	private Map<SMessageType, Boolean> monitor;

	public void putMonitor(SMessageType key, boolean value){
		if(null == monitor){
			monitor = new HashMap<SMessageType, Boolean>();
		}
		monitor.put(key, value);
	}

	public void putBubbling(SMessageType key, boolean value){
		if(null == bubbling){
			bubbling = new HashMap<SMessageType, Boolean>();
		}
		bubbling.put(key, value);
	}

	public boolean getBuddling(SMessageType key){
		if(bubbling == null){
			return true;
		}
		Boolean b = bubbling.get(key);
		if(b == null){
			b = true;
		}
		return b;
	}

	public boolean getMonitor(SMessageType key,List<Auth> auth){
		if(monitor == null){
			return key.getDefShowMonitor(auth);
		}
		Boolean b = monitor.get(key);
		if(b == null){
			b = key.getDefShowMonitor(auth);
		}
		return b;
	}
}
