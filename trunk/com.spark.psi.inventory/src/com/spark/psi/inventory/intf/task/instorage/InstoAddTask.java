/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-14       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.instorage;

import java.util.List;
import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.inventory.intf.entity.instorage.mod.InstorageItem;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.DistributeInventoryItem;

/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-14
 */

public class InstoAddTask extends Task<CheckingInType>{

	

	private Instorage entity;

	private List<InstorageItem> detailList;
	
	private String dealingsWay;
	private DistributeInventoryItem[] distributeInventoryItems;
	/**
	 * @param entity ��ⵥʵ��
	 * @param detailList �����ϸʵ�弯��
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
