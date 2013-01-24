package com.spark.psi.inventory.intf.key.dismounting;


/**
 * 
 * <p>拆装Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class DismountingKey{
	private String searceText;
    private String sortType = "desc";
	
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
    
    private String sortField="createDate";
    
    private boolean isEmployee;
    private boolean isManager;
    private boolean isBoss;
	
    
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
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
	public boolean isBoss() {
		return isBoss;
	}
	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}
	public String getSearceText() {
		return searceText;
	}
	public void setSearceText(String searceText) {
		this.searceText = searceText;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortField() {
		return sortField;
	}
	
	
}
