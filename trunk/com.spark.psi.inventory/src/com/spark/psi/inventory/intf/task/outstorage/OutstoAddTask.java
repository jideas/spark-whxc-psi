/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.store.instorage.task.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-14       ��־��      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.Task;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
import com.spark.psi.publish.CheckingOutType;

/**
 * <p>TODO ������</p>
 *


 *
 * @author ��־��
 * @version 2011-11-14
 */

public class OutstoAddTask extends Task<CheckingOutType>{


	private Outstorage entity;

	private List<OutstorageItem> detailList;
	
	private String dealingsWay;

	/**
	 * @param entity ���ⵥʵ��
	 * @param detailList ������ϸʵ�弯��
	 */
	public OutstoAddTask(Outstorage entity, List<OutstorageItem> detailList){
		this.entity = entity;
		this.detailList = detailList;
	}

	/**
	 * @return the entity
	 */
	public Outstorage getEntity(){
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Outstorage entity){
		this.entity = entity;
	}

	/**
	 * @return the detailList
	 */
	public List<OutstorageItem> getDetailList(){
		return detailList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<OutstorageItem> detailList){
		this.detailList = detailList;
	}

	public void setDealingsWay(String dealingsWay) {
		this.dealingsWay = dealingsWay;
	}

	public String getDealingsWay() {
		return dealingsWay;
	}
}
