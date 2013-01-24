/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.instorage;

import java.util.List;
import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * <p>TODO 类描述</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-14
 */

public class InstoAddTask extends Task<CheckingInType>{

	

	private Instorage entity;

	private List<InstorageItem> detailList;
	
	private String dealingsWay;
	private DistributeInventoryItem[] distributeInventoryItems;
	/**
	 * @param entity 入库单实体
	 * @param detailList 入库明细实体集合
	 */
	public InstoAddTask(Instorage entity, List<InstorageItem> detailList){
		this.entity = entity;
		this.detailList = detailList;
	}

	public DistributeInventoryItem[] getDistributeInventoryItems() {
		return distributeInventoryItems;
	}

	public void setDistributeInventoryItems(DistributeInventoryItem[] distributeInventoryItems) {
		this.distributeInventoryItems = distributeInventoryItems;
	}

	/**
	 * @return the entity
	 */
	public Instorage getEntity(){
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Instorage entity){
		this.entity = entity;
	}

	/**
	 * @return the detailList
	 */
	public List<InstorageItem> getDetailList(){
		return detailList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<InstorageItem> detailList){
		this.detailList = detailList;
	}

	public void setDealingsWay(String dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	public String getDealingsWay() {
		return dealingsWay;
	}
}
