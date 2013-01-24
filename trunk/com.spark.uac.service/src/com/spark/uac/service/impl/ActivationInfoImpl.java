package com.spark.uac.service.impl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.ActivationInfo;

public class ActivationInfoImpl implements ActivationInfo {

	/**
	 * 
	 */
	private GUID id;

	/**
	 * Ŀ���⻧ID
	 */
	private GUID targetId;

	/**
	 * ��Դ�⻧ID�������û������ҵ�Լ���ID���������Ȩ���������Ȩ��Ӧ��ID��
	 */
	private GUID sourceId;

	/**
	 * 
	 */
	private GUID userId;

	/**
	 * �ֻ���
	 */
	private String mobileNumber;

	/**
	 * �û�����
	 */
	private String title;

	/**
	 * ��������
	 */
	private String activeCode;

	/**
	 * �������봴��ʱ��
	 */
	private long activeCodeCreateTime;

	/**
	 * @return the targetId
	 */
	public GUID getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(GUID targetId) {
		this.targetId = targetId;
	}

	/**
	 * @return the sourceId
	 */
	public GUID getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 *            the sourceId to set
	 */
	public void setSourceId(GUID sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNo() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNo(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the activeCode
	 */
	public String getActiveCode() {
		return activeCode;
	}

	/**
	 * @param activeCode
	 *            the activeCode to set
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	/**
	 * @return the activeCodeCreateTime
	 */
	public long getActiveCodeCreateTime() {
		return activeCodeCreateTime;
	}

	/**
	 * @param activeCodeCreateTime
	 *            the activeCodeCreateTime to set
	 */
	public void setActiveCodeCreateTime(long activeCodeCreateTime) {
		this.activeCodeCreateTime = activeCodeCreateTime;
	}

	/**
	 * @return the id
	 */
	public GUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(GUID id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public GUID getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(GUID userId) {
		this.userId = userId;
	}

}
