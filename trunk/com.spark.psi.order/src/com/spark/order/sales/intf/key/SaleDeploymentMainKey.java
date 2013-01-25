package com.spark.order.sales.intf.key;

import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>出库分配列表数据key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author 莫迪
 * @version 2011-11-20
 */
public class SaleDeploymentMainKey extends SelectKey implements ITaskResult{
	public final static String[] sortColumn = new String[]{"t.deliveryDate","t.billsNo","t.partnerNamePY","collate_gbk(t.address)"};
	private String search = null; 
	private String sortColumnName = "t.createDate asc, t.billsNo";//排序字段名
	private String sortType = "";//排序类型
	public int totalCount;
	
	public int lenght;

	/**
	 * 单据总数量
	 * @return int
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * 清空搜索类容
	 *  void
	 */
	public void clear(){
		this.sortColumnName = "t.createDate";//排序字段名
		this.sortType = "desc";//排序类型
		this.search = null;
	}
	/**
	 * @return the sortColumnName
	 */
	public String getSortColumnName() {
		return sortColumnName;
	}
	/**
	 * @return the sortType
	 */
	@Override
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortColumnName the sortColumnName to set
	 */
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}
	/**
	 * @param sortColumnName the sortColumnName to set
	 */
	public void setSortColumnName(int index) {
		this.sortColumnName = sortColumn[index];
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	} 
	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	} 
	public boolean isSucceed() {
		return true;
	}
	public int lenght() {
		return lenght;
	}
}
