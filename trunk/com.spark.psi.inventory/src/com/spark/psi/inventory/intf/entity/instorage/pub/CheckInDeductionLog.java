/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.instorage.pub
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-12       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.instorage.pub;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>入库抵减记录</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-12
 */

public class CheckInDeductionLog {

	/**
	 * 	入库单GUID
	 */
	private GUID checkInSheetGuid;
	/**
	 * 商品GUID
	 */
	private GUID goodsGuid;	
	/**
	 * 本次抵减金额
	 */
	private double deductionAmount;	
	/**
	 * 抵减时间
	 */
	private long deductionDate;
	/**
	 * 租户ID
	 */
	private GUID tenantsId;
	/**
	 * recid
	 */
	private GUID id;
	
	public GUID getCheckInSheetGuid() {
		return checkInSheetGuid;
	}
	public void setCheckInSheetGuid(GUID checkInSheetGuid) {
		this.checkInSheetGuid = checkInSheetGuid;
	}
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
