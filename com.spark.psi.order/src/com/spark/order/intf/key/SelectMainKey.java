package com.spark.order.intf.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.order.intf.type.BillsEnum;
import com.spark.order.intf.type.PageEnum;

/**
 * <p>
 * 主页面查询Key
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author 莫迪
 * @version 2011-11-10
 */
public class SelectMainKey extends SelectKey {
	private BillsEnum billsEnum; // 单据类型
	private PageEnum tabEnum; // 页签枚举
	private String search;// 搜索文本
	//private ShowTitle title; // 排序列枚举
	private String sortType = null; // 排序方式“desc”，“”
	private String sortField = null;//排序字段名称，默认为createDate
	
	//筛选
	private GUID deptGuid;//部门GUID
	private long startDate;//起始日期
	private Long endDate;//结束日期
	
	private boolean isMine;//是否只显示自己的单据
	
	/**
	 * @param billsEnum 单据类型
	 * @param tabEnum 页签枚举
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
	 * 清空当前设置值（部门GUID，起始日期，结束日期, 是否只显示自己的单据， 排序方式, 排序字段名称，搜索文本）
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

	private boolean employee;//采购员工
	private boolean manager;//采购经理
	private boolean boss;//总经理
	
	
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
	 * 单据类型
	 * 
	 * @return the billsEnum
	 */
	public BillsEnum getBillsEnum() {
		return billsEnum;
	}

	/**
	 * 页签枚举
	 * 
	 * @return the tabEnum
	 */
	public PageEnum getTabEnum() {
		return tabEnum;
	}

	/**
	 * 搜索文本
	 * 
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * 页签枚举
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
			sortType = "desc"; // 排序方式“desc”，“”
			sortField = "t.createDate";//排序字段名称，默认为createDate
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
			sortType = "desc"; // 排序方式“desc”，“”
			sortField = "t.createDate";//排序字段名称，默认为createDate
			break;
		}
	}

	/**
	 * 搜索文本
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
