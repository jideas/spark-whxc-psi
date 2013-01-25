/**
 * 
 */
package com.spark.psi.message.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SMessageTemplateEnum;
import com.spark.psi.publish.SMessageType;

/**
 *
 */
public class SMessageDelTask extends SimpleTask{

	private SMessageType type;
	private GUID objId;
	private GUID userId;
	private GUID tenantId;
	private SMessageTemplateEnum temp;

	public SMessageDelTask(SMessageType type, GUID objId){
		this.type = type;
		this.objId = objId;
	}

	public SMessageDelTask(SMessageType type, GUID objId, GUID userId){
		this.type = type;
		this.objId = objId;
		this.userId = userId;
	}

	/**
     * @return the temp
     */
    public SMessageTemplateEnum getTemp(){
    	return temp;
    }

	/**
     * @param temp the temp to set
     */
    public void setTemp(SMessageTemplateEnum temp){
    	this.temp = temp;
    }

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

	/**
	 * @return the type
	 */
	public SMessageType getType(){
		return type;
	}

	public void setType(SMessageType type){
		this.type = type;
	}

	/**
	 * @return the objId
	 */
	public GUID getObjId(){
		return objId;
	}

	/**
	 * @return the userId
	 */
	public GUID getUserId(){
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(GUID userId){
		this.userId = userId;
	}
}
