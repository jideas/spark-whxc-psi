/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.inventory.intf.inventoryenum
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-2       Wangtiancai        
 * ============================================================*/

package com.spark.psi.inventory.intf.inventoryenum;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2012-3-2
 */

public enum AllocateTab {
	/** ����	 */
	TAB_CREATE,
	
	/** ��� */
	TAB_EXAMINE,
	
	/** ���� */
	TAB_FOLLOW,
	
	/** ��ʷ */
	TAB_HISTORY;
	
	public String getTitle() {
		switch(this) {
		case TAB_CREATE:
			return "��������";
		case TAB_EXAMINE:
			return "��������";
		case TAB_FOLLOW:
			return "��������";
		case TAB_HISTORY:
			return "������¼";
		default:
			return "";
		}
	}
	
}
