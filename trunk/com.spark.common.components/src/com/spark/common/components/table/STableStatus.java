package com.spark.common.components.table;

/**
 * 表格当前的状态
 * 
 */
public class STableStatus {

	public static final int PAGESIZE = 20;
	
	public static final int FIRSTPAGE = 0;
	
	private int pageNo = 0;
	
	/**
	 * 排序列
	 */
	private String sortColumn;

	/**
	 * 排序方向
	 */
	private SSortDirection sortDirection;

	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * @param sortColumn
	 *            the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * @return the sortDirection
	 */
	public SSortDirection getSortDirection() {
		return sortDirection;
	}

	/**
	 * @param sortDirection
	 *            the sortDirection to set
	 */
	public void setSortDirection(SSortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getBeginIndex() {
		return pageNo * PAGESIZE;
//		return 0;
	}
	
	public int getPageSize() {
		//return PAGESIZE * (pageNo + 1);
		return PAGESIZE;
	}
	
	public void resetPageInfo() {
		pageNo = 0;
	}
	
}
