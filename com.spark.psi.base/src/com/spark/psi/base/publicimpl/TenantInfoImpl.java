package com.spark.psi.base.publicimpl;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.base.config.entity.TenantInfo;

@StructClass
public class TenantInfoImpl implements TenantInfo{

	/**
	 * 直供模式
	 */
	private boolean directDelivery;
	
	/**
	 * 名称
	 */
	private String title;
	
	private GUID id;
	
	private int userCount;
	
	/**
	 * 已初始化往来款
	 */
	private boolean dealingsInited;


	/**
	 * @return the directDelivery
	 */
	public boolean isDirectDelivery() {
		return directDelivery;
	}

	public void setDirectDelivery(boolean directDelivery){
    	this.directDelivery = directDelivery;
    }

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserCount(){
    	return userCount;
    }

	public void setUserCount(int userCount){
    	this.userCount = userCount;
    }

	public boolean isDealingsInited(){
    	return dealingsInited;
    }

	public void setDealingsInited(boolean dealingsInited){
    	this.dealingsInited = dealingsInited;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }
	

}
