/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.inventory.intf.key.instorage
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckInLogKey {

	/**
	 * 入库单ID
	 */
	private GUID checkInSheetId;

	public void setCheckInSheetId(GUID checkInSheetId) {
		this.checkInSheetId = checkInSheetId;
	}

	public GUID getCheckInSheetId() {
		return checkInSheetId;
	}
	
}
