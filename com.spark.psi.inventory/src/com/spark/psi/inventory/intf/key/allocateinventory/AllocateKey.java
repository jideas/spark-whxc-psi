
package com.spark.psi.inventory.intf.key.allocateinventory;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.intf.inventoryenum.AllocateTab;
import com.spark.psi.publish.InventoryAllocateStatus;
/**
 * 
 * <p>����Key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
	
	//��������
	private String sortColumnName;
	
	private String sortType;
	
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
	 * ���ò�ѯ�����GUID
	 * @param queryObjGuid
	 */
	public void setQueryObjGuid(GUID queryObjGuid) {
		this.queryObjGuid = queryObjGuid;
	}
	
	/**
	 * ���ò�ѯ��������
	 * @param queryObjType
	 */
	public void setQueryObjType(QueryObjType queryObjType) {
		this.queryObjType = queryObjType;
	}
	/**
	 * ����Ҫ��ʾ��ҳǩ
	 * @param showContent
	 */
	public void setShowContent(AllocateTab showContent) {
		this.showContent = showContent;
	}
	/**
	 * ���������ֶ�
	 * @param sortColumnName
	 */
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}
	/**
	 * ��������ʽ
	 * @param sortType
	 */
//	public void setSortType(String sortType) {
//		this.sortType = sortType;
//	}
	/**
	 * ���������ַ���
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
