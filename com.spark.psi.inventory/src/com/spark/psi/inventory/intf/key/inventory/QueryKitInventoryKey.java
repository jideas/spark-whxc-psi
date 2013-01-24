/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-19       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.inventory;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SortType;

/**
 * @author zhongxin
 *
 */
public class QueryKitInventoryKey {
//	private GUID otherGoodsGuid;
	
	private String goodsName;
	private String goodsUnit;
	private String goodsDescription;
	
	private Boolean isInit;
	
	private boolean isQuerySummary = false;
	
	private String sortColumn;
	
	private GUID storeId;
	
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

	/**
	 * �����ı�
	 */
	private String searchText;
	
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	/**
	 * 
	 * @param goodsName ��Ʒ����
	 * @param goodsUnit ��Ʒ��λ
	 * @param goodsDescription ��Ʒ����
	 */
	public QueryKitInventoryKey(String goodsName, String goodsUnit, String goodsDescription) {
		this.goodsDescription = goodsDescription;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
	}

	public QueryKitInventoryKey() {
	}
//	public GUID getOtherGoodsGuid() {
//		return otherGoodsGuid;
//	}
//
//	public void setOtherGoodsGuid(GUID otherGoodsGuid) {
//		this.otherGoodsGuid = otherGoodsGuid;
//	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}


	public Boolean getIsInit() {
		return isInit;
	}

	public void setIsInit(Boolean isInit) {
		this.isInit = isInit;
	}

	public boolean isQuerySummary() {
		return isQuerySummary;
	}

	public void setQuerySummary(boolean isQuerySummary) {
		this.isQuerySummary = isQuerySummary;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public GUID getStoreId() {
		return storeId;
	}

}
