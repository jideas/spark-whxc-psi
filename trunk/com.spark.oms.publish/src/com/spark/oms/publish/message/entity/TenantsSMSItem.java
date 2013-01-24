package com.spark.oms.publish.message.entity;

import java.util.ArrayList;
import java.util.List;

public class TenantsSMSItem {

	private List<MessageInfo> list = new ArrayList<MessageInfo>();
	
	public void add(MessageInfo msg){
		list.add(msg);
	}

	public List<MessageInfo> getList() {
		return list;
	}

	public void setList(List<MessageInfo> list) {
		this.list = list;
	}
	
}
