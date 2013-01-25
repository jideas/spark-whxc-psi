package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;


public interface TenantInfo  {
	
	public GUID getId();

	public String getTitle();
	
	/**
	 * 是否已开启直供模式
	 */
	public boolean isDirectDelivery();
	
	/**
	 * 获得可用用户数
	 * 
	 * @return int
	 */
	public int getUserCount();
	
	/**
	 * 是否已经初始化往来款
	 * 
	 * @return boolean
	 */
	public boolean isDealingsInited();
	
//	/**
//	 */
//	
//	public String getSystemName();
//	
}
