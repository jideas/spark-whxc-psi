/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.Outstorage.key
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-4       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * 
 * <p>出库Key</p> 
 * @version 2012-3-2
 */
public class OutstorageKey{
	/**
	 * 模糊查询关键字
	 * 匹配：出库单号、相关单据、仓库
	 */
	private String searchKey;

	/**
	 * 出库状态
	 */
	private String outstoState;
	/**
	 * 出库类型
	 */
	private CheckingOutType OutstoType;
	/**
	 * 可操作仓库集合
	 */
	private List<GUID> actionStores; 
	/**
	 * 查询历史记录时的时间区间
	 */
	private Long beginTime;
	private Long endTime;
	
	private GUID relaOrdGuid;

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
     * @return the sortColumnName
     */
    public String getSortColumnName(){
    	return sortColumnName;
    }

	/**
     * @param sortColumnName the sortColumnName to set
     */
    public void setSortColumnName(String sortColumnName){
    	this.sortColumnName = sortColumnName;
    } 

	/**
	 * @return the beginTime
	 */
	public Long getBeginTime(){
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Long beginTime){
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public Long getEndTime(){
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Long endTime){
		this.endTime = endTime;
	}

	public String getSearchKey(){
		return searchKey;
	}

	public void setSearchKey(String searchKey){
		this.searchKey = searchKey;
	}

	
	/**
	 * @return the outstoState
	 */
	public String getOutstoState(){
		return outstoState;
	}

	/**
	 * @param outstoState the outstoState to set
	 */
	public void setOutstoState(String outstoState){
		this.outstoState = outstoState;
	}

	/**
	 * @return the actionStores
	 */
	public List<GUID> getActionStores(){
		return actionStores;
	}

	/**
	 * @param actionStores the actionStores to set
	 */
	public void setActionStores(List<GUID> actionStores){
		this.actionStores = actionStores;
	}

	/**
	 * 添加一个可操作仓库
	 */
	public void addActionStores(GUID... recid){
		if(null == this.actionStores){
			this.actionStores = new ArrayList<GUID>();
		}
		for(GUID guid : recid){	        
			this.actionStores.add(guid);
        }

	}

	/**
	 * @return the OutstoType
	 */
	public CheckingOutType getOutstoType(){
		return OutstoType;
	}

	/**
	 * @param OutstoType the OutstoType to set
	 */
	public void setOutstoType(CheckingOutType OutstoType){
		this.OutstoType = OutstoType;
	}

	public void setRelaOrdGuid(GUID relaOrdGuid) {
		this.relaOrdGuid = relaOrdGuid;
	}

	public GUID getRelaOrdGuid() {
		return relaOrdGuid;
	}
}
