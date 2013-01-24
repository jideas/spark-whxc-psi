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

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
/**
 * <p>ȷ�ϳ���</p>
 *


 *
 * @author ��־��
 * @version 2011-11-14
 */

public class SureOutTask extends SimpleTask{

	private Outstorage entity;

	private List<OutstorageItem> detailList;
	
	/**
	 * �����
	 */
	private String deliveryPerson;

	/**
	 * �����λ
	 */
	private String deliveryDepartment;

	/**
	 * ƾ֤��
	 */
	private String voucherNumber;

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public String getDeliveryDepartment() {
		return deliveryDepartment;
	}

	public void setDeliveryDepartment(String deliveryDepartment) {
		this.deliveryDepartment = deliveryDepartment;
	}

	public String getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public int count;

	/**
	 * @param entity ���ⵥʵ��
	 * @param detailList ������ϸʵ�弯��
	 */
	public SureOutTask(Outstorage entity, List<OutstorageItem> detailList){
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
}
