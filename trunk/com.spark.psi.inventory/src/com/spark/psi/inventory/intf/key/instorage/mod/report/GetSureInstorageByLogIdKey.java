/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.instorage.module.report
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage.mod.report;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����ȷ������¼ID��ѯȷ�������ϸ�б�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-28
 */

public class GetSureInstorageByLogIdKey {
	
	public GetSureInstorageByLogIdKey(GUID tenentsId,GUID checkInLogId){
		this.checkInLogId = checkInLogId;
		this.tenentsId = tenentsId;
	}

	private GUID tenentsId;
	public GUID getTenentsId() {
		return tenentsId;
	}

	private GUID checkInLogId;

	public void setCheckInLogId(GUID checkInLogId) {
		this.checkInLogId = checkInLogId;
	}

	public GUID getCheckInLogId() {
		return checkInLogId;
	}
}
