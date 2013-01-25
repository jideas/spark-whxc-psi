package com.spark.psi.publish.base.store.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.StoreStatus;

/**
 * 仓库列表条目对象<br>
 * 查询方法：<br>
 * 使用GetStoreListKey查询StoreItem列表
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
	 * 可操作功能
	 * 
	 * @return Action[]
	 */
	public Action[] getAction();

}
