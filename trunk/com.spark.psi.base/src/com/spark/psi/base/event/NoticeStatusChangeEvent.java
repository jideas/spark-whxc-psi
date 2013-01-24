package com.spark.psi.base.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>公共生效过期事件</p>
 *


 *
 
 * @version 2012-4-26
 */
public class NoticeStatusChangeEvent extends Event{
	
	public enum NoticeAction{
		/**
		 * 过期
		 */
		Expired,
		/**
		 * 生效
		 */
		Effectual
	}
	
	private NoticeAction action;

	/**
     * @return the tenantId
     */
    public GUID getTenantId(){
    	return tenantId;
    }

	/**
     * @param tenantId the tenantId to set
     */
    public void setTenantId(GUID tenantId){
    	this.tenantId = tenantId;
    }

	private GUID id;

	private GUID tenantId;
	
	public NoticeStatusChangeEvent(GUID id,GUID tenantId,NoticeAction action){
	    this.id = id;
	    this.tenantId = tenantId;
	    this.action = action;
    }

	public NoticeAction getAction(){
    	return action;
    }

	public GUID getId(){
    	return id;
    }
	
}
