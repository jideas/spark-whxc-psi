package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>����̵�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-1
 */
public class CheckInventory{
	private GUID recid;
	private GUID tenantsGuid;//�⻧ID
	private long startDate;//�̵㿪ʼʱ��
	private long endDate;//�̵����ʱ��
	private String checkOrdNo;//�̵㵥��ţ����ţ�
	private String checkOrdState;//�̵㵥��״̬,01���̵��У�02�������
	private String remark;//��ע
	private String checkObj;//�̵����
	private String checkPerson;//�̵���
	private GUID markPerson;//�Ƶ���
	private String markName;//�Ƶ�������
	private GUID deptGuid;//����
	private GUID storeGuid;//�ֿ�
	private String storeName;//�ֿ�����
	private String storePY;//�ֿ�ƴ��
	private String createPerson;//������
	private long createDate;//����ʱ��
	private String StoreStatus;//�ֿ�״̬
	private int checkProfit;//��ӯ����
	private int checkDeficient;//�̿�����
	
	
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
