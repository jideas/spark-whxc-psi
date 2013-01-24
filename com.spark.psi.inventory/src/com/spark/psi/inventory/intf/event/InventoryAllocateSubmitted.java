/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.event
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-20       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.event;

import com.jiuqi.dna.core.invoke.Event;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>����������¼�</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

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
