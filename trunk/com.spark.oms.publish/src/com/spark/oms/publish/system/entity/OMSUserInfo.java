package com.spark.oms.publish.system.entity;

import com.jiuqi.dna.core.type.GUID;

public interface OMSUserInfo {

	public GUID getUserId();
	
	public String getUserName();

	//added by gonght 用于运营平台向租户发送手机短信
	public GUID getTenantsId();
	
	public String getTenantsName();	
}
