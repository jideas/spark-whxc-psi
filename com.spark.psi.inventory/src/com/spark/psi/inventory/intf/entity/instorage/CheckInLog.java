/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.entity.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>确认入库记录</p>
 *
 * 查询一条入库单的确认入库记录列表key: CheckInLogKey
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckInLog {
	
	private GUID tenantsId;
	/**
	 * 记录Id
	 */
	private GUID id;
	/**
	 * 入库单ID
	 */
	private GUID checkInSheetId;	
	/**
	 * 入库人
	 */
	private String keeper;	
	/**
	 * 入库时间
	 */
	private long checkInDate;	
	/**
	 * 入库数量
	 */
	private double checkInCount;	
	/**
	 * 入库金额
	 */
	private double checkInAmount;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getCheckInSheetId() {
		return checkInSheetId;
	}
	public void setCheckInSheetId(GUID checkInSheetId) {
		this.checkInSheetId = checkInSheetId;
	}
	public String getKeeper() {
		return keeper;
	}
	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}
	public long getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(long checkInDate) {
		this.checkInDate = checkInDate;
	}
	public double getCheckInCount() {
		return checkInCount;
	}
	public void setCheckInCount(double checkInCount) {
		this.checkInCount = checkInCount;
	}
	public double getCheckInAmount() {
		return checkInAmount;
	}
	public void setCheckInAmount(double checkInAmount) {
		this.checkInAmount = checkInAmount;
	}
	public void setTenantsId(GUID tenantsId) {
		this.tenantsId = tenantsId;
	}
	public GUID getTenantsId() {
		return tenantsId;
	}	


}
