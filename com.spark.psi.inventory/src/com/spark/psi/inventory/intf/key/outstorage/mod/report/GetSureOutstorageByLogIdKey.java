/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.outstorage.module.report
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.outstorage.mod.report;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
