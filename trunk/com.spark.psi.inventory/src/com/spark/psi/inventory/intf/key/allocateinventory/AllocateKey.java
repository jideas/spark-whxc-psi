
package com.spark.psi.inventory.intf.key.allocateinventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.inventoryenum.AllocateTab;
import com.spark.psi.publish.InventoryAllocateStatus;
/**
 * 
 * <p>调拨Key</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class AllocateKey{
	public enum QueryObjType {
		BOSS,
		DEPARTMENT,
		EMPLOYEE
	}
	private GUID queryObjGuid;
	private QueryObjType queryObjType;
	private InventoryAllocateStatus[] dealState;
	
	private AllocateTab showContent;
	
	private String searchString;
	
	private long beginTime;
	private long endTime;
	
	//用于排序
	private String sortColumnName;
	
	private String sortType;
	
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
	private boolean isLimit = true;
	
	/**
	 * 设置查询对象的GUID
	 * @param queryObjGuid
	 */
	public void setQueryObjGuid(GUID queryObjGuid) {
		this.queryObjGuid = queryObjGuid;
	}
	
	/**
	 * 设置查询对像类型
	 * @param queryObjType
	 */
	public void setQueryObjType(QueryObjType queryObjType) {
		this.queryObjType = queryObjType;
	}
	/**
	 * 设置要显示的页签
	 * @param showContent
	 */
	public void setShowContent(AllocateTab showContent) {
		this.showContent = showContent;
	}
	/**
	 * 设置排序字段
	 * @param sortColumnName
	 */
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}
	/**
	 * 设置排序方式
	 * @param sortType
	 */
//	public void setSortType(String sortType) {
//		this.sortType = sortType;
//	}
	/**
	 * 设置搜索字符串
	 * @param searchString
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public GUID getQueryObjGuid() {
		return queryObjGuid;
	}
	
	public QueryObjType getQueryObjType() {
		return queryObjType;
	}
	
	public AllocateTab getShowContent() {
		return showContent;
	}
//	public String getSortType() {
//		return sortType;
//	}
	public String getSortColumnName() {
		return sortColumnName;
	}
	public String getSearchString() {
		return searchString;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public boolean isLimit() {
		return isLimit;
	}
	public void setLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}
	public void setdealState(InventoryAllocateStatus[] dealState) {
		this.dealState = dealState;
	}
	public InventoryAllocateStatus[] getdealState() {
		return dealState;
	}
	
}
