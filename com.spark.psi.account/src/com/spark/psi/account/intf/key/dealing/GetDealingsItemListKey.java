/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.account.intf.key.dealing
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-29       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.key.dealing;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>��ѯ����������������ϸKey</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-29
 */

public class GetDealingsItemListKey {

	private GUID partnerId;
	private long startTime;
	private long endTime;
	
	private int count;
	private int offSet;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOffSet() {
		return offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public GUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(GUID partnerId) {
		this.partnerId = partnerId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
