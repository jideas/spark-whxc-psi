/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.entity.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.entity.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>ȷ������¼</p>
 *
 * ��ѯһ����ⵥ��ȷ������¼�б�key: CheckInLogKey
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckInLog {
	
	private GUID tenantsId;
	/**
	 * ��¼Id
	 */
	private GUID id;
	/**
	 * ��ⵥID
	 */
	private GUID checkInSheetId;	
	/**
	 * �����
	 */
	private String keeper;	
	/**
	 * ���ʱ��
	 */
	private long checkInDate;	
	/**
	 * �������
	 */
	private double checkInCount;	
	/**
	 * �����
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
