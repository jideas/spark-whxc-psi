package com.spark.oms.publish.system.entity;

import com.jiuqi.dna.core.type.GUID;

public interface OMSUserInfo {

	public GUID getUserId();
	
	public String getUserName();

	//added by gonght ������Ӫƽ̨���⻧�����ֻ�����
	public GUID getTenantsId();
	
	public String getTenantsName();	
}
