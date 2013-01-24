/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.instorage.module.report
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage.mod.report;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>根据确认入库记录ID查询确认入库明细列表</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

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
