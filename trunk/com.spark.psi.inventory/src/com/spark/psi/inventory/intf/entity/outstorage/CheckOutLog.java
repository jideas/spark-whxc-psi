/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.outstorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ȷ�ϳ����¼</p>
 *
 * ��ѯһ����ⵥ��ȷ������¼�б�key: CheckInLogKey
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckOutLog {
	
	private GUID tenantsId;
	/**
	 * ��¼Id
	 */
	private GUID id;
	/**
	 * ��ⵥID
	 */
	private GUID checkOutSheetId;	
	/**
	 * ������
	 */
	private String keeper;	
	/**
	 * ����ʱ��
	 */
	private long checkOutDate;	
	/**
	 * ��������
	 */
	private double checkOutCount;	
	/**
	 * ������
	 */
	private double checkOutAmount;
	/**
	 * �����
	 */
	private String taker;	
	/**
	 * �����λ
	 */
	private String takerUnit;	
	
	/**
	 * ƾ֤��
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
