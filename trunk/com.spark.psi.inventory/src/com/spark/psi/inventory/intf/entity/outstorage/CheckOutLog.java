/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>确认出库记录</p>
 *
 * 查询一条入库单的确认入库记录列表key: CheckInLogKey
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckOutLog {
	
	private GUID tenantsId;
	/**
	 * 记录Id
	 */
	private GUID id;
	/**
	 * 入库单ID
	 */
	private GUID checkOutSheetId;	
	/**
	 * 出库人
	 */
	private String keeper;	
	/**
	 * 出库时间
	 */
	private long checkOutDate;	
	/**
	 * 出库数量
	 */
	private double checkOutCount;	
	/**
	 * 出库金额
	 */
	private double checkOutAmount;
	/**
	 * 提货人
	 */
	private String taker;	
	/**
	 * 提货单位
	 */
	private String takerUnit;	
	
	/**
	 * 凭证号
	 */
	private String vouchersNumber;	

	public String getTaker() {
		return taker;
	}
	public void setTaker(String taker) {
		this.taker = taker;
	}
	public String getTakerUnit() {
		return takerUnit;
	}
	public void setTakerUnit(String takerUnit) {
		this.takerUnit = takerUnit;
	}
	public String getVouchersNumber() {
		return vouchersNumber;
	}
	public void setVouchersNumber(String vouchersNumber) {
		this.vouchersNumber = vouchersNumber;
	}
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getCheckOutSheetId() {
		return checkOutSheetId;
	}
	public void setCheckOutSheetId(GUID checkOutSheetId) {
		this.checkOutSheetId = checkOutSheetId;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public long getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public double getCheckOutCount() {
		return checkOutCount;
	}
	public void setCheckOutCount(double checkOutCount) {
		this.checkOutCount = checkOutCount;
	}
	public double getCheckOutAmount() {
		return checkOutAmount;
	}
	public void setCheckOutAmount(double checkOutAmount) {
		this.checkOutAmount = checkOutAmount;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}
	

}
