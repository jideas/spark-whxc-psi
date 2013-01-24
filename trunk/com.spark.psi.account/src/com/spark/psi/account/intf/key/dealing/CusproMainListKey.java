package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>获取客户/供应商列表</p>
 *


 *
 * @author yanglin
 * @version 2011-11-14
 */
public class CusproMainListKey{

	//查询关键字
	private String searchKey;
	//部门GUID
	private GUID deptGuid;
	//业务负责人
	private GUID busPersonGuid;
	//类型
	private String cusproType;
	//关联标志
	private Boolean isReflag;
	//
	private boolean hasShareCus;
	
	//总数
	private int total;
	//排序类型
//	private String sortType;
	//排序字段
	private String sortColumn;
	
	//总金额
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
	 * 查询偏移（从0开始）
	 */
	private int offset;

	/**
	 * 查询数量
	 */
	private int count;

	/**
	 * 是否查询总数
	 */
	private boolean queryTotal;

	/**
	 * 排序方式
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
