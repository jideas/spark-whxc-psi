package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>库存盘点</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class CheckInventory{
	private GUID recid;
	private GUID tenantsGuid;//租户ID
	private long startDate;//盘点开始时间
	private long endDate;//盘点结束时间
	private String checkOrdNo;//盘点单编号（单号）
	private String checkOrdState;//盘点单据状态,01是盘点中，02是已完成
	private String remark;//备注
	private String checkObj;//盘点对象
	private String checkPerson;//盘点人
	private GUID markPerson;//制单人
	private String markName;//制单人姓名
	private GUID deptGuid;//部门
	private GUID storeGuid;//仓库
	private String storeName;//仓库名称
	private String storePY;//仓库拼音
	private String createPerson;//创建人
	private long createDate;//创建时间
	private String StoreStatus;//仓库状态
	private int checkProfit;//盘盈数量
	private int checkDeficient;//盘亏数量
	
	
	public String getStoreStatus() {
		return StoreStatus;
	}
	public void setStoreStatus(String StoreStatus) {
		this.StoreStatus = StoreStatus;
	}
	public GUID getRecid() {
		return recid;
	}
	public void setRecid(GUID recid) {
		this.recid = recid;
	}
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public void setTenantsGuid(GUID tenantsGuid) {
		this.tenantsGuid = tenantsGuid;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public String getCheckOrdNo() {
		return checkOrdNo;
	}
	public void setCheckOrdNo(String checkOrdNo) {
		this.checkOrdNo = checkOrdNo;
	} 
	public String getCheckOrdState() {
		return checkOrdState;
	}
	public void setCheckOrdState(String checkOrdState) {
		this.checkOrdState = checkOrdState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCheckObj() {
		return checkObj;
	}
	public void setCheckObj(String checkObj) {
		this.checkObj = checkObj;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public GUID getMarkPerson() {
		return markPerson;
	}
	public void setMarkPerson(GUID markPerson) {
		this.markPerson = markPerson;
	}
	public String getMarkName() {
		return markName;
	}
	public void setMarkName(String markName) {
		this.markName = markName;
	}
	public GUID getDeptGuid() {
		return deptGuid;
	}
	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}
	public GUID getStoreGuid() {
		return storeGuid;
	}
	public void setStoreGuid(GUID storeGuid) {
		this.storeGuid = storeGuid;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStorePY() {
		return storePY;
	}
	public void setStorePY(String storePY) {
		this.storePY = storePY;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public int getCheckProfit() {
		return checkProfit;
	}
	public void setCheckProfit(int checkProfit) {
		this.checkProfit = checkProfit;
	}
	public int getCheckDeficient() {
		return checkDeficient;
	}
	public void setCheckDeficient(int checkDeficient) {
		this.checkDeficient = checkDeficient;
	}
	
	
	
	
	
}
