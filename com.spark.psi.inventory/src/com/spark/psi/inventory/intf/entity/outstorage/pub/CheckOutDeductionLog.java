/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.instorage.pub
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����ּ���¼</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class CheckOutDeductionLog {

	/**
	 * 	���ⵥGUID
	 */
	private GUID checkOutSheetGuid;
	/**
	 * ��ƷGUID
	 */
	private GUID goodsGuid;	
	/**
	 * ���εּ����
	 */
	private double deductionAmount;	
	/**
	 * �ּ�ʱ��
	 */
	private long deductionDate;


	/**
	 * �⻧ID
	 */
	private GUID tenantsId;
	/**
	 * recid
	 */
	private GUID id;
	
	public GUID getGoodsGuid() {
		return goodsGuid;
	}
	public void setGoodsGuid(GUID goodsGuid) {
		this.goodsGuid = goodsGuid;
	}
	public double getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}
	public long getDeductionDate() {
		return deductionDate;
	}
	public void setDeductionDate(long deductionDate) {
		this.deductionDate = deductionDate;
	}
	public void setCheckOutSheetGuid(GUID checkOutSheetGuid) {
		this.checkOutSheetGuid = checkOutSheetGuid;
	}
	public GUID getCheckOutSheetGuid() {
		return checkOutSheetGuid;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getId() {
		return id;
	}	

}
