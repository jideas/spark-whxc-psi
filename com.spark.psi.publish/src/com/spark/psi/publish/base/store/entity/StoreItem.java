package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;

/**
 * �ֿ��б���Ŀ����<br>
 * ��ѯ������<br>
 * ʹ��GetStoreListKey��ѯStoreItem�б�
 */
public interface StoreItem {

	/**
	 * @return the id
	 */
	public GUID getId();

	/**
	 * @return the name
	 */
	public String getName();
	/**
	 * @return the status
	 */
	public StoreStatus getStatus();

	/**
	 * @return the address
	 */
	public String getAddress();

	/**
	 * @return the keeperIds
	 */
	public GUID[] getKeeperIds();
	/**
	 * @return the kepperInfo
	 */
	public String getKepperInfo();
	
	/**
	 * �ɲ�������
	 * 
	 * @return Action[]
	 */
	public Action[] getAction();

}
