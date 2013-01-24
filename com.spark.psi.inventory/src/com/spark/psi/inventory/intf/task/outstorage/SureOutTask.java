/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.store.instorage.task.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-11-14       王志坚      
 * ============================================================*/

package com.spark.psi.inventory.intf.task.outstorage;

import java.util.List;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.inventory.intf.entity.outstorage.mod.Outstorage;
import com.spark.psi.inventory.intf.entity.outstorage.mod.OutstorageItem;
/**
 * <p>确认出库</p>
 *


 *
 * @author 王志坚
 * @version 2011-11-14
 */

public class SureOutTask extends SimpleTask{

	private Outstorage entity;

	private List<OutstorageItem> detailList;
	
	/**
	 * 提货人
	 */
	private String deliveryPerson;

	/**
	 * 提货单位
	 */
	private String deliveryDepartment;

	/**
	 * 凭证号
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
	 * @param entity 出库单实体
	 * @param detailList 出库明细实体集合
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
