package com.spark.order.sales.intf.key;

import com.spark.order.intf.key.SelectKey;
import com.spark.order.intf.task.ITaskResult;

/**
 * <p>��������б�����key</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Ī��
 * @version 2011-11-20
 */
public class SaleDeploymentMainKey extends SelectKey implements ITaskResult{
	public final static String[] sortColumn = new String[]{"t.deliveryDate","t.billsNo","t.partnerNamePY","collate_gbk(t.address)"};
	private String search = null; 
	private String sortColumnName = "t.createDate asc, t.billsNo";//�����ֶ���
	private String sortType = "";//��������
	public int totalCount;
	
	public int lenght;

	/**
	 * ����������
	 * @return int
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * �����������
	 *  void
	 */
	public void clear(){
		this.sortColumnName = "t.createDate";//�����ֶ���
		this.sortType = "desc";//��������
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
