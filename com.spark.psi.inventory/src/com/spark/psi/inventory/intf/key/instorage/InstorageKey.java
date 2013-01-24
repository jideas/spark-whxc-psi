/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-4       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * 入库Key
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class InstorageKey {

	/**
	 * 模糊查询关键字 匹配：入库单号、相关单据、仓库
	 */
	private String searchKey;

	/**
	 * 入库状态
	 */
	private String instoState;
	/**
	 * 入库类型
	 */
	private String instoType;
	/**
	 * 可操作仓库集合
	 */
	private List<GUID> actionStores;
	/**
	 * 排序字符串
	 */
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
	/**
	 * 查询历史记录时的时间区间
	 */
	private Long beginTime;
	private Long endTime;

	private GUID relaOrdGuid, ID;

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

	/**
	 * @return the beginTime
	 */
	public Long getBeginTime() {
		return beginTime;
	}

	/**
	 * @return the sortColumnName
	 */
	public String getSortColumnName() {
		return sortColumnName;
	}

	/**
	 * @param sortColumnName
	 *            the sortColumnName to set
	 */
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	} 

	/**
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the instoState
	 */
	public String getInstoState() {
		return instoState;
	}

	/**
	 * @param instoState
	 *            the instoState to set
	 */
	public void setInstoState(String instoState) {
		this.instoState = instoState;
	}

	/**
	 * @return the actionStores
	 */
	public List<GUID> getActionStores() {
		return actionStores;
	}

	/**
	 * @param actionStores
	 *            the actionStores to set
	 */
	public void setActionStores(List<GUID> actionStores) {
		this.actionStores = actionStores;
	}

	/**
	 * 添加一个可操作仓库
	 */
	public void addActionStores(GUID... recid) {
		if (null == this.actionStores) {
			this.actionStores = new ArrayList<GUID>();
		}
		for(GUID guid : recid){
			this.actionStores.add(guid);	        
        }

	}

	/**
	 * @return the instoType
	 */
	public String getInstoType() {
		return instoType;
	}

	/**
	 * @param instoType
	 *            the instoType to set
	 */
	public void setInstoType(String instoType) {
		this.instoType = instoType;
	}

	public void setRelaOrdGuid(GUID relaOrdGuid) {
		this.relaOrdGuid = relaOrdGuid;
	}

	public GUID getRelaOrdGuid() {
		return relaOrdGuid;
	}

	public void setID(GUID iD) {
		ID = iD;
	}

	public GUID getID() {
		return ID;
	}
}
