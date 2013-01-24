/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-4       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import java.util.ArrayList;
import java.util.List;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>
 * ���Key
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Wangtiancai
 * @version 2012-3-2
 */
public class InstorageKey {

	/**
	 * ģ����ѯ�ؼ��� ƥ�䣺��ⵥ�š���ص��ݡ��ֿ�
	 */
	private String searchKey;

	/**
	 * ���״̬
	 */
	private String instoState;
	/**
	 * �������
	 */
	private String instoType;
	/**
	 * �ɲ����ֿ⼯��
	 */
	private List<GUID> actionStores;
	/**
	 * �����ַ���
	 */
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
	/**
	 * ��ѯ��ʷ��¼ʱ��ʱ������
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
	 * ���һ���ɲ����ֿ�
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
