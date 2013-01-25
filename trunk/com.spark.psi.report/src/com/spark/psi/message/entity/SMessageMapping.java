/**
 * 
 */
package com.spark.psi.message.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 *
 */
public class SMessageMapping{
	private GUID RECID;//	�б�ʶ	guid 
	private GUID userId;//	��Ϣ������	guid
	private GUID messageId;//��Ϣ����	guid
	private String messageType;//	��Ϣ����	char
	private long startTime;//	��ʼʱ��	date
	private long endTime;//	ֹͣʱ��	date
	/**
     * @return the rECID
     */
    public GUID getRECID(){
    	return RECID;
    }
	/**
     * @param rECID the rECID to set
     */
    public void setRECID(GUID rECID){
    	RECID = rECID;
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
	/**
     * @return the messageId
     */
    public GUID getMessageId(){
    	return messageId;
    }
	/**
     * @param messageId the messageId to set
     */
    public void setMessageId(GUID messageId){
    	this.messageId = messageId;
    }
	/**
     * @return the messageType
     */
    public String getMessageType(){
    	return messageType;
    }
	/**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType){
    	this.messageType = messageType;
    }
	/**
     * @return the startTime
     */
    public long getStartTime(){
    	return startTime;
    }
	/**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime){
    	this.startTime = startTime;
    }
	/**
     * @return the endTime
     */
    public long getEndTime(){
    	return endTime;
    }
	/**
     * @param endTime the endTime to set
     */
    public void setEndTime(long endTime){
    	this.endTime = endTime;
    }

}
