/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.key.instorage
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-14       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.key.instorage;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-14
 */

public class CheckInLogKey {

	/**
	 * ��ⵥID
	 */
	private GUID checkInSheetId;

	public void setCheckInSheetId(GUID checkInSheetId) {
		this.checkInSheetId = checkInSheetId;
	}

	public GUID getCheckInSheetId() {
		return checkInSheetId;
	}
	
}
