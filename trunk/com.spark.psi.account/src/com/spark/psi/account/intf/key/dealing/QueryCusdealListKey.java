package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.account.intf.entity.dealing.DealingItem;

/**
 * ͨ���ͻ�/��Ӧ��guid��ѯ������ϸ�б�
 * @author yanglin
 *
 */
public class QueryCusdealListKey{

	private GUID cusproGuid;
	private Long beginDate;
	private Long endDate;
	
	private DealingItem cusdeal;
	
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

	/**
	 * ����ʽ
	 */
	private String sortType;
	
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
	public DealingItem getCusdeal() {
		return cusdeal;
	}
	public void setCusdeal(DealingItem cusdeal) {
		this.cusdeal = cusdeal;
	}
	public GUID getCusproGuid() {
		return cusproGuid;
	}
	public void setCusproGuid(GUID cusproGuid) {
		this.cusproGuid = cusproGuid;
	}
	public Long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Long beginDate) {
		this.beginDate = beginDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
}
