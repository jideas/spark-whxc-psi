package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;

/**
 * <p>
 * ��ҳ���ѯKey
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-11-10
 */
public class SelectMainKey extends SelectKey {
	private BillsEnum billsEnum; // ��������
	private PageEnum tabEnum; // ҳǩö��
	private String search;// �����ı�
	//private ShowTitle title; // ������ö��
	private String sortType = null; // ����ʽ��desc��������
	private String sortField = null;//�����ֶ����ƣ�Ĭ��ΪcreateDate
	
	//ɸѡ
	private GUID deptGuid;//����GUID
	private long startDate;//��ʼ����
	private Long endDate;//��������
	
	private boolean isMine;//�Ƿ�ֻ��ʾ�Լ��ĵ���
	
	/**
	 * @param billsEnum ��������
	 * @param tabEnum ҳǩö��
	 */
	public SelectMainKey(BillsEnum billsEnum, PageEnum tabEnum) {
		this.billsEnum = billsEnum;
		this.tabEnum = tabEnum;
		initSort();
	}
	
	public void setBillsEnum(BillsEnum billsEnum) {
		this.billsEnum = billsEnum;
	}
	
	/**
	 * ��յ�ǰ����ֵ������GUID����ʼ���ڣ���������, �Ƿ�ֻ��ʾ�Լ��ĵ��ݣ� ����ʽ, �����ֶ����ƣ������ı���
	 *  void
	 */
	public void clear(){
		this.deptGuid = null;
		this.startDate = 0;
		this.endDate = null;
		this.isMine = false;
		this.sortType = "desc";
		this.sortField = "payDate";
		this.search = null; 
	}
	
	/**
	 * @return the sortType
	 */
	@Override
	public String getSortType() {
		return sortType;
	}

	/**
	 * @return the sortField
	 */
	public String getSortField() {
		return sortField;
	}

	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public GUID getDeptGuid() {
		return deptGuid;
	}

	public void setDeptGuid(GUID deptGuid) {
		this.deptGuid = deptGuid;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	private boolean employee;//�ɹ�Ա��
	private boolean manager;//�ɹ�����
	private boolean boss;//�ܾ���
	
	
	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	/**
	 * ��������
	 * 
	 * @return the billsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}

	/**
	 * ҳǩö��
	 * 
	 * @return the tabEnum
	 */
	public PageEnum getTabEnum() {
		return tabEnum;
	}

	/**
	 * �����ı�
	 * 
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * ҳǩö��
	 * 
	 * @param tabEnum
	 *            the tabEnum to set
	 */
	public void setTabEnum(PageEnum tabEnum) {
		this.tabEnum = tabEnum;
		initSort();
	}

	protected static final String sort_one = "t.createDate asc, t.billsNo";
	
	protected void initSort() {
		if(null == tabEnum){
			sortType = "desc"; // ����ʽ��desc��������
			sortField = "t.createDate";//�����ֶ����ƣ�Ĭ��ΪcreateDate
			return;
		}
		switch (tabEnum) {   
		case NEW:
			sortField = sort_one;
			sortType = "asc";
			break;
		case NEW_CANCEL:
			sortField = "t.createDate";
			sortType = "desc";
			break;
		case EXAMINE:
			sortField = sort_one;
			sortType = "asc";
			break;
		case FOLLOW:
			sortField = sort_one;
			sortType = "asc";
			break;
		case LOG:
			sortField = "t.createDate";
			sortType = "desc";
			break;
		default:
			sortType = "desc"; // ����ʽ��desc��������
			sortField = "t.createDate";//�����ֶ����ƣ�Ĭ��ΪcreateDate
			break;
		}
	}

	/**
	 * �����ı�
	 * 
	 * @param search
	 *            the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	} 

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isMine() {
		return isMine;
	}

}
