/**
 * 
 */
package com.spark.psi.report.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * �������ʵ��
 */
public class ReportQueue {

	public ReportQueue() {

	}

	private GUID RECID;// �б�ʶ guid
	private GUID tenantId;// �⻧��� guid
	private String eventClass;// �¼����� varchar
	private String attributeXml;// ����xml varchar
	private long currTime;// ����ʱ��� long
	private GUID userId;// �����¼��û�id guid
	/**
	 * @return the rECID
	 */
	public GUID getRECID() {
		return RECID;
	}
	/**
	 * @param rECID the rECID to set
	 */
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	/**
	 * @return the tenantId
	 */
	public GUID getTenantsGuid() {
		return tenantId;
	}
	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantsGuid(GUID tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * @return the eventClass
	 */
	public String getEventClass() {
		return eventClass;
	}
	/**
	 * @param eventClass the eventClass to set
	 */
	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}
	/**
	 * @return the attributeXml
	 */
	public String getAttributeXml() {
		return attributeXml;
	}
	/**
	 * @param attributeXml the attributeXml to set
	 */
	public void setAttributeXml(String attributeXml) {
		this.attributeXml = attributeXml;
	}
	/**
	 * @return the currTime
	 */
	public long getCurrTime() {
		return currTime;
	}
	/**
	 * @param currTime the currTime to set
	 */
	public void setCurrTime(long currTime) {
		this.currTime = currTime;
	}
	/**
	 * @return the userId
	 */
	public GUID getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(GUID userId) {
		this.userId = userId;
	}

}
