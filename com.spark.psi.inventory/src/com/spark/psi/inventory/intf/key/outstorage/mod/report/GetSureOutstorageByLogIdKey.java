/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.outstorage.module.report
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage.mod.report;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-28
 */

public class GetSureOutstorageByLogIdKey {

	private GUID tenantsId;
	public GUID getTenantsId() {
		return tenantsId;
	}

	private GUID logId;

	public void setLogId(GUID tenantsId,GUID logId) {
		this.tenantsId = tenantsId;
		this.logId = logId;
	}

	public GUID getLogId() {
		return logId;
	}
}
