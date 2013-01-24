/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.event
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>调拨待审核事件</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-20
 */

public class InventoryAllocateSubmitted extends Event {

	private GUID inventoryAllocateSheetId;

	public void setInventoryAllocateSheetId(GUID inventoryAllocateSheetId) {
		this.inventoryAllocateSheetId = inventoryAllocateSheetId;
	}

	public GUID getInventoryAllocateSheetId() {
		return inventoryAllocateSheetId;
	}
}
