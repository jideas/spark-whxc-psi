package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>��ȡ�ͻ�/��Ӧ���б�</p>
 *


 *
 * @author yanglin
 * @version 2011-11-14
 */
public class CusproMainListKey{

	//��ѯ�ؼ���
	private String searchKey;
	//����GUID
	private GUID deptGuid;
	//ҵ������
	private GUID busPersonGuid;
	//����
	private String cusproType;
	//������־
	private Boolean isReflag;
	//
	private boolean hasShareCus;
	
	//����
	private int total;
	//��������
//	private String sortType;
	//�����ֶ�
	private String sortColumn;
	
	//�ܽ��
	private double amount;
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * ��ѯƫ�ƣ���0��ʼ��
	 */
	private int offset;

	/**
	 * ��ѯ����
	 */
	private int count;

	/**
	 * �Ƿ��ѯ����
	 */
	private boolean queryTotal;

	/**
	 * ����ʽ
	 */
	private String sortType;
	
	public boolean isHasShareCus() {
		return hasShareCus;
	}

	public void setHasShareCus(boolean hasShareCus) {
		this.hasShareCus = hasShareCus;
	}
	
	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public Boolean getIsReflag() {
		return isReflag;
	}

	public void setIsReflag(Boolean isReflag) {
		this.isReflag = isReflag;
	}
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public GUID getDeptGuid() {
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}

	public GUID getBusinessPersonGuid() {
		return busPersonGuid;
	}

	public void setBusPersonGuid(GUID busPersonGuid) {
		this.busPersonGuid = busPersonGuid;
	}

	public String getCusproType() {
		return cusproType;
	}

	public void setCusproType(String cusproType) {
		this.cusproType = cusproType;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
