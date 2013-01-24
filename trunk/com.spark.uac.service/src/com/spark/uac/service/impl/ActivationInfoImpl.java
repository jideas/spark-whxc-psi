package com.spark.uac.service.impl;

import com.jiuqi.dna.core.type.GUID;
import com.spark.uac.entity.ActivationInfo;

public class ActivationInfoImpl implements ActivationInfo {

	/**
	 * 
	 */
	private GUID id;

	/**
	 * 目标租户ID
	 */
	private GUID targetId;

	/**
	 * 来源租户ID（对于用户激活即企业自己的ID，如果是授权激活，则是授权供应商ID）
	 */
	private GUID sourceId;

	/**
	 * 
	 */
	private GUID userId;

	/**
	 * 手机号
	 */
	private String mobileNumber;

	/**
	 * 用户姓名
	 */
	private String title;

	/**
	 * 激活密码
	 */
	private String activeCode;

	/**
	 * 激活密码创建时间
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
