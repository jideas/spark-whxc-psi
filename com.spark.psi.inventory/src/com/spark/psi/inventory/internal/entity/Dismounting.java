package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>拆装</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class Dismounting{
	private GUID RECID;//
	private GUID tenantsGuid;//
	private long dismDate;
	private String dismOrdNo;//
	private GUID markPerson;//
	private String markName;//
	private GUID deptGuid;//
	private GUID storeGuid;//
	private String storeName;//
	private String storePY;//
	private String createPerson;//
	private long createDate;
	
	
	
	public void setRECID(GUID rECID) {
		RECID = rECID;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public void setDismDate(Long dismDate) {
		this.dismDate = dismDate;
	}
	public void setDismOrdNo(String dismOrdNo) {
		this.dismOrdNo = dismOrdNo;
	}
	public void setMarkPerson(GUID markPerson) {
		this.markPerson = markPerson;
	}
	public void setMarkName(String markName) {
		this.markName = markName;
	}
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setStorePY(String storePY) {
		this.storePY = storePY;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public GUID getRECID() {
		return RECID;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public Long getDismDate() {
		return dismDate;
	}
	public String getDismOrdNo() {
		return dismOrdNo;
	}
	public GUID getMarkPerson() {
		return markPerson;
	}
	public String getMarkName() {
		return markName;
	}
	public GUID getDeptGuid() {
		return deptGuid;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public String getStoreName() {
		return storeName;
	}
	public String getStorePY() {
		return storePY;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public Long getCreateDate() {
		return createDate;
	}
}
