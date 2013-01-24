/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.bus.finance.invoice.intf.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.invoice;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SortType;

/**
 * <p>开票主列表查询KEY</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2011-11-28
 */

public class InvoiceKey{
 
	private GUID deptGuid;
	private long startDate;
    private long endDate;
    private String searchText;
    private String sortField;
    private SortType sortType;
    
    private int totalCoun;
	private double taotalAmount;
	private GUID cusGuid;
	
	private GUID tenantsGuid;
	private GUID authDeptGuid;
	private GUID empGuid;
	
	/**
	 * 是否财务角色
	 */
	private boolean isFinanceRole;
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
	/**
	 * 总经理传租户ID，经理传部门ID，员工传员工ID
	 * @param tenantsGuid
	 * @param authDeptGuid
	 * @param empGuid
	 */
//	public SearchKey(GUID tenantsGuid,GUID authDeptGuid,GUID empGuid)
//	{
//		this.tenantsGuid = tenantsGuid;
//		this.authDeptGuid = authDeptGuid;
//		this.empGuid = empGuid;
//	}
	public int getTotalCoun() {
		return totalCoun;
	}
	public void setTotalCoun(int totalCoun) {
		this.totalCoun = totalCoun;
	}
	public double getTaotalAmount() {
		return taotalAmount;
	}
	public void setTaotalAmount(double taotalAmount) {
		this.taotalAmount = taotalAmount;
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
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}
	public SortType getSortType() {
		return sortType;
	}
	public void setCusGuid(GUID cusGuid) {
		this.cusGuid = cusGuid;
	}
	public GUID getCusGuid() {
		return cusGuid;
	}
	
	public GUID getTenantsGuid() {
		return tenantsGuid;
	}
	public GUID getAuthDeptGuid() {
		return authDeptGuid;
	}
	public void setEmpGuid(GUID empGuid) {
		this.empGuid = empGuid;
	}
	public GUID getEmpGuid() {
		return empGuid;
	}
	public void setFinanceRole(boolean isFinanceRole) {
		this.isFinanceRole = isFinanceRole;
	}
	public boolean isFinanceRole() {
		return isFinanceRole;
	}
}
